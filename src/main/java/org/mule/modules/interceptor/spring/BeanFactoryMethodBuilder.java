/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.spring;

import java.util.Map;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;


/**
 * <p>
 * Sets the constructor information to a bean definition
 * </p>
 *
 * @author Mulesoft Inc.
 * @since  3.3.2
 */
public class BeanFactoryMethodBuilder
{

    /**
     * <p>
     * The bean definition that has to be modified
     * </p>
     */
    private AbstractBeanDefinition beanDefinition;


    /**
     * <p>
     * Constructor. Sets the factory method name and the bean factory.
     * </p>
     *
     * @param beanDefinition    <p>
     *                          The bean definition that has to be modified
     *                          </p>
     * @param factoryMethodName <p>
     *                          The factory method name
     *                          </p>
     * @param beanFactoryName   <p>
     *                          The bean factory name
     *                          </p>
     */
    public BeanFactoryMethodBuilder(AbstractBeanDefinition beanDefinition,
                                    String factoryMethodName,
                                    String beanFactoryName)
    {
        this.beanDefinition = beanDefinition;

        beanDefinition.setFactoryBeanName(beanFactoryName);
        beanDefinition.setFactoryMethodName(factoryMethodName);
    }

    /**
     * <p>
     * Sets the constructor arguments to the bean definition
     * </p>
     *
     * @param constructorArguments <p>
     *                             The constructor arguments for the bean definition
     *                             </p>
     * @return <p>
     *         The bean definition with the one it was created.
     *         </p>
     */
    public AbstractBeanDefinition withConstructorArguments(Object... constructorArguments)
    {
        int argumentsSize = constructorArguments.length;
        ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();

        ConstructorArgumentValues values = new ConstructorArgumentValues();
        Map<Integer, ConstructorArgumentValues.ValueHolder> indexedArgumentValues = constructorArgumentValues.getIndexedArgumentValues();

        for (int i = 0; i < indexedArgumentValues.size(); i++)
        {
            values.addIndexedArgumentValue(i + argumentsSize, indexedArgumentValues.get(i));
        }

        for (int i = 0; i < argumentsSize; i++)
        {
            values.addIndexedArgumentValue(i, constructorArguments[i]);
        }
        beanDefinition.setConstructorArgumentValues(values);

        return beanDefinition;
    }
}

