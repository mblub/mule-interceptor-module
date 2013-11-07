/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.interceptor.processors;

/**
 * <p/>
 * Represents an action that has to be executed based on a {@link MessageProcessorCall}.
 * <p/>
 *
 * @author Mulesoft Inc.
 * @since 3.4.0
 */
public abstract class MessageProcessorCallAction
{
    /**
     * <p>
     * The message processor call representation. When this call is executed then return returnMessage
     * </p>
     */
    protected MessageProcessorCall messageProcessorCall;


    protected MessageProcessorCallAction(MessageProcessorCall messageProcessorCall)
    {
        this.messageProcessorCall = messageProcessorCall;
    }

    public MessageProcessorCall getMessageProcessorCall()
    {
        return messageProcessorCall;
    }
}
