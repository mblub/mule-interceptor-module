/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers.mel;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.mule.modules.interceptor.matchers.Matcher;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class AnyClassMatcherFunctionTest
{

    @Test
    public void testWithBoolean()
    {
        AnyClassMatcherFunction function = new AnyClassMatcherFunction();

        Matcher matcher = (Matcher) function.call(new Class[] {Boolean.class}, null);
        assertTrue(matcher.match(Boolean.FALSE));
    }

    @Test
    public void testWithstring()
    {
        AnyClassMatcherFunction function = new AnyClassMatcherFunction();

        Matcher matcher = (Matcher) function.call(new Class[] {Boolean.class}, null);
        assertFalse(matcher.match(""));
    }

    @Test
    public void testWithNull()
    {
        AnyClassMatcherFunction function = new AnyClassMatcherFunction();

        Matcher matcher = (Matcher) function.call(null, null);
        assertFalse(matcher.match(null));
    }

    @Test
    public void testWitEmpty()
    {
        AnyClassMatcherFunction function = new AnyClassMatcherFunction();

        Matcher matcher = (Matcher) function.call(new Class[] {}, null);
        assertFalse(matcher.match(null));
    }

    @Test
    public void testWitNonClass()
    {
        AnyClassMatcherFunction function = new AnyClassMatcherFunction();

        Matcher matcher = (Matcher) function.call(new Object[] {new Object()}, null);
        assertFalse(matcher.match(null));
    }
}
