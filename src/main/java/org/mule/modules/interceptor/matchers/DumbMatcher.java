/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers;

/**
 * <p>
 * Dumb matcher that returns the specified value
 * </p>
 * <p/>
 * Usage: <code>new DumbMatcher(true).match(any)</code> will return always true, no matter <code>any</code> value
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class DumbMatcher implements Matcher
{

    boolean expectedValue;

    public DumbMatcher(boolean expectedValue)
    {
        this.expectedValue = expectedValue;
    }

    /**
     * @see Matcher#match(Object)
     */
    @Override
    public boolean match(Object o)
    {
        return expectedValue;
    }
}
