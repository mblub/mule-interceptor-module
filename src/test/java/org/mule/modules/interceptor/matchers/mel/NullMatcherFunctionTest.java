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
 * @author Javier Casal
 * @version since 3.3.2
 */
public class NullMatcherFunctionTest
{

    @Test
    public void callWithNull()
    {
        assertTrue(((Matcher) new NullMatcherFunction().call(null, null)).match(null));
    }

    @Test
    public void callWithNotNull()
    {
        assertFalse(((Matcher) new NullMatcherFunction().call(null, null)).match(new Object()));
    }
}
