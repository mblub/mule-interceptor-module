/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers.mel;

import static junit.framework.Assert.assertTrue;
import org.mule.modules.interceptor.matchers.Matcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class AnyMatcherFunctionTest
{

    @Test
    public void testAnyBoolean()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(Boolean.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match(Boolean.FALSE));
    }

    @Test
    public void testAnyString()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(String.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match("string"));
    }

    @Test
    public void testAnyByte()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(Byte.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match(Byte.MIN_VALUE));
    }

    @Test
    public void testAnyInt()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(Integer.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match(9));
    }

    @Test
    public void testAnyDouble()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(Double.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match(new Double(0)));
    }

    @Test
    public void testAnyFloat()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(Float.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match(new Float(0)));
    }

    @Test
    public void testAnyShort()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(Short.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match(Short.MIN_VALUE));
    }

    @Test
    public void testAnyObject()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(Object.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match(""));
    }


    @Test
    public void testAnyCollection()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(Collection.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match(new ArrayList<String>()));
    }

    @Test
    public void testAnyList()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(List.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match(new ArrayList<String>()));
    }

    @Test
    public void testAnyMap()
    {
        AnyMatcherFunction f = new AnyMatcherFunction(Map.class);
        Matcher m = (Matcher) f.call(null, null);

        assertTrue(m.match(new HashMap<String, Object>()));
    }
}
