/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.spring;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.mule.modules.interceptor.connectors.ConnectorMethodInterceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class MethodInterceptorFactoryTest
{

    @Test
    public void testCreate()
    {
        MethodInterceptorFactory factory = createFactory();
        TestObject object = (TestObject) factory.create(TestObject.class, "myString", 3);

        assertEquals("myString", object.getString1());
        assertEquals(new Integer(3), object.getInteger1());
    }

    @Test(expected = Error.class)
    public void testFailWhenIncorrectParameters()
    {
        MethodInterceptorFactory factory = createFactory();
        factory.create(TestObject.class, "myString");
    }

    @Test
    public void testWithNoParameters()
    {
        MethodInterceptorFactory factory = createFactory();
        TestObject object = (TestObject) factory.create(TestObject.class);
        assertNull(object.getInteger1());
        assertNull(object.getString1());
    }

    private MethodInterceptorFactory createFactory()
    {
        return new MethodInterceptorFactory()
        {

            @Override
            protected MethodInterceptor createInterceptor()
            {
                return new ConnectorMethodInterceptor();
            }
        };
    }
}
