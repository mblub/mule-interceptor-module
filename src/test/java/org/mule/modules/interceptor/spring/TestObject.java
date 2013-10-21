/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.spring;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class TestObject
{

    private String string1;
    private Integer integer1;

    public TestObject()
    {
    }

    public TestObject(String string1, Integer integer1)
    {
        this.string1 = string1;
        this.integer1 = integer1;
    }

    public String getString1()
    {
        return string1;
    }

    public Integer getInteger1()
    {
        return integer1;
    }
}
