/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.connectors;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mule.api.transport.Connector;

import net.sf.cglib.proxy.MethodProxy;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class ConnectorMethodInterceptorTest
{

    MethodProxy proxy;

    @Before
    public void setUp()
    {
        proxy = mock(MethodProxy.class);
    }

    @Test
    public void whenConnectReturnNull() throws Throwable
    {

        ConnectorMethodInterceptor connectorMethodInterceptor = new ConnectorMethodInterceptor();

        Object interceptedResult = connectorMethodInterceptor.intercept(null, Connector.class.getMethod("connect"), null, proxy);


        verify(proxy, never()).invokeSuper(any(Object.class), any(Object[].class));

        assertNull(interceptedResult);
    }

    @Test
    public void whenNotConnectThenCallSuper() throws Throwable
    {
        ConnectorMethodInterceptor connectorMethodInterceptor = new ConnectorMethodInterceptor();
        connectorMethodInterceptor.intercept(null, Connector.class.getMethod("isConnected"), null, proxy);

        verify(proxy, times(1)).invokeSuper(any(Object.class), any(Object[].class));

    }


}
