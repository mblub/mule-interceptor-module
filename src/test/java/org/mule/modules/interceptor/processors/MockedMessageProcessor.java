/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.processors;

import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.construct.FlowConstructAware;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.processor.MessageProcessor;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class MockedMessageProcessor implements MessageProcessor, Startable, Initialisable, Disposable, MuleContextAware, FlowConstructAware, Stoppable
{

    boolean calledDispose;
    boolean calledStart;
    boolean calledStop;
    boolean calledsetFlowConstruct;
    boolean calledInitialise;
    boolean calledSetMuleContext;

    @Override
    public void dispose()
    {
        calledDispose = true;
    }

    @Override
    public MuleEvent process(MuleEvent event) throws MuleException
    {
        return null;
    }

    @Override
    public void start() throws MuleException
    {
        this.calledStart = true;
    }

    @Override
    public void stop() throws MuleException
    {
        this.calledStop = true;
    }

    @Override
    public void setFlowConstruct(FlowConstruct flowConstruct)
    {
        this.calledsetFlowConstruct = true;
    }

    @Override
    public void initialise() throws InitialisationException
    {
        this.calledInitialise = true;
    }

    @Override
    public void setMuleContext(MuleContext context)
    {
        this.calledSetMuleContext = true;
    }
}
