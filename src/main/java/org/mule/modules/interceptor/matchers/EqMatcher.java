/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers;

/**
 * <p>
 * Equality matcher. It compares using the equal method of Object
 * </p>
 * <p/>
 * Usage: <code>new EqMatcher("desired").match("desired");</code> will return true.
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class EqMatcher implements Matcher
{

    /**
     * <p>The expected object</p>
     */
    private Object expected;

    public EqMatcher(Object expected)
    {
        this.expected = expected;
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
        return o.equals(expected);
    }
}
