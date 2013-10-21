/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.processors;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.mule.api.construct.FlowConstruct;
import org.mule.modules.interceptor.matchers.EqMatcher;

import java.util.HashMap;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class MessageProcessorCallTest
{

    public static final HashMap<String, Object> ATTRIBUTES = new HashMap<String, Object>();
    public static final String TEST_NAME = "testName";
    public static final String TEST_NAMESPACE = "testNamespace";
    public static final MessageProcessorId MESSAGE_PROCESSOR_ID = new MessageProcessorId(TEST_NAME, TEST_NAMESPACE);

    @Test
    public void gettersReturnOriginalValue()
    {
        MessageProcessorCall call = new MessageProcessorCall(MESSAGE_PROCESSOR_ID);
        call.setAttributes(ATTRIBUTES);

        assertEquals(MESSAGE_PROCESSOR_ID, call.getMessageProcessorId());
        assertEquals(ATTRIBUTES, call.getAttributes());
    }

    @Test
    public void attributesMustNeverBeNull()
    {
        MessageProcessorCall call = new MessageProcessorCall(MESSAGE_PROCESSOR_ID);

        assertNotNull(call.getAttributes());
    }

    @Test
    public void returnTrueCallOf()
    {
        MessageProcessorCall call = new MessageProcessorCall(MESSAGE_PROCESSOR_ID);

        assertTrue(call.isCallOf(MESSAGE_PROCESSOR_ID));
    }


    @Test
    public void ifSameNameButDifferentAttributeReturnNegative()
    {
        MessageProcessorCall call = createMatcherCall();

        MessageProcessorCall call2 = createRealCall("attr", "attrValue2");

        assertEquals(-1, call.matchingWeight(call2));
    }

    @Test
    public void ifSameNameAndSameAttributeReturnWeight()
    {
        MessageProcessorCall call = createMatcherCall();
        MessageProcessorCall call2 = createRealCall("attr", "attrValue");

        assertEquals(1, call.matchingWeight(call2));
    }

    @Test
    public void ifSameNameAndSameAttributeReturnWeightWhenNoMatcher()
    {
        MessageProcessorCall call = createMatcherEqualsCall();
        MessageProcessorCall call2 = createRealCall("attr", "attrValue");

        assertEquals(1, call.matchingWeight(call2));
    }

    @Test
    public void ifSameNameAndNoMatchingAttributeReturnZero()
    {
        MessageProcessorCall call = new MessageProcessorCall(MESSAGE_PROCESSOR_ID);
        MessageProcessorCall call2 = createRealCall("attr", "attrValue");

        assertEquals(0, call.matchingWeight(call2));
    }

    @Test
    public void ifDifferentNameThenReturnNegative()
    {
        MessageProcessorCall call = new MessageProcessorCall(new MessageProcessorId("another", "another"));
        MessageProcessorCall call2 = createRealCall("attr", "attrValue");

        assertEquals(-1, call.matchingWeight(call2));
    }

    @Test
    public void allowSettingFlowConstruct()
    {
        FlowConstruct flow = mock(FlowConstruct.class);
        MessageProcessorCall call = new MessageProcessorCall(new MessageProcessorId("another", "another"));
        call.setFlowConstruct(flow);

        assertEquals(flow, call.getFlowConstruct());
    }


    private MessageProcessorCall createRealCall(String attr, String attrValue)
    {
        MessageProcessorCall call = new MessageProcessorCall(MESSAGE_PROCESSOR_ID);
        HashMap<String, Object> attributes = new HashMap<String, Object>();

        attributes.put(attr, attrValue);
        call.setAttributes(attributes);
        return call;
    }

    private MessageProcessorCall createMatcherCall()
    {
        MessageProcessorCall call = new MessageProcessorCall(MESSAGE_PROCESSOR_ID);
        HashMap<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("attr", new EqMatcher("attrValue"));
        call.setAttributes(attributes);
        return call;
    }

    private MessageProcessorCall createMatcherEqualsCall()
    {
        MessageProcessorCall call = new MessageProcessorCall(MESSAGE_PROCESSOR_ID);
        HashMap<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("attr", "attrValue");
        call.setAttributes(attributes);
        return call;
    }


}
