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
 * @since  3.3.2
 */
public class MessageProcessorBehavior extends MessageProcessorCallAction
{

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
        super(messageProcessorCall);
        this.returnMuleMessage = returnMuleMessage;
    }

    public MessageProcessorBehavior(MessageProcessorCall messageProcessorCall, Throwable exceptionToThrow)
    {
        super(messageProcessorCall);
        this.exceptionToThrow = exceptionToThrow;
    }

    public MuleMessage getReturnMuleMessage()
    {
        return returnMuleMessage;
    }



    public Throwable getExceptionToThrow()
    {
        return exceptionToThrow;
    }
}
