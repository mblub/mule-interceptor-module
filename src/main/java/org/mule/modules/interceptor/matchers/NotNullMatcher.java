/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers;

/**
 * <p>
 * Matcher to check not null
 * </p>
 * <p/>
 * Usage: <code>new NotNullMatcher().match(null)</code> will return false
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class NotNullMatcher implements Matcher
{

    /**
     * @see Matcher#match(Object)
     */
    @Override
    public boolean match(Object o)
    {
        return o != null;
    }
}
