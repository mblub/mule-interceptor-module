/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.modules.interceptor.matchers.mel;

import org.mule.api.el.ExpressionLanguageContext;
import org.mule.api.el.ExpressionLanguageFunction;
import org.mule.modules.interceptor.matchers.AnyClassMatcher;


/**
 * <p>
 * MEL function for the Munit {@link org.mule.modules.interceptor.matchers.AnyClassMatcher} with a defined class
 * <p/>
 * Example:
 * <p/>
 * <pre>
 *         {@code
 *           <mock:verify-call messageProcessor="jira:create-group" atLeast="1">
 *                   <mock:attributes>
 *                           <mock:attribute name="userName" whereValue-ref='#[anyString()]'/>
 *                   </mock:attributes>
 *           </mock:verify-call>
 *         }
 *     </pre>
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AnyMatcherFunction implements ExpressionLanguageFunction
{

    private Class expectedClass;

    public AnyMatcherFunction(Class expectedClass)
    {
        this.expectedClass = expectedClass;
    }

    @Override
    public Object call(Object[] params, ExpressionLanguageContext context)
    {
        return new AnyClassMatcher(expectedClass);
    }
}
