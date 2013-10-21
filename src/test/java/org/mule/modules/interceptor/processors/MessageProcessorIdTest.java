/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.processors;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class MessageProcessorIdTest
{

    public static final String TEST_NAMESPACE = "testNamespace";
    public static final String TEST_NAME = "testName";

    @Test
    public void gettersMustReturnTheOriginalValues()
    {
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);

        assertEquals(TEST_NAME, messageProcessorId.getName());
        assertEquals(TEST_NAMESPACE, messageProcessorId.getNamespace());
    }

    @Test
    public void ifDifferentTypeEqualsFail()
    {
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);

        assertFalse(messageProcessorId.equals(new Object()));
    }

    @Test
    public void ifDifferentNameThenEqualsFail()
    {
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        MessageProcessorId messageProcessorId2 = new MessageProcessorId("anotherName", TEST_NAMESPACE);

        assertFalse(messageProcessorId.equals(messageProcessorId2));
    }

    @Test
    public void ifDifferentNamespaceThenEqualsFail()
    {
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        MessageProcessorId messageProcessorId2 = new MessageProcessorId(TEST_NAME, "anotherNamespace");

        assertFalse(messageProcessorId.equals(messageProcessorId2));
    }

    @Test
    public void ifDifferentNamespaceAndNameThenEqualsFail()
    {
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        MessageProcessorId messageProcessorId2 = new MessageProcessorId("anotherName", "anotherNamespace");

        assertFalse(messageProcessorId.equals(messageProcessorId2));
    }

    @Test
    public void ifSameNameAndNamespaceThenEqualsTrue()
    {
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        MessageProcessorId messageProcessorId2 = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);

        assertTrue(messageProcessorId.equals(messageProcessorId2));
    }

    @Test
    public void testFullName()
    {
        assertEquals(TEST_NAMESPACE + ":" + TEST_NAME, new MessageProcessorId(TEST_NAME, TEST_NAMESPACE).getFullName());
    }

    @Test
    public void getNamespaceFromFullName()
    {
        MessageProcessorId mp = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        assertEquals(TEST_NAMESPACE, MessageProcessorId.getNamespace(mp.getFullName()));
    }

    @Test
    public void getNameFromFullName()
    {
        MessageProcessorId mp = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        assertEquals(TEST_NAME, MessageProcessorId.getName(mp.getFullName()));
    }

    @Test
    public void testHashCode()
    {
        MessageProcessorId mp = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);

        assertEquals(mp.getFullName().hashCode(), mp.hashCode());
    }


    @Test
    public void getMuleNamespaceFromFullName()
    {
        assertEquals("mule", MessageProcessorId.getNamespace(TEST_NAME));
    }

    @Test
    public void getNameFromCoreFullName()
    {
        assertEquals(TEST_NAME, MessageProcessorId.getName(TEST_NAME));
    }
}
