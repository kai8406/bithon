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

package org.bithon.server.storage.datasource.transformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bithon.component.commons.utils.HumanReadablePercentage;
import org.bithon.server.commons.serializer.HumanReadablePercentageDeserializer;
import org.bithon.server.commons.serializer.HumanReadablePercentageSerializer;
import org.bithon.server.storage.datasource.input.transformer.ITransformer;
import org.bithon.server.storage.datasource.input.transformer.ProbabilisticSamplerTransform;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Frank Chen
 * @date 25/1/24 5:27 pm
 */
public class ProbabilisticSamplerTransformTest {

    @Test
    public void test_NegativeProbability() {
        ITransformer transformer = new ProbabilisticSamplerTransform(HumanReadablePercentage.parse("-1%"));
        for (int i = 0; i < ProbabilisticSamplerTransform.MAX_PROBABILITY_VALUE + 1; i++) {
            Assert.assertFalse(transformer.transform(null));
        }
    }

    @Test
    public void test_ZeroProbability() {
        {
            ITransformer transformer = new ProbabilisticSamplerTransform(HumanReadablePercentage.parse("0%"));
            for (int i = 0; i < ProbabilisticSamplerTransform.MAX_PROBABILITY_VALUE + 1; i++) {
                Assert.assertFalse(transformer.transform(null));
            }
        }
    }

    @Test
    public void test_LessThanMinimumProbability() {
        ITransformer transformer = new ProbabilisticSamplerTransform(HumanReadablePercentage.parse((0.9 * 100 / ProbabilisticSamplerTransform.MAX_PROBABILITY_VALUE) + "%"));

        for (int j = 0; j < 3; j++) { // Test for 3 loops
            for (int i = 0; i < ProbabilisticSamplerTransform.MAX_PROBABILITY_VALUE; i++) {
                Assert.assertFalse(transformer.transform(null));
            }
        }
    }

    @Test
    public void test_MinimumProbability() {
        ITransformer transformer = new ProbabilisticSamplerTransform(HumanReadablePercentage.parse((100.0 / ProbabilisticSamplerTransform.MAX_PROBABILITY_VALUE) + "%"));

        for (int j = 0; j < 3; j++) { // Test for 3 loops
            Assert.assertTrue(transformer.transform(null));
            for (int i = 0; i < ProbabilisticSamplerTransform.MAX_PROBABILITY_VALUE - 1; i++) {
                Assert.assertFalse(transformer.transform(null));
            }
        }
    }

    @Test
    public void test_MaximumProbability() {
        ITransformer transformer = new ProbabilisticSamplerTransform(HumanReadablePercentage.parse("100%"));

        for (int j = 0; j < 3; j++) { // Test for 3 loops
            for (int i = 0; i < ProbabilisticSamplerTransform.MAX_PROBABILITY_VALUE; i++) {
                Assert.assertTrue(transformer.transform(null));
            }
        }
    }

    @Test
    public void test_AboveMaximumProbability() {
        ITransformer transformer = new ProbabilisticSamplerTransform(HumanReadablePercentage.parse("101%"));

        for (int j = 0; j < 3; j++) { // Test for 3 loops
            for (int i = 0; i < ProbabilisticSamplerTransform.MAX_PROBABILITY_VALUE; i++) {
                Assert.assertTrue(transformer.transform(null));
            }
        }
    }

    @Test
    public void test_50PercentageProbability() {
        ITransformer transformer = new ProbabilisticSamplerTransform(HumanReadablePercentage.parse("50%"));
        for (int i = 0; i < 200; i++) {
            Assert.assertTrue(transformer.transform(null));
            Assert.assertFalse(transformer.transform(null));
        }
    }

    @Test
    public void test_JsonDeserialization() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        SimpleModule m = new SimpleModule();
        m.addDeserializer(HumanReadablePercentage.class, new HumanReadablePercentageDeserializer());
        m.addSerializer(HumanReadablePercentage.class, new HumanReadablePercentageSerializer());
        om.registerModule(m);

        ITransformer transformer = om.readValue(om.writeValueAsString(new ProbabilisticSamplerTransform(HumanReadablePercentage.parse("50%"))), ITransformer.class);

        for (int i = 0; i < 200; i++) {
            Assert.assertTrue(transformer.transform(null));
            Assert.assertFalse(transformer.transform(null));
        }
    }
}
