/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.processors;

import org.mule.api.MuleMessage;

/**
 * <p>
 * The representation of a Message Processor mocked behavior.
 * We use this in order to know that the Message processor must return.
 * </p>
 *
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class MessageProcessorBehavior
{

    /**
     * <p>
     * The message processor call representation. When this call is executed then return returnMessage
     * </p>
     */
    private MessageProcessorCall messageProcessorCall;

    /**
     * <p>
     * The Mule message information that will be replaced in the flow Message
     * </p>
     */
    private MuleMessage returnMuleMessage;

    /**
     * <p>
     * The exception that has to be thrown when this behavior is executed
     * </p>
     */
    private Throwable exceptionToThrow;

    public MessageProcessorBehavior(MessageProcessorCall messageProcessorCall, MuleMessage returnMuleMessage)
    {
        this.messageProcessorCall = messageProcessorCall;
        this.returnMuleMessage = returnMuleMessage;
    }

    public MessageProcessorBehavior(MessageProcessorCall messageProcessorCall, Throwable exceptionToThrow)
    {
        this.messageProcessorCall = messageProcessorCall;
        this.exceptionToThrow = exceptionToThrow;
    }

    public MuleMessage getReturnMuleMessage()
    {
        return returnMuleMessage;
    }

    public MessageProcessorCall getMessageProcessorCall()
    {
        return messageProcessorCall;
    }

    public Throwable getExceptionToThrow()
    {
        return exceptionToThrow;
    }
}
