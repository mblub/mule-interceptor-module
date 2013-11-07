/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.processors;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * The Class that manages the mocking process. Gets the behaviors, stores the message processor calls and stores
 * the spy process
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MessageProcessorManager
{

    public static String ID = "_muleMpManager";

    /**
     * <p>
     * These are the behaviors expected for different message processor mocks
     * </p>
     */
    protected List<MessageProcessorBehavior> behaviors = new ArrayList<MessageProcessorBehavior>();


    /**
     * <p>
     * Reset all the status
     * </p>
     */
    public void reset()
    {
        behaviors.clear();
    }

    /**
     * <p>
     * Gets the best matching Behavior. The best matching behavior is the one that mostly matches the attributes
     * </p>
     *
     * @param messageProcessorCall The comparing call
     * @return The best matching behavior
     */
    public MessageProcessorBehavior getBetterMatchingBehavior(MessageProcessorCall messageProcessorCall)
    {
        return getBetterMatchingAction(messageProcessorCall, behaviors);
    }


    protected <T extends MessageProcessorCallAction> T getBetterMatchingAction(MessageProcessorCall messageProcessorCall,
                                                                               Collection<T> actions)
    {
        Map.Entry<Integer, T> bestMatchingBehavior = new AbstractMap.SimpleEntry<Integer, T>(0, null);
        for (T behavior : actions)
        {
            int matchingWeight = behavior.getMessageProcessorCall().matchingWeight(messageProcessorCall);
            if (matchingWeight >= 0 && matchingWeight >= bestMatchingBehavior.getKey())
            {
                bestMatchingBehavior.setValue(behavior);
            }
        }

        return bestMatchingBehavior.getValue();
    }

    public synchronized void addBehavior(MessageProcessorBehavior behavior)
    {
        behaviors.add(behavior);
    }


}
