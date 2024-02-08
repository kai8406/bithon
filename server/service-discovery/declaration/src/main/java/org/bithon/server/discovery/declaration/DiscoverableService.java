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

package org.bithon.server.discovery.declaration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A service annotation used to declare a service is provided in multiple instances.
 * <br/>
 * Currently,
 * it is used only for the {@link org.bithon.server.discovery.declaration.cmd.IAgentProxyApi},
 * which is implemented in the controller module.
 * <br/>
 * For any methods in an interface which is annotated by this annotation,
 * the return value MUST be type of {@link ServiceResponse}.
 *
 * @author frank.chen
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DiscoverableService {
    /**
     * service name. MUST be unique
     */
    String name();
}
