/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.spring;

import java.lang.reflect.Constructor;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * <p>
 * Abstract definition that creates an interceptor
 * </p>
 *
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public abstract class MethodInterceptorFactory
{

    /**
     * <p>
     * The factory method to create connector/message processors beans
     * </p>
     *
     * @param realMpClass <p>
     *                    The class of the message processor/ connector
     *                    </p>
     * @param objects     <p>
     *                    Constructor arguments
     *                    </p>
     * @return <p>
     *         An {@link net.sf.cglib.proxy.Enhancer} of the message processor/connector
     *         </p>
     */
    public Object create(Class realMpClass, Object... objects)
    {
        try
        {

            Enhancer e = new Enhancer();
            e.setSuperclass(realMpClass);

            MethodInterceptor callback = createInterceptor();

            e.setCallback(callback);
            if (objects.length != 0)
            {

                Constructor[] constructors = realMpClass.getConstructors();
                for (Constructor constructor : constructors)
                {
                    Class[] parameterTypes = constructor.getParameterTypes();
                    boolean matchConstructor = parameterTypes.length == objects.length;
                    for (int j = 0; j < parameterTypes.length; j++)
                    {
                        if (j < objects.length)
                        {
                            matchConstructor = matchConstructor && parameterTypes[j].isAssignableFrom(objects[j].getClass());
                        }
                        else
                        {
                            matchConstructor = false;
                        }
                    }

                    if (matchConstructor)
                    {
                        return e.create(constructor.getParameterTypes(), objects);
                    }
                }
                throw new Error("Could not mock the connectors");
            }
            return e.create();

        }
        catch (Throwable e)
        {
            throw new Error("Could not mock the connectors", e);
        }
    }

    protected abstract MethodInterceptor createInterceptor();
}
