package tree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for representing simple arithmetic expressions.
 * @author Anders Schneider
 * @version Feb 10, 2015
 */
public class Expression {
    Tree<String> expressionTree;
    
    /**
     * Constructs a Tree<String> representing the given arithmetic expression,
     * then verifies that the newly created Tree is valid as an expression.
     * If the Tree is invalid, throws an IllegalArgumentException.<br>
     * Here are the validity rules:<ul>
     * <li>The value of each node must be one of "+", "-", "*", "/",
     *     or a String representing an unsigned integer.</li>
     * <li>If a node has value "+" or "*", it must have two or more children.</li>
     * <li>If a node has value "-" or "/", it must have exactly two children.</li>
     * <li>If a node contains a numeric string, it must be a leaf.</li></ul>
     * Note that the input parameter uses prefix notation, for example:
     * "+ (5 10 -( *(15 20) 25) 30)"
     * @param expression The String representation of the expression to be constructed.
     */
    public Expression(String expression) {
        expressionTree = Tree.parse(expression);
        if (!valid(expressionTree)) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
    }

    /**
     * Tests whether the given Tree represents a valid Expression.
     * @param tree The input tree.
     * @return <code>true</code> iff the Tree is a valid Expression.
     */
    private boolean valid(Tree<String> tree) {
    	int numChil = tree.getNumberOfChildren();
    	
    	// Make sure number of children is within allowable range for give operator
    	if ("+".equals(tree.getValue()) || "*".equals(tree.getValue())){
    		if (numChil < 2) {return false;}
    	} else if ("/".equals(tree.getValue()) || "-".equals(tree.getValue())) {
    		if (numChil != 2) {return false;}
    	} else {
    		int num;
    		
    		try {
    			num = Integer.parseInt(tree.getValue());
    		} catch (NumberFormatException e) {
    			return false;
    		}
    		
    		if (num < 0) {return false;}
    		if (tree.getNumberOfChildren() > 0) {return false;}
    	}
    	
    	// Check validity of children of this node
    	for (int i = 0; i < numChil; i++) {
    		if (!valid(tree.getChild(i))) {return false;}
    	}
    	
    	return true;
    }
    
    /**
     * Evaluates this Expression.
     * @return The value of this Expression.
     */
    public int evaluate() {
        return evaluate(expressionTree);
    }
    
    /**
     * Evaluates the given Tree, which must represent a valid Expression.
     * @return The value of this Expression.
     */
    private int evaluate(Tree<String> tree) {
    	int result;
    	
    	String val = tree.getValue();
    	Iterator iter = tree.iterator();
    	
    	if ("+".equals(val)) {
    		result = 0;
    		while (iter.hasNext()) {
    			result += evaluate((Tree<String>) iter.next());
    		}
    	} else if ("*".equals(val)) {
    		result = 1;
    		while (iter.hasNext()) {
    			result = result * evaluate((Tree<String>) iter.next());
    		}
    	} else if ("-".equals(val)) {
    		result = evaluate(tree.getChild(0)) - evaluate(tree.getChild(1));
    	} else if ("/".equals(val)) {
    		result = evaluate(tree.getChild(0)) / evaluate(tree.getChild(1));
    	} else {
    		result = Integer.parseInt(val);
    	}
    	return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toString(expressionTree);
    }
    
    private static String toString(Tree<String> tree) {
    	String result;
    	
    	String val = tree.getValue();
    	Iterator iter = tree.iterator();
    	
    	if ("+".equals(val)) {
    		result = "(" + toString((Tree<String>) iter.next());
    		while (iter.hasNext()) {
    			result += " + " + toString((Tree<String>) iter.next());
    		}
    		result += ")";
    	} else if ("*".equals(val)) {
    		result = "(" + toString((Tree<String>) iter.next());
    		while (iter.hasNext()) {
    			result += " * " + toString((Tree<String>) iter.next());
    		}
    		result += ")";
    	} else if ("-".equals(val)) {
    		result = "(" + toString(tree.getChild(0)) + " - " + toString(tree.getChild(1));
    		result += ")";
    	} else if ("/".equals(val)) {
    		result = "(" + toString(tree.getChild(0)) + " / " + toString(tree.getChild(1));
    		result += ")";
    	} else {
    		// Stand-alone numbers don't get parentheses
    		result = val;
    	}
    	
    	return result;
    }
}
