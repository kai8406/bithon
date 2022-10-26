/*
 *    Copyright 2020 bithon.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.bithon.server.web.service.tracing.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bithon.component.commons.tracing.SpanKind;
import org.bithon.component.commons.tracing.Tags;
import org.bithon.server.storage.tracing.TraceSpan;
import org.bithon.server.web.service.tracing.api.TraceSpanBo;
import org.bithon.server.web.service.tracing.api.TraceTopo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Build application topo for a specific tracing
 *
 * @author frank.chen021@outlook.com
 * @date 24/11/21 7:06 pm
 */
@Data
@Slf4j
public class TraceTopoBuilder {

    static class Instance {
        String application;
        String instanceName;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Instance other = (Instance) o;
            return Objects.equals(application, other.application) && Objects.equals(instanceName, other.instanceName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(application, instanceName);
        }

        public Instance(String application, String instanceName) {
            this.application = application;
            this.instanceName = instanceName;
        }

        public static Instance of(String application, String instanceName) {
            return new Instance(application, instanceName);
        }
    }

    private final Map<String, TraceTopo.Link> links = new HashMap<>();

    private final Map<String, TraceTopo.Node> nodes = new HashMap<>();

    /**
     * key: level
     * val: count of nodes in this level
     */
    private final Map<Integer, Integer> nodeCountOfLevels = new HashMap<>();

    /**
     * A map from instance to generated node name
     */
    private final Map<Instance, String> nodeNameMap = new HashMap<>();
    private int nodeNumber = 0;
    private int maxLevel = -1;
    private int maxNodeCount = -1;

    private String getNodeName(TraceSpan span) {
        return nodeNameMap.computeIfAbsent(Instance.of(span.getAppName(), span.getInstanceName()),
                                           (key) -> "n" + nodeNumber++);
    }

    private TraceTopo.Link addLink(TraceSpan source, TraceSpan target) {
        String srcNodeName = getNodeName(source);
        TraceTopo.Node srcNode = nodes.get(srcNodeName);
        if (srcNode == null) {
            srcNode = new TraceTopo.Node(srcNodeName, source.getAppName(), source.getInstanceName());
            srcNode.setNodeIndex(getAndIncreaseNodeCount(srcNode.getLevel()));

            nodes.put(srcNodeName, srcNode);
        }

        String dstNodeName = getNodeName(target);
        TraceTopo.Node dstNode = nodes.get(dstNodeName);
        if (dstNode == null) {
            dstNode = new TraceTopo.Node(dstNodeName, target.getAppName(), target.getInstanceName());
            dstNode.setLevel(srcNode.getLevel() + 1);
            dstNode.setNodeIndex(getAndIncreaseNodeCount(dstNode.getLevel()));

            nodes.put(dstNodeName, dstNode);
        }

        maxLevel = Math.max(maxLevel, dstNode.getLevel());
        maxNodeCount = Math.max(maxNodeCount, dstNode.getNodeIndex() + 1);

        return this.links.computeIfAbsent(srcNodeName + "->" + dstNodeName,
                                          v -> new TraceTopo.Link(srcNodeName, dstNodeName));
    }

    private int getAndIncreaseNodeCount(int level) {
        return nodeCountOfLevels.compute(level, (k, old) -> old == null ? 1 : old + 1);
    }

    @SuppressWarnings("unchecked")
    public TraceTopo build(List<? extends TraceSpan> spans) {
        //
        // Step 2. Traverse the tree to get Topo
        //
        for (TraceSpanBo root : (List<TraceSpanBo>) spans) {
            buildLink(root, root.children);
        }

        //
        // Step 3. create user node if necessary
        //
        TraceSpanBo user = new TraceSpanBo();
        user.setAppName("user");

        TraceSpanBo producer = new TraceSpanBo();
        producer.setAppName("producer");
        for (TraceSpan root : spans) {
            if ("SERVER".equals(root.kind)) {
                // TODO: we can detect the root.tags['http.header.User-Agent'] to decide the name of user
                // For example, if the user-agent is like 'chrome', the name could be chrome and icon could also be chrome style
                this.addLink(user, root).incrCount();
            } else if (SpanKind.CONSUMER.name().equals(root.kind)) {
                this.addLink(producer, root).incrCount();
            }
        }

        // Step 3. set level/node property for each node
        Collection<TraceTopo.Node> topoNodes = nodes.values();
        topoNodes.forEach((node) -> {
            int nodeCount = this.nodeCountOfLevels.getOrDefault(node.getLevel(), 1);
            node.setNodeCount(nodeCount);
        });
        return new TraceTopo(topoNodes, links.values(), maxLevel, maxNodeCount);
    }

    /**
     * Search spans that crosses two instances and then create a directed graph
     */
    private void buildLink(TraceSpanBo parentSpan, List<?> childSpans) {
        //noinspection unchecked
        for (TraceSpanBo childSpan : (List<TraceSpanBo>) childSpans) {

            if (!parentSpan.getAppName().equals(childSpan.getAppName())
                || !Objects.equals(parentSpan.getInstanceName(), childSpan.getInstanceName())) {

                this.addLink(parentSpan, childSpan).incrCount();

                buildLink(childSpan, childSpan.children);
            } else {
                // the instance of childSpan is the same as the parentSpan
                // no need to create a link, but just recursively search next level
                buildLink(parentSpan, childSpan.children);
            }

            // this childSpan is a CLIENT termination,
            // when there's no children, it means the next hop might be another system.
            // So, we need to create a link for this situation
            if (childSpan.children.size() == 0
                && "CLIENT".equals(childSpan.getKind())
                && childSpan.containsTag(Tags.HTTP_URI)) {

                String uriText = childSpan.getTag(Tags.HTTP_URI);
                try {
                    URI uri = new URI(uriText);
                    TraceSpan next = new TraceSpan();
                    next.setAppName(uri.getScheme());
                    next.setInstanceName(uri.getHost() + ":" + uri.getPort());
                    this.addLink(childSpan, next).incrCount();
                } catch (URISyntaxException e) {
                    log.error("Malformed uri detected in span: {}", childSpan);
                }
            }
        }
    }
}