package org.mule.modules.interceptor.matchers;

/**
 * <p>
 *     Matcher to check null
 * </p>
 *
 * Usage: <code>new NullMatcher().match(null)</code> will return true
 * @author Federico, Fernando
 * @since 3.3.2
 */
public class NullMatcher implements Matcher{

    /**
     *  @see Matcher#match(Object)
     */
    @Override
    public boolean match(Object o) {
        return o == null;
    }
}