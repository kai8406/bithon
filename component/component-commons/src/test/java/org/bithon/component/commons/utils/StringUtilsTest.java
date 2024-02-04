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

package org.bithon.component.commons.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Frank Chen
 * @date 31/1/24 10:09 am
 */
public class StringUtilsTest {

    @Test
    public void testBase16Encoding() {
        String id = "ea91433abf7d42a4b5afbbbeb8f71cec";
        String id2 = StringUtils.base16BytesToString(StringUtils.base16StringToBytes(id));
        Assert.assertEquals(id, id2);
    }

    @Test
    public void testBase16Encoding_ByteAccessor() {
        String id = "ea91433abf7d42a4b5afbbbeb8f71cec";

        byte[] bytes = StringUtils.base16StringToBytes(id);

        String id2 = StringUtils.base16BytesToString((idx) -> bytes[idx], bytes.length);
        Assert.assertEquals(id, id2);
    }
}
