/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.spring;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class BeanFactoryMethodBuilderTest
{

    @Test
    public void addingNewConstructorParametersToExistingBeanDefinition()
    {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();

        constructorArgumentValues.addGenericArgumentValue("arg1");
        BeanFactoryMethodBuilder builder = new BeanFactoryMethodBuilder(beanDefinition, "factoryMethod", "factoryBean");

        AbstractBeanDefinition modifiedBeanDefinition = builder.withConstructorArguments("test1", "test2");

        assertEquals("test1", modifiedBeanDefinition.getConstructorArgumentValues().getArgumentValue(0, String.class).getValue());
        assertEquals("test2", modifiedBeanDefinition.getConstructorArgumentValues().getArgumentValue(1, String.class).getValue());
    }
}
