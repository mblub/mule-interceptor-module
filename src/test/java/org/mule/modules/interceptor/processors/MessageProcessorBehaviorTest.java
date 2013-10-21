/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.processors;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.mule.api.MuleMessage;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class MessageProcessorBehaviorTest
{

    public static final MessageProcessorCall MESSAGE_PROCESSOR_CALL = new MessageProcessorCall(new MessageProcessorId("test", "any"));
    public static final Exception EXCEPTION = new Exception();
    private MuleMessage muleMessage;

    @Before
    public void createMock()
    {
        muleMessage = mock(MuleMessage.class);
    }

    @Test
    public void gettersMustReturnTheOriginalValue()
    {
        MessageProcessorBehavior behavior = new MessageProcessorBehavior(MESSAGE_PROCESSOR_CALL, muleMessage);

        assertEquals(MESSAGE_PROCESSOR_CALL, behavior.getMessageProcessorCall());
        assertEquals(muleMessage, behavior.getReturnMuleMessage());
    }

    @Test
    public void gettersMustReturnTheOriginalValueWhenExceptionMustBeThrown()
    {
        MessageProcessorBehavior behavior = new MessageProcessorBehavior(MESSAGE_PROCESSOR_CALL, EXCEPTION);

        assertEquals(MESSAGE_PROCESSOR_CALL, behavior.getMessageProcessorCall());
        assertEquals(EXCEPTION, behavior.getExceptionToThrow());
    }


}
