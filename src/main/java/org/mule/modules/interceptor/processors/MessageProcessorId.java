/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.processors;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * The representation of a Message Processor Id
 * </p>
 *
 * @author Mulesoft Inc.
 * @version since 3.3.2
 */
public class MessageProcessorId
{

    /**
     * <p>
     * Utility method to extract namespace from full name
     * </p>
     *
     * @param fullName <p>
     *                 For example mule:logger
     *                 </p>
     * @return <p>
     *         For example mule
     *         </p>
     */
    public static String getNamespace(String fullName)
    {
        String[] split = fullName.split(":");
        if (split.length > 1)
        {
            return split[0];
        }

        return "mule";
    }

    /**
     * <p>
     * Utility method to extract name from full name
     * </p>
     *
     * @param fullName <p>
     *                 For example mule:logger
     *                 </p>
     * @return <p>
     *         For example logger
     *         </p>
     */
    public static String getName(String fullName)
    {
        String[] split = fullName.split(":");
        if (split.length > 1)
        {
            return split[1];
        }

        return split[0];
    }


    /**
     * <p>
     * The Name of the message processor. For example, the name of jira:create-group is create-group
     * </p>
     */
    private String name;

    /**
     * <p>
     * The namespace of the message processor. For example, the namespace of jira:create-group is jira
     * </p>
     */
    private String namespace;


    /**
     * <p>
     * A (potentially empty) set of attributes to match against
     * </p>
     */
    private Map<String, Object> attributes;


    public MessageProcessorId(String name, String namespace)
    {
        this.name = name;
        this.namespace = namespace;
        this.attributes = new HashMap<String, Object>();
    }

    public MessageProcessorId(String name, String namespace, Map<String, Object> attributes)
    {
        this.name = name;
        this.namespace = namespace;
        this.attributes = new HashMap<String, Object>(attributes);
    }

    public String getFullName()
    {
        return namespace + ":" + name;
    }

    public String getName()
    {
        return name;
    }

    public String getNamespace()
    {
        return namespace;
    }

    public Map<String,Object> getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof MessageProcessorId)
        {
            MessageProcessorId mpId = (MessageProcessorId) o;

            return mpId.getName().equals(name) && mpId.getNamespace().equals(namespace) && mpId.getAttributes().equals(attributes);
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        return getFullName().hashCode();
    }
}
