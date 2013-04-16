package org.mule.modules.interceptor.matchers.mel;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.mule.modules.interceptor.matchers.Matcher;

import org.junit.Test;

/**
 * @author Javier Casal
 * @version since 3.3.2
 */
public class NullMatcherFunctionTest {

    @Test
    public void callWithNull(){
        assertTrue(((Matcher) new NullMatcherFunction().call(null, null)).match(null));
    }

    @Test
    public void callWithNotNull(){
        assertFalse(((Matcher) new NullMatcherFunction().call(null, null)).match(new Object()));
    }
}
