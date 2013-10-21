/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.connectors;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class ConnectorMethodInterceptorFactoryTest
{

    @Test
    public void checkCorrectId()
    {
        assertEquals("__munitConnectorInterceptorFactory", ConnectorMethodInterceptorFactory.ID);
    }

    @Test
    public void checkCorrectFactoryNames()
    {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        ConnectorMethodInterceptorFactory.addFactoryDefinitionTo(beanDefinition);

        assertEquals("create", beanDefinition.getFactoryMethodName());
        assertEquals(ConnectorMethodInterceptorFactory.ID, beanDefinition.getFactoryBeanName());
    }

    @Test
    public void testCreateInterceptor()
    {
        assertTrue(new ConnectorMethodInterceptorFactory().createInterceptor() instanceof ConnectorMethodInterceptor);
    }

}
