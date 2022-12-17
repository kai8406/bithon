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

package org.bithon.agent.bootstrap.aop.advice;

import org.bithon.agent.bootstrap.aop.AbstractInterceptor;
import org.bithon.shaded.net.bytebuddy.description.annotation.AnnotationList;
import org.bithon.shaded.net.bytebuddy.description.field.FieldDescription;
import org.bithon.shaded.net.bytebuddy.description.type.TypeDescription;
import org.bithon.shaded.net.bytebuddy.jar.asm.Opcodes;

import javax.annotation.Nonnull;

/**
 * @author frank.chen021@outlook.com
 * @date 19/2/22 3:47 PM
 */
public class InterceptorResolver extends FieldDescription.InDefinedShape.AbstractBase {
    private static final TypeDescription.Generic INTERCEPTOR_TYPE = new TypeDescription.ForLoadedType(AbstractInterceptor.class).asGenericType();

    private final TypeDescription declaringType;
    private final String fieldName;

    public InterceptorResolver(@Nonnull TypeDescription declaringType,
                               String fieldName) {
        this.declaringType = declaringType;
        this.fieldName = fieldName;
    }

    @Nonnull
    @Override
    public TypeDescription getDeclaringType() {
        return declaringType;
    }

    @Nonnull
    @Override
    public TypeDescription.Generic getType() {
        return INTERCEPTOR_TYPE;
    }

    @Override
    public int getModifiers() {
        return Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC;
    }

    @Nonnull
    @Override
    public String getName() {
        return fieldName;
    }

    @Nonnull
    @Override
    public AnnotationList getDeclaredAnnotations() {
        return new AnnotationList.Empty();
    }
}
