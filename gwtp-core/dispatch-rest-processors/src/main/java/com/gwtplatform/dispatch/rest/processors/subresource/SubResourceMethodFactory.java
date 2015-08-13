/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.dispatch.rest.processors.subresource;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;

import com.google.auto.service.AutoService;
import com.gwtplatform.dispatch.rest.processors.logger.Logger;
import com.gwtplatform.dispatch.rest.processors.resolvers.HttpVerbResolver;
import com.gwtplatform.dispatch.rest.processors.resource.Resource;
import com.gwtplatform.dispatch.rest.processors.resource.ResourceMethod;
import com.gwtplatform.dispatch.rest.processors.resource.ResourceMethodFactory;
import com.gwtplatform.dispatch.rest.processors.utils.Utils;

import static com.google.auto.common.MoreTypes.asTypeElement;

@AutoService(ResourceMethodFactory.class)
public class SubResourceMethodFactory implements ResourceMethodFactory {
    @Override
    public boolean canHandle(ExecutableElement element) {
        return !HttpVerbResolver.isPresent(element)
                && asTypeElement(element.getReturnType()).getKind() == ElementKind.INTERFACE;
    }

    @Override
    public ResourceMethod resolve(Logger logger, Utils utils, Resource resource, ExecutableElement element) {
        return new SubResourceMethod(logger, utils, resource, element);
    }
}
