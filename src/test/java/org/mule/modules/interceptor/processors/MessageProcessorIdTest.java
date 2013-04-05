package org.mule.modules.interceptor.processors;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author Federico, Fernando
 * @version since 3.3.2
 */
public class MessageProcessorIdTest {

    public static final String TEST_NAMESPACE = "testNamespace";
    public static final String TEST_NAME = "testName";

    @Test
    public void gettersMustReturnTheOriginalValues(){
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);

        assertEquals(TEST_NAME, messageProcessorId.getName());
        assertEquals(TEST_NAMESPACE, messageProcessorId.getNamespace());
    }
    
    @Test
    public void ifDifferentTypeEqualsFail(){
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        
        assertFalse(messageProcessorId.equals(new Object()));
    }

    @Test
    public void ifDifferentNameThenEqualsFail(){
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        MessageProcessorId messageProcessorId2 = new MessageProcessorId("anotherName", TEST_NAMESPACE);

        assertFalse(messageProcessorId.equals(messageProcessorId2));
    }

    @Test
    public void ifDifferentNamespaceThenEqualsFail(){
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        MessageProcessorId messageProcessorId2 = new MessageProcessorId(TEST_NAME, "anotherNamespace");

        assertFalse(messageProcessorId.equals(messageProcessorId2));
    }

    @Test
    public void ifDifferentNamespaceAndNameThenEqualsFail(){
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        MessageProcessorId messageProcessorId2 = new MessageProcessorId("anotherName", "anotherNamespace");

        assertFalse(messageProcessorId.equals(messageProcessorId2));
    }

    @Test
    public void ifSameNameAndNamespaceThenEqualsTrue(){
        MessageProcessorId messageProcessorId = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);
        MessageProcessorId messageProcessorId2 = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);

        assertTrue(messageProcessorId.equals(messageProcessorId2));
    }

    @Test
    public void testFullName(){
        assertEquals(TEST_NAMESPACE + ":" + TEST_NAME, new MessageProcessorId(TEST_NAME, TEST_NAMESPACE).getFullName());
    }
}
