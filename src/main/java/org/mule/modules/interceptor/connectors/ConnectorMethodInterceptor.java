/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.connectors;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * <p>
 * Every time a method of a @see #org.mule.api.transport.Connector implementation is called this class is called. @see{MethodInterceptor}
 * </p>
 * <p/>
 * <p>
 * This class only acts when the method is connect
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class ConnectorMethodInterceptor implements MethodInterceptor
{

    /**
     * <p>
     * The method of the <code>Connector</code> implementation that is wanted to be mocked
     * </p>
     */
    private static final String METHOD_TO_INTERCEPT = "connect";

    /**
     * @see net.sf.cglib.proxy.MethodInterceptor#intercept(Object, java.lang.reflect.Method, Object[], net.sf.cglib.proxy.MethodProxy)
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable
    {
        String methodName = method.getName();
        if (methodName.equals(METHOD_TO_INTERCEPT))
        {
            return null;
        }
        return proxy.invokeSuper(obj, args);
    }
}
