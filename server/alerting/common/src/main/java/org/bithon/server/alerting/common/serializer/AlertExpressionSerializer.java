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

package org.bithon.server.alerting.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bithon.server.alerting.common.model.AlertExpression;

import java.io.IOException;

/**
 * This serializer serializes the {@link AlertExpression} so that clients (FE) understand each part of the parsed expression
 * as well as raw text.
 *
 * @author Frank Chen
 * @date 15/2/24 5:16 pm
 */
public class AlertExpressionSerializer extends JsonSerializer<AlertExpression> {

    @Override
    public Class<AlertExpression> handledType() {
        return AlertExpression.class;
    }

    @Override
    public void serialize(AlertExpression expression, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        {
            gen.writeStringField("id", expression.getId());

            // The raw expression for rendering
            gen.writeStringField("expressionText", expression.serializeToText());

            // Breakdown elements in the expression
            gen.writeStringField("from", expression.getFrom());
            gen.writeObjectField("select", expression.getSelect());
            gen.writeStringField("where", expression.getWhere());
            serializers.defaultSerializeField("window", expression.getWindow(), gen);

            if (expression.getGroupBy() != null) {
                gen.writeArrayFieldStart("groupBy");
                for (String group : expression.getGroupBy()) {
                    gen.writeString(group);
                }
                gen.writeEndArray();
            }
            gen.writeStringField("alertPredicate", expression.getAlertPredicate());
            gen.writeObjectField("alertExpected", expression.getAlertExpected());

            if (expression.getExpectedWindow() != null) {
                serializers.defaultSerializeField("expectedWindow", expression.getWindow(), gen);
            }
        }
        gen.writeEndObject();
    }
}
