/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.processors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.expression.ExpressionManager;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.MuleRegistry;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class AbstractMessageProcessorInterceptorTest
{

    private MuleEvent event = mock(MuleEvent.class);
    private MuleContext context = mock(MuleContext.class);
    private ExpressionManager expressionManager = mock(ExpressionManager.class);
    private MuleRegistry registry = mock(MuleRegistry.class);

    @Test
    public void ifNotMessageProcessorThenDoNotMock() throws Throwable
    {
        Object returnValue = new Object();
        MethodProxy proxy = mock(MethodProxy.class);
        AnyClass obj = new AnyClass();
        Object[] args = new Object[0];
        new MessageProcessorInterceptor(returnValue).intercept(obj, AnyClass.class.getMethod("getName"), args, proxy);

        verify(proxy, times(1)).invokeSuper(obj, args);
    }


    @Test
    public void ifMessageProcessorThenReturnExpected() throws Throwable
    {
        Object returnValue = new Object();
        MethodProxy proxy = mock(MethodProxy.class);
        AnyClass obj = new AnyClass();
        Object[] args = new Object[0];
        Object processed = new MessageProcessorInterceptor(returnValue).intercept(obj, MessageProcessor.class.getMethod("process", MuleEvent.class), args, proxy);

        verify(proxy, never()).invokeSuper(obj, args);
        assertEquals(returnValue, processed);
    }


    @Test
    public void evaluateAttributes()
    {
        when(event.getMuleContext()).thenReturn(context);
        when(context.getExpressionManager()).thenReturn(expressionManager);
        when(expressionManager.isExpression(anyString())).thenReturn(false);
        when(context.getRegistry()).thenReturn(registry);

        Object returnValue = new Object();
        MessageProcessorInterceptor mpInterceptor = new MessageProcessorInterceptor(returnValue);
        HashMap<String, String> attributes = new HashMap<String, String>();
        attributes.put("key", "value");
        mpInterceptor.setAttributes(attributes);

        Map<String,Object> attrs = mpInterceptor.getAttributes(event);

        assertEquals("value", attrs.get("key"));
    }

    @Test
    public void evaluateAttributesWithBeanInRegistry()
    {
        when(event.getMuleContext()).thenReturn(context);
        when(context.getExpressionManager()).thenReturn(expressionManager);
        when(expressionManager.isExpression(anyString())).thenReturn(false);
        when(context.getRegistry()).thenReturn(registry);
        when(registry.lookupObject(anyString())).thenReturn("object");

        Object returnValue = new Object();
        MessageProcessorInterceptor mpInterceptor = new MessageProcessorInterceptor(returnValue);
        HashMap<String, String> attributes = new HashMap<String, String>();
        attributes.put("key", "value");
        mpInterceptor.setAttributes(attributes);

        Map<String,Object> attrs = mpInterceptor.getAttributes(event);

        assertEquals("object", attrs.get("key"));
    }

    @Test
    public void evaluateAttributesWithExpression()
    {
        when(event.getMuleContext()).thenReturn(context);
        when(context.getExpressionManager()).thenReturn(expressionManager);
        when(expressionManager.isExpression(anyString())).thenReturn(true);
        when(expressionManager.parse(anyString(), any(MuleEvent.class))).thenReturn("object");

        Object returnValue = new Object();
        MessageProcessorInterceptor mpInterceptor = new MessageProcessorInterceptor(returnValue);
        HashMap<String, String> attributes = new HashMap<String, String>();
        attributes.put("key", "value");
        mpInterceptor.setAttributes(attributes);

        Map<String, Object> attrs = mpInterceptor.getAttributes(event);

        assertEquals("object", attrs.get("key"));
    }


    @Test
    public void evaluateAttributesWithFailure()
    {
        when(event.getMuleContext()).thenReturn(context);
        when(context.getExpressionManager()).thenReturn(expressionManager);
        when(expressionManager.isExpression(anyString())).thenReturn(false);

        Object returnValue = new Object();
        MessageProcessorInterceptor mpInterceptor = new MessageProcessorInterceptor(returnValue);
        HashMap<String, String> attributes = new HashMap<String, String>();
        attributes.put("key", "value");
        mpInterceptor.setAttributes(attributes);

        Map<String, Object> attrs = mpInterceptor.getAttributes(event);

        assertEquals("value", attrs.get("key"));
    }


    @Test
    public void testSetID()
    {
        MessageProcessorInterceptor interceptor = new MessageProcessorInterceptor(new Object());

        MessageProcessorId id = new MessageProcessorId("name", "nsp");
        interceptor.setId(id);

        assertEquals(id, interceptor.id);
    }

    private static class MessageProcessorInterceptor extends AbstractMessageProcessorInterceptor
    {

        private Object returnValue;

        private MessageProcessorInterceptor(Object returnValue)
        {
            this.returnValue = returnValue;
        }

        @Override
        protected Object process(Object obj, Object[] args, MethodProxy proxy) throws Throwable
        {
            return returnValue;
        }
    }

    private static class AnyClass
    {

        public String getName()
        {
            return "AnyClass";
        }
    }
}
