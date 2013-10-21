/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.connectors;

import org.mule.modules.interceptor.spring.BeanFactoryMethodBuilder;
import org.mule.modules.interceptor.spring.MethodInterceptorFactory;

import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

/**
 * <p>
 * Factory to create the method interceptor for Mule Connector
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class ConnectorMethodInterceptorFactory extends MethodInterceptorFactory
{

    /**
     * <p>
     * The bean definition ID in the mule registry
     * </p>
     */
    public static String ID = "__munitConnectorInterceptorFactory";

    /**
     * <p>
     * Util method that creates a @see #BeanFactoryMethodBuilder based on an abstract bean definition
     * </p>
     * <p/>
     * <p>
     * The usage:
     * </p>
     * <p/>
     * <code>
     * addFactoryDefinitionTo(beanDefinition).withConstructorArguments(beanDefinition.getBeanClass());
     * </code>
     *
     * @param beanDefinition <p>
     *                       The bean definition that we want to modify
     *                       </p>
     * @return <p>
     *         The {@link BeanFactoryMethodBuilder} that will do the job of adding constructor params to the bean definition
     *         </p>
     */
    public static BeanFactoryMethodBuilder addFactoryDefinitionTo(AbstractBeanDefinition beanDefinition)
    {
        return new BeanFactoryMethodBuilder(beanDefinition, "create", ID);
    }

    /**
     * <p>
     * Actual implementation of the interceptor creation
     * </p>
     *
     * @return <p>
     *         A {@link ConnectorMethodInterceptor} object
     *         </p>
     */
    @Override
    protected MethodInterceptor createInterceptor()
    {
        return new ConnectorMethodInterceptor();
    }
}
