/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers;


/**
 * <p>
 * Matcher to match a specified class.
 * </p>
 * <p/>
 * <p>
 * Usage ex.: <code>new AnyClassMatcher(String.class).match("hello);</code> Will return true
 * </p>
 * <p>
 * Usage ex.: <code>new AnyClassMatcher(Integer.class).match("hello); </code> Will return false
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AnyClassMatcher implements Matcher
{

    /**
     * <p>
     * The wanted class.
     * </p>
     */
    private Class expectedClass;

    public AnyClassMatcher(Class expectedClass)
    {
        this.expectedClass = expectedClass;
    }

    /**
     * @see Matcher#match(Object)
     */
    @Override
    public boolean match(Object o)
    {
        if (o == null)
        {
            return false;
        }
        return this.expectedClass.isAssignableFrom(o.getClass());
    }
}
