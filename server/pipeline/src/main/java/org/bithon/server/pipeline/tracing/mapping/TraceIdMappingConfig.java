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

package org.bithon.server.pipeline.tracing.mapping;

import java.util.HashMap;

/**
 * Extract a user-defined transaction id on a given parameter to trace id
 * See {@link ITraceIdMappingExtractor} and its implementation
 *
 * - type: xxx
 * - args:
 *
 * @author frank.chen021@outlook.com
 * @date 10/12/21 3:08 PM
 */
public class TraceIdMappingConfig extends HashMap<String, Object> {
}
