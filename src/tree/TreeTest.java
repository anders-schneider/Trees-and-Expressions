package tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class TreeTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public final void testHashCode() {
    	Tree<Integer> t1 = new Tree<Integer>(1);
    	Tree<Integer> t2 = new Tree<Integer>(2);
    	Tree<Integer> t3 = new Tree<Integer>(3);
    	Tree<Integer> t4 = new Tree<Integer>(4);
    	
    	t1.addChild(t2);
    	t1.addChild(t3);
    	t2.addChild(t4);
    	
    	assertEquals(1684627318, t1.hashCode());
    	
    	Tree<Integer> s1 = new Tree<Integer>(1);
    	Tree<Integer> s2 = new Tree<Integer>(2);
    	Tree<Integer> s3 = new Tree<Integer>(3);
    	Tree<Integer> s4 = new Tree<Integer>(4);
    	
    	s1.addChild(s2);
    	s1.addChild(s3);
    	s1.addChild(s4);
    	
    	assertEquals(-127595902, s1.hashCode());
    	
    	assertNotEquals(s1.hashCode(), t1.hashCode());
    }

    @Test
    public final void testTree() {
    	Tree<String> t1 = new Tree<String>("A");
    	Tree<String> t2 = new Tree<String>("B");
    	Tree<String> t3 = new Tree<String>("C");
    	Tree<String> t4 = new Tree<String>("D");
    	Tree<String> t5 = new Tree<String>("E");
    	
    	assertTrue(t1.getValue().equals("A"));
    	
    	Tree<String> tree1 = new Tree<String>("F", t2);
    	
    	assertTrue(tree1.getValue().equals("F"));
    	assertEquals(tree1.getNumberOfChildren(), 1);
    	
    	Tree<String> tree2 = new Tree<String>("G", t3, t4, t5);
    	
    	assertTrue(tree2.getValue().equals("G"));
    	assertEquals(tree2.getNumberOfChildren(), 3);
    }

    @Test
    public final void testSetValue() {
    	Tree<Boolean> t1 = new Tree<Boolean>(true);
    	
    	assertTrue(t1.getValue());
    	t1.setValue(false);
    	assertFalse(t1.getValue());
    	
    	Tree<Integer> t2 = new Tree<Integer>(3);
    	
    	assertTrue(t2.getValue() == 3);
    	t2.setValue(100);
    	assertTrue(t2.getValue() == 100);
    }

    @Test
    public final void testGetValue() {
    	Tree<Boolean> t1 = new Tree<Boolean>(true);
    	
    	assertTrue(t1.getValue());
    	t1.setValue(false);
    	assertFalse(t1.getValue());
    	
    	Tree<Integer> t2 = new Tree<Integer>(3);
    	
    	assertTrue(t2.getValue() == 3);
    	t2.setValue(100);
    	assertTrue(t2.getValue() == 100);
    }

    @Test
    public final void testAddChildIntTreeOfV() {
    	Tree<Character> t1 = new Tree<Character>('a');
    	Tree<Character> t2 = new Tree<Character>('b');
    	Tree<Character> t3 = new Tree<Character>('c');
    	
    	Tree<Character> bigTree = new Tree<Character>('d');
    	
    	bigTree.addChild(t1);
    	bigTree.addChild(t2);
    	bigTree.addChild(0, t3);
    	
    	assertTrue(bigTree.getChild(0).getValue() == 'c');
    	assertTrue(bigTree.getChild(1).getValue() == 'a');
    	assertTrue(bigTree.getChild(2).getValue() == 'b');
    }

    @Test
    public final void testAddChildTreeOfV() {
    	Tree<Character> t1 = new Tree<Character>('a');
    	Tree<Character> t2 = new Tree<Character>('b');
    	Tree<Character> t3 = new Tree<Character>('c');
    	
    	Tree<Character> bigTree = new Tree<Character>('d');
    	
    	bigTree.addChild(t1);
    	bigTree.addChild(t2);
    	bigTree.addChild(t3);
    	
    	assertTrue(bigTree.getChild(0).getValue() == 'a');
    	assertTrue(bigTree.getChild(1).getValue() == 'b');
    	assertTrue(bigTree.getChild(2).getValue() == 'c');
    }

    @Test
    public final void testAddChildren() {
    	Tree<Character> t1 = new Tree<Character>('a');
    	Tree<Character> t2 = new Tree<Character>('b');
    	Tree<Character> t3 = new Tree<Character>('c');
    	
    	Tree<Character> bigTree = new Tree<Character>('d');
    	
    	bigTree.addChildren(t1, t2, t3);
    	
    	assertTrue(bigTree.getValue() == 'd');
    	
    	assertTrue(bigTree.getChild(0).getValue() == 'a');
    	assertTrue(bigTree.getChild(1).getValue() == 'b');
    	assertTrue(bigTree.getChild(2).getValue() == 'c');
    }

    @Test
    public final void testGetNumberOfChildren() {
    	Tree<Integer> t1 = new Tree<Integer>(1);
    	Tree<Integer> t2 = new Tree<Integer>(2);
    	Tree<Integer> t3 = new Tree<Integer>(3);
    	Tree<Integer> t4 = new Tree<Integer>(4);
    	Tree<Integer> t5 = new Tree<Integer>(5);
    	
    	assertEquals(0, t5.getNumberOfChildren());
    	
    	t5.addChild(t1);
    	
    	assertEquals(1, t5.getNumberOfChildren());
    	
    	t5.addChildren(t2, t3, t4);
    	
    	assertEquals(4, t5.getNumberOfChildren());
    }

    @Test
    public final void testGetChild() {
    	Tree<Integer> t1 = new Tree<Integer>(1);
    	Tree<Integer> t2 = new Tree<Integer>(2);
    	Tree<Integer> t3 = new Tree<Integer>(3);
    	Tree<Integer> t4 = new Tree<Integer>(4);
    	Tree<Integer> t5 = new Tree<Integer>(5);
    	
    	t5.addChildren(t1);
    	t1.addChildren(t2, t3, t4);
    	
    	assertTrue(t5.getChild(0).getValue() == 1);
    	assertTrue(t1.getChild(2).getValue() == 4);
    }

    @Test
    public final void testIterator() {
    	Tree<Integer> t1 = new Tree<Integer>(1);
    	Tree<Integer> t2 = new Tree<Integer>(2);
    	Tree<Integer> t3 = new Tree<Integer>(3);
    	Tree<Integer> t4 = new Tree<Integer>(4);
    	Tree<Integer> t5 = new Tree<Integer>(5);
    	
    	t1.addChildren(t2, t3, t4, t5);
    	
    	Iterator<Tree<Integer>> iter = t1.iterator();
    	
    	int i = 2;
    	while (iter.hasNext()) {
    		assertTrue(iter.next().getValue() == i);
    		i++;
    	}
    }

    @Test
    public final void testContains() {
    	Tree<String> t1 = new Tree<String>("A");
    	Tree<String> t2 = new Tree<String>("B");
    	Tree<String> t3 = new Tree<String>("C");
    	Tree<String> t4 = new Tree<String>("D");
    	Tree<String> t5 = new Tree<String>("E");
    	
    	t4.addChild(t5);
    	t2.addChildren(t3, t4);
    	t1.addChild(t2);
    	
    	assertTrue(t1.contains(t2));
    	assertTrue(t1.contains(t5));
    	assertFalse(t2.contains(t1));
    	assertFalse(t5.contains(t4));
    }

    @Test
    public final void testToString() {
    	Tree<String> t1 = new Tree<String>("one");
    	Tree<String> t2 = new Tree<String>("two");
    	Tree<String> t3 = new Tree<String>("three");
    	Tree<String> t4 = new Tree<String>("four");
    	Tree<String> t5 = new Tree<String>("five");
    	Tree<String> t6 = new Tree<String>("six");
    	Tree<String> t7 = new Tree<String>("seven");
    	Tree<String> t8 = new Tree<String>("eight");
    	
    	t5.addChildren(t6, t7, t8);
    	t3.addChildren(t4, t5);
    	t1.addChildren(t2, t3);

    	assertTrue(t1.toString().equals("one (two three (four five (six seven eight)))"));
    	assertTrue(t8.toString().equals("eight"));
    	assertTrue(t5.toString().equals("five (six seven eight)"));
    }

    @Test
    public final void testEqualsObject() {
    	Tree<String> t1 = new Tree<String>("one");
    	Tree<String> t2 = new Tree<String>("two");
    	Tree<String> t3 = new Tree<String>("three");
    	Tree<String> t4 = new Tree<String>("four");
    	Tree<String> t5 = new Tree<String>("five");
    	Tree<String> t6 = new Tree<String>("six");
    	Tree<String> t7 = new Tree<String>("seven");
    	Tree<String> t8 = new Tree<String>("eight");
    	
    	t5.addChildren(t6, t7, t8);
    	t3.addChildren(t4, t5);
    	t1.addChildren(t2, t3);
    	
    	Tree<String> s1 = new Tree<String>("one");
    	Tree<String> s2 = new Tree<String>("two");
    	Tree<String> s3 = new Tree<String>("three");
    	Tree<String> s4 = new Tree<String>("four");
    	Tree<String> s5 = new Tree<String>("five");
    	Tree<String> s6 = new Tree<String>("six");
    	Tree<String> s7 = new Tree<String>("seven");
    	Tree<String> s8 = new Tree<String>("eight");
    	
    	s5.addChildren(s6, s7, s8);
    	s3.addChildren(s4, s5);
    	s1.addChildren(s2, s3);
    	
    	assertTrue(t1.equals(s1));
    	assertTrue(s3.equals(t3));
    	
    	Tree<String> u1 = new Tree<String>("one");
    	Tree<String> u2 = new Tree<String>("two");
    	Tree<String> u3 = new Tree<String>("three");
    	Tree<String> u4 = new Tree<String>("four");
    	Tree<String> u5 = new Tree<String>("five");
    	Tree<String> u6 = new Tree<String>("six");
    	Tree<String> u7 = new Tree<String>("seven");
    	Tree<String> u8 = new Tree<String>("eight");
    	
    	u1.addChildren(u2, u3, u4);
    	u2.addChildren(u5, u6);
    	u3.addChildren(u7, u8);
    	
    	assertFalse(t1.equals(u1));
    	assertTrue(t8.equals(u8));
    	
    	assertFalse(t1.equals(7));
    }

    @Test
    public final void testParseString() {
    	String input = "one (two (three))";
    	
    	Tree<String> tree = Tree.parse(input);
    	
    	System.out.println(tree.toString());
    	System.out.println(tree.getNumberOfChildren());
    }

}
