/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers.mel;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.mule.modules.interceptor.matchers.Matcher;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class EqMatcherFunctionTest
{


    public static final Object OBJECT = new Object();

    @Test
    public void callWithNull()
    {
        assertFalse(((Matcher) new EqMatcherFunction().call(null, null)).match(null));
    }

    @Test
    public void callWithEmpty()
    {
        assertFalse(((Matcher) new EqMatcherFunction().call(new Object[] {}, null)).match(null));
    }

    @Test
    public void callWithDifferent()
    {
        assertFalse(((Matcher) new EqMatcherFunction().call(new Object[] {new Object()}, null)).match(new Object()));
    }

    @Test
    public void callWithEquals()
    {
        assertTrue(((Matcher) new EqMatcherFunction().call(new Object[] {OBJECT}, null)).match(OBJECT));
    }
}
