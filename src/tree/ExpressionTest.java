package tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExpressionTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public final void testExpression() {
    	Expression exp0 = new Expression("* (9 2)");
    	assertEquals(exp0.evaluate(), 18);
    	assertTrue("(9 * 2)".equals(exp0.toString()));
    	
    	Expression exp1 = new Expression("+ (5 10 -( *(15 20) 25) 30)");
    	assertEquals(exp1.evaluate(), 320);
    	assertTrue("(5 + 10 + ((15 * 20) - 25) + 30)".equals(exp1.toString()));
    	
    	try {
    		Expression exp2 = new Expression("5 + 6");
    	} catch (IllegalArgumentException e) {
    		assertTrue("Unexpected expression: +".equals(e.getMessage()));
    	}
    	
    	try {
    		Expression exp3 = new Expression("-(1 2 3)");
    	} catch (IllegalArgumentException e) {
    		assertTrue("Invalid expression: -(1 2 3)".equals(e.getMessage()));
    	}
    	
    	try {
    		Expression exp4 = new Expression("+5");
    	} catch (IllegalArgumentException e) {
    		assertTrue("Invalid expression: +5".equals(e.getMessage()));
    	}
    	
    	try {
    		Expression exp5 = new Expression("-(3)");
    	} catch (IllegalArgumentException e) {
    		assertTrue("Invalid expression: -(3)".equals(e.getMessage()));
    	}
    	
    	try {
    		Expression exp6 = new Expression("-(1 a)");
    	} catch (IllegalArgumentException e) {
    		assertTrue("Invalid expression: -(1 a)".equals(e.getMessage()));
    	}
    }

    @Test
    public final void testEvaluate() {
    	Expression exp1 = new Expression("2");
    	assertEquals(2, exp1.evaluate());
    	
    	Expression exp2 = new Expression("-(10 1)");
    	assertEquals(9, exp2.evaluate());
    	
    	Expression exp3 = new Expression("*(1 2 3 4)");
    	assertEquals(24, exp3.evaluate());
    	
    	Expression exp4 = new Expression("+( *(2 7) 6)");
    	assertEquals(20, exp4.evaluate());
    	
    	Expression exp5 = new Expression("*(1 +(2 3) *(1 1 7) -(10 8))");
    	assertEquals(70, exp5.evaluate());
    }

    @Test
    public final void testToString() {
    	Expression exp1 = new Expression("2");
    	assertTrue("2".equals(exp1.toString()));
    	
    	Expression exp2 = new Expression("-(10 1)");
    	assertTrue("(10 - 1)".equals(exp2.toString()));
    	
    	Expression exp3 = new Expression("*(1 2 3 4)");
    	assertTrue("(1 * 2 * 3 * 4)".equals(exp3.toString()));
    	
    	Expression exp4 = new Expression("+( *(2 7) 6)");
    	assertTrue("((2 * 7) + 6)".equals(exp4.toString()));
    	
    	Expression exp5 = new Expression("*(1 +(2 3) *(1 1 7) -(10 8))");
    	assertTrue("(1 * (2 + 3) * (1 * 1 * 7) * (10 - 8))".equals(exp5.toString()));
    }

}
