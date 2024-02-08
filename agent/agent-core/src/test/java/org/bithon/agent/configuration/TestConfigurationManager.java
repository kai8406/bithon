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

package org.bithon.agent.configuration;

import org.bithon.component.commons.utils.HumanReadablePercentage;
import org.bithon.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.bithon.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

/**
 * @author frank.chen021@outlook.com
 * @date 2023/1/6 20:09
 */
public class TestConfigurationManager {

    @ConfigurationProperties(prefix = "test")
    static class TestConfig {
        private int a;
        private int b;

        private HumanReadablePercentage percentage;

        public TestConfig() {
        }

        public TestConfig(int a, int b, String p) {
            this.a = a;
            this.b = b;
            this.percentage = HumanReadablePercentage.parse(p);
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public HumanReadablePercentage getPercentage() {
            return percentage;
        }

        public void setPercentage(HumanReadablePercentage percentage) {
            this.percentage = percentage;
        }
    }

    private final ObjectMapper objectMapper = ObjectMapperConfigurer.configure(new ObjectMapper());

    @Test
    public void test() throws IOException {
        JsonNode json = objectMapper.readTree(objectMapper.writeValueAsBytes(Collections.singletonMap("test", new TestConfig(1, 7, "8%"))));
        ConfigurationManager manager = ConfigurationManager.create(new Configuration(json));

        TestConfig testConfig = manager.getConfig(TestConfig.class);
        Assert.assertEquals(1, testConfig.getA());
        Assert.assertEquals(7, testConfig.getB());
        Assert.assertEquals("8%", testConfig.getPercentage().toString());

        //
        // Use new value to refresh the old one
        //
        JsonNode json2 = objectMapper.readTree(objectMapper.writeValueAsBytes(Collections.singletonMap("test", new TestConfig(2, 8, "500%"))));
        manager.refresh(new Configuration(json2));
        Assert.assertEquals(2, testConfig.getA());
        Assert.assertEquals(8, testConfig.getB());
        Assert.assertEquals(5, testConfig.getPercentage().intValue());
    }
}
