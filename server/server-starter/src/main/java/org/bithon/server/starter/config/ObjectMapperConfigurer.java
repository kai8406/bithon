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

package org.bithon.server.starter.config;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bithon.server.commons.time.Period;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * @author frank.chen021@outlook.com
 * @date 2021-10-01
 */
@Configuration
public class ObjectMapperConfigurer {
    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder,
                                     ApplicationContext applicationContext) {
        // use Jackson2ObjectMapperBuilder so that all instances of injected 'Module's can be registered to this ObjectMapper
        return builder.serializers(new JsonSerializer<Timestamp>() {
                                       @Override
                                       public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                                           gen.writeNumber(value.getTime());
                                       }

                                       @Override
                                       public Class<Timestamp> handledType() {
                                           return Timestamp.class;
                                       }
                                   },
                                   new JsonSerializer<Period>() {
                                       @Override
                                       public void serialize(Period value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                                           gen.writeString(value.getText());
                                       }

                                       @Override
                                       public Class<Period> handledType() {
                                           return Period.class;
                                       }
                                   }
                      ).build()
                      .setInjectableValues(new InjectableValues() {
                          @Override
                          public Object findInjectableValue(Object valueId,
                                                            DeserializationContext ctx,
                                                            BeanProperty forProperty,
                                                            Object beanInstance) {
                              JacksonInject inject = forProperty.getAnnotation(JacksonInject.class);
                              if (!StringUtils.isEmpty(inject.value())) {
                                  // use JacksonInject annotation's value() as bean name
                                  return applicationContext.getBean(inject.value());
                              } else {
                                  Class<?> targetClass = forProperty.getType().getRawClass();
                                  if (targetClass.isAssignableFrom(applicationContext.getClass())) {
                                      return applicationContext;
                                  } else {
                                      return applicationContext.getBean(targetClass);
                                  }
                              }
                          }
                      })
                      .configure(JsonParser.Feature.ALLOW_COMMENTS, true)
                      .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                      .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }
}
