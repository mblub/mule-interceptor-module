package org.mule.modules.interceptor.matchers.mel;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.mule.modules.interceptor.matchers.Matcher;

import org.junit.Test;

/**
 * @author Javier Casal
 * @version since 3.3.2
 */
public class NotNullMatcherFunctionTest {

    @Test
    public void callWithNull(){
        assertFalse(((Matcher) new NotNullMatcherFunction().call(null, null)).match(null));
    }

    @Test
    public void callWithNotNull(){
        assertTrue(((Matcher) new NotNullMatcherFunction().call(null, null)).match(new Object()));
    }


}
