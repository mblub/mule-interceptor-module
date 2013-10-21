/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class AnyClassMatcherTest
{

    @Test
    public void testWithNoNull()
    {
        AnyClassMatcher anyClassMatcher = new AnyClassMatcher(String.class);

        assertTrue(anyClassMatcher.match("string"));
    }

    @Test
    public void testWithNull()
    {
        AnyClassMatcher anyClassMatcher = new AnyClassMatcher(String.class);

        assertFalse(anyClassMatcher.match(null));
    }
}
