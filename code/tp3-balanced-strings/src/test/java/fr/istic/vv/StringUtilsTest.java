package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class StringUtilsTest {

	 @Test
	    public void testEmptyString() {
	        assertTrue(StringUtils.isBalanced(""));
	    }

	    @Test
	    public void testSingleInvalidCharacters() {
	        assertFalse(StringUtils.isBalanced("("));
	        assertFalse(StringUtils.isBalanced("]"));
	        assertFalse(StringUtils.isBalanced("{"));
	    }

	    @Test
	    public void testCorrectlyNestedStringUtils() {
	        assertTrue(StringUtils.isBalanced("{}"));
	        assertTrue(StringUtils.isBalanced("{[]}"));
	        assertTrue(StringUtils.isBalanced("{[()]}"));
	    }

	    @Test
	    public void testIncorrectlyNestedOrUnmatched() {
	        assertFalse(StringUtils.isBalanced("]{"));
	        assertFalse(StringUtils.isBalanced("[(])"));
	        assertFalse(StringUtils.isBalanced("{(}]"));
	    }

	    @Test
	    public void testRandomStrings() {
	        assertTrue(StringUtils.isBalanced("a(b)c"));
	        assertTrue(StringUtils.isBalanced("foo{bar}"));
	        assertFalse(StringUtils.isBalanced("{foo[bar]}]"));
	    }
}
