/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers;

/**
 * <p>
 * Matcher definition
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public interface Matcher
{

    /**
     * <p>
     * Comparing method
     * </p>
     *
     * @param o <p>
     *          The object that is needed to compare
     *          </p>
     * @return <p>
     *         true/false based on the matching result
     *         </p>
     */
    boolean match(Object o);
}
