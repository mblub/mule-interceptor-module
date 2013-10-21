/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers;

/**
 * <p>
 * Matcher to check null
 * </p>
 * <p/>
 * Usage: <code>new NullMatcher().match(null)</code> will return true
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class NullMatcher implements Matcher
{

    /**
     * @see Matcher#match(Object)
     */
    @Override
    public boolean match(Object o)
    {
        return o == null;
    }
}
