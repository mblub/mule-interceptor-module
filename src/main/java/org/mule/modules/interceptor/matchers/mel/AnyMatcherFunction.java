package org.mule.modules.interceptor.matchers.mel;

import org.mule.api.el.ExpressionLanguageContext;
import org.mule.api.el.ExpressionLanguageFunction;
import org.mule.modules.interceptor.matchers.AnyClassMatcher;


/**
 * <p>
 *     MEL function for the Munit {@link org.mule.modules.interceptor.matchers.AnyClassMatcher} with a defined class
 *
 *  Example:
 *
 *     <pre>
 *         {@code
 *           <mock:verify-call messageProcessor="jira:create-group" atLeast="1">
 *                   <mock:attributes>
 *                           <mock:attribute name="userName" whereValue-ref='#[anyString()]'/>
 *                   </mock:attributes>
 *           </mock:verify-call>
 *         }
 *     </pre>
 * </p>
 * @author Federico, Fernando
 * @since 3.3.2
 */
public class AnyMatcherFunction implements ExpressionLanguageFunction {
    private Class expectedClass;

    public AnyMatcherFunction(Class expectedClass) {
        this.expectedClass = expectedClass;
    }

    @Override
    public Object call(Object[] params, ExpressionLanguageContext context) {
        return new AnyClassMatcher(expectedClass);
    }
}
