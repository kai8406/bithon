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

package org.bithon.server.collector.cmd.api;

import lombok.Data;
import org.bithon.agent.rpc.brpc.cmd.IConfigCommand;
import org.bithon.agent.rpc.brpc.cmd.IJvmCommand;
import org.bithon.component.brpc.channel.ServerChannel;
import org.bithon.component.brpc.exception.ServiceInvocationException;
import org.bithon.component.commons.utils.StringUtils;
import org.bithon.server.collector.cmd.service.CommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Frank Chen
 * @date 2022/8/7 20:46
 */
@RestController
public class CommandApi {
    private final CommandService commandService;

    public CommandApi(CommandService commandService) {
        this.commandService = commandService;
    }

    @GetMapping("/api/command/clients")
    public Map<String, List<Map<String, String>>> getClients() {
        Map<String, List<Map<String, String>>> clients = new HashMap<>(17);
        List<ServerChannel.Session> sessions = commandService.getServerChannel()
                                                             .getSessions();
        for (ServerChannel.Session session : sessions) {
            Map<String, String> properties = new HashMap<>();
            if (StringUtils.hasText(session.getAppId())) {
                properties.put("appId", session.getAppId());
            }
            properties.put("endpoint", session.getEndpoint().toString());

            clients.computeIfAbsent(session.getAppName(), v -> new ArrayList<>())
                   .add(properties);
        }
        return clients;
    }

    @PostMapping("/api/command/jvm/dumpThread")
    public void dumpThread(@Valid @RequestBody CommandArgs<Void> args, HttpServletResponse response) throws IOException {
        IJvmCommand command = commandService.getServerChannel().getRemoteService(args.getAppId(), IJvmCommand.class);
        if (command == null) {
            response.setContentType("application/text");
            response.getWriter().write(StringUtils.format("client by id [%s] not found", args.getAppId()));
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }
        try {
            // Get
            List<IJvmCommand.ThreadInfo> threads = command.dumpThreads();

            // Sort by thread name for better analysis
            threads.sort(Comparator.comparing(IJvmCommand.ThreadInfo::getName));

            // Output as stream
            response.setContentType("application/text");
            response.setStatus(HttpStatus.OK.value());
            PrintWriter pw = response.getWriter();

            // Output header
            pw.write(StringUtils.format("---Total Threads: %d---\n", threads.size()));
            for (IJvmCommand.ThreadInfo thread : threads) {
                pw.write(StringUtils.format("Id: %d, Name: %s, State: %s \n", thread.getThreadId(), thread.getName(), thread.getState()));
                String[] stackElements = thread.getStacks().split("\n");
                for (String stackElement : stackElements) {
                    pw.write('\t');
                    pw.write(stackElement);
                    pw.write('\n');
                }
                pw.write('\n');
                pw.flush();
            }
        } catch (ServiceInvocationException e) {
            response.setContentType("application/text");
            response.getWriter().write(e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    /**
     * @param args A string pattern which comply with database's like expression.
     *             For example:
     *             "%CommandApi" will match all classes whose name ends with CommandApi
     *             "CommandApi" matches only qualified class name that is the exact CommandApi
     *             "%bithon% matches all qualified classes whose name contains bithon
     */
    @PostMapping("/api/command/jvm/dumpClazz")
    public CommandResponse<Set<String>> dumpClazz(@Valid @RequestBody CommandArgs<String> args) {
        IJvmCommand command = commandService.getServerChannel().getRemoteService(args.getAppId(), IJvmCommand.class);
        if (command == null) {
            return CommandResponse.error(StringUtils.format("client by id [%s] not found", args.getAppId()));
        }
        try {
            String pattern;
            if (StringUtils.isEmpty(args.getArgs())) {
                pattern = ".*";
            } else {
                pattern = args.getArgs();
                pattern = pattern.replace(".", "\\.").replace("%", ".*");
            }

            return CommandResponse.success(new TreeSet<>(command.dumpClazz(pattern)));
        } catch (ServiceInvocationException e) {
            return CommandResponse.exception(e);
        }
    }

    @Data
    static class GetConfigurationRequest {
        private String format;
        private boolean pretty;
    }

    @PostMapping("/api/command/config/get")
    public ResponseEntity<String> getConfiguration(@RequestBody CommandArgs<GetConfigurationRequest> args) {
        IConfigCommand command = commandService.getServerChannel().getRemoteService(args.getAppId(), IConfigCommand.class);
        if (command == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .header("Content-Type", "application/text")
                                 .body(StringUtils.format("client by id [%s] not found", args.getAppId()));
        }
        try {
            return ResponseEntity.status(HttpStatus.OK)
                                 .header("Content-Type", "application/text")
                                 .body(command.getConfiguration(args.getArgs().getFormat(), args.getArgs().isPretty()));
        } catch (ServiceInvocationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .header("Content-Type", "application/text")
                                 .body(e.getMessage());
        }
    }
}
