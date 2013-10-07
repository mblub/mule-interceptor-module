package org.mule.modules.interceptor.processors;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang.StringUtils;
import org.mule.api.MuleEvent;
import org.mule.api.expression.ExpressionManager;
import org.mule.api.processor.MessageProcessor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     Abstract class to support intercepting message processors.
 * </p>
 *
 * <p>
 *     It intercept the {@link MessageProcessor#process(org.mule.api.MuleEvent)}  calls
 * </p>
 *
 * @author Federico, Fernando
 * @version since 3.3.2
 */
public abstract class AbstractMessageProcessorInterceptor  implements MethodInterceptor {

    protected MessageProcessorId id;
    protected Map<String,String> attributes;

    protected String methodName;

    public AbstractMessageProcessorInterceptor(String methodName)
    {
        this.methodName = methodName;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();
        if ( MessageProcessor.class.isAssignableFrom(declaringClass) && method.getName().equals(methodName) )
        {
            return process(obj,args,proxy);
        }

        return proxy.invokeSuper(obj, args);
    }

    protected abstract Object process(Object obj, Object[] args, MethodProxy proxy) throws Throwable;

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    protected Map<String, Object> getAttributes(MuleEvent event) {
        Map<String, Object> processed = new HashMap<String, Object>();
        for (Map.Entry<String,String> attrs : attributes.entrySet() ){
            try{
                Object evaluate = evaluate(attrs.getValue(), event);
                processed.put(attrs.getKey(), evaluate);
            }catch (Throwable t){
                processed.put(attrs.getKey(), attrs.getValue());
            }
        }
        return processed;
    }

    private Object evaluate(String elementValue, MuleEvent event) {
        Object compareTo = elementValue;
        ExpressionManager expressionManager = event.getMuleContext().getExpressionManager();
        if ( expressionManager.isExpression(elementValue)){
            compareTo = expressionManager.parse(elementValue, event);
        }
        else if (!StringUtils.isEmpty(elementValue) ){
            Object o = event.getMuleContext().getRegistry().lookupObject(elementValue);
            if ( o != null ){
                compareTo = o;
            }
        }
        return compareTo;
    }

    public void setId(MessageProcessorId id) {
        this.id = id;
    }
}
