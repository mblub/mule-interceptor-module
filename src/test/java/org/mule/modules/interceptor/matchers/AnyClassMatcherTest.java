package org.mule.modules.interceptor.matchers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Federico, Fernando
 * @version since 3.3.2
 */
public class AnyClassMatcherTest {
    
    @Test
    public void testWithNoNull(){
        AnyClassMatcher anyClassMatcher = new AnyClassMatcher(String.class);

        assertTrue(anyClassMatcher.match("string"));
    }

    @Test
    public void testWithNull(){
        AnyClassMatcher anyClassMatcher = new AnyClassMatcher(String.class);

        assertFalse(anyClassMatcher.match(null));
    }
}
