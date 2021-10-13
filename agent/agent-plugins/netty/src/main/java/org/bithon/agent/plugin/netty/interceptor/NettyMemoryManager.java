/*
 *    Copyright 2020 bithon.cn
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

package org.bithon.agent.plugin.netty.interceptor;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Frank Chen
 * @date 13/10/21 3:51 pm
 */
public class NettyMemoryManager {
    public static final NettyMemoryManager INSTANCE = new NettyMemoryManager();

    private final Map<Long, Long> addrMap = new ConcurrentHashMap<>(256);
    private final AtomicLong allocatedSize = new AtomicLong();

    public void allocate(long addr, long size) {
        if (addrMap.putIfAbsent(addr, size) == null) {
            allocatedSize.addAndGet(size);
        }
    }

    public void free(long addr) {
        Long size = addrMap.remove(addr);
        if (size != null) {
            allocatedSize.addAndGet(-1 * size);
        }
    }

    public void reallocate(long oldAddr, long newAddr, long newSize) {
        free(oldAddr);
        allocate(newAddr, newSize);
    }

    public long getUsed() {
        return allocatedSize.longValue();
    }
}
