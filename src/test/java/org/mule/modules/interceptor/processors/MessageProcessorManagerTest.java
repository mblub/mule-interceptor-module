/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.processors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class MessageProcessorManagerTest
{

    public static final MessageProcessorCall MESSAGE_PROCESSOR_CALL = new MessageProcessorCall(
            new MessageProcessorId("TEST_MP", "TEST_NAMESPACE"));

    public static final MessageProcessorCall MESSAGE_PROCESSOR_CALL_2 = new MessageProcessorCall(
            new MessageProcessorId("TEST_MP2", "TEST_NAMESPACE"));

    @Test
    public void checkId()
    {
        assertEquals("_muleMpManager", MessageProcessorManager.ID);
    }

    @Test
    public void validateThatResetRemovesAllBehaviors()
    {
        MessageProcessorManager manager = new MessageProcessorManager();
        manager.addBehavior(new MessageProcessorBehavior(MESSAGE_PROCESSOR_CALL, new Exception()));

        manager.reset();

        assertTrue(manager.behaviors.isEmpty());
    }

    @Test
    public void getBetterMatchingCall()
    {
        MessageProcessorManager manager = new MessageProcessorManager();
        MessageProcessorBehavior behavior = new MessageProcessorBehavior(MESSAGE_PROCESSOR_CALL, new Exception());
        manager.addBehavior(behavior);
        MessageProcessorBehavior betterMatchingBehavior = manager.getBetterMatchingBehavior(MESSAGE_PROCESSOR_CALL);

        assertEquals(behavior, betterMatchingBehavior);
    }


    @Test
    public void getBetterMatchingCallNotFound()
    {
        MessageProcessorManager manager = new MessageProcessorManager();
        MessageProcessorBehavior behavior = new MessageProcessorBehavior(MESSAGE_PROCESSOR_CALL, new Exception());
        manager.addBehavior(behavior);
        MessageProcessorBehavior betterMatchingBehavior = manager.getBetterMatchingBehavior(MESSAGE_PROCESSOR_CALL_2);


        assertNull(betterMatchingBehavior);
    }


}
