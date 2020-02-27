package SPLT_A4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnit_SPLT {
	
	SPLT b;
	boolean val;
	List<String> list = new ArrayList<String>();
	List<String> input = new ArrayList<String>();

	@BeforeEach
	void setUp() throws Exception {
		b = new SPLT();
	}
	
	@Test
	void testSize() {
		// Case 0
		assertEquals(0, b.size());
		
		// Case 1
		b.insert("a");
		b.insert("b");
		assertEquals(2, b.size());
		
		// Case 2
		b = new SPLT();
		b.insert("a");
		b.insert("b");
		b.remove("a");
		assertEquals(1, b.size());
		
		// Case 3
		b = new SPLT();
		b.insert("a");
		b.insert("a");
		assertEquals(1, b.size());
		
	}

	@Test
	void testInsertBasic() {
		assertEquals(0, b.size());
		assertTrue(b.empty());
		assertEquals(-1, b.height());
		
		b.insert(null);
		assertEquals(0, b.size());
		
		b.insert("hello");
		assertEquals(1, b.size());
		assertEquals("hello", b.root.data);
		assertEquals(0, b.height());
		
		b.insert("world");
		assertEquals(2, b.size());
		assertEquals("world", b.root.data);
		
		testSplayInsert("my", true);
		this.testSplayInsert("name", true);
		this.testSplayInsert("hello", false);
		this.testSplayInsert("is", true);
		this.testSplayInsert("blank", true);
		
		b.insert("world");
		assertEquals("world", b.root.data);
	}
	
	@Test
	void testInsertAdvanced() {
		assertEquals(0, b.size());
		assertTrue(b.empty());
		
		// Case 0
		b.insert("c");
		b.insert("b");
		b.insert("a");
		assertEquals(b.getRoot().getData(), "a");
		String[] s1 = {"a", "b", "c"};
		this.inOrderCheck(s1);
		
		// Case 1
		b = new SPLT();
		b.insert("b");
		b.insert("a");
		b.insert("d");
		b.insert("c");
		assertEquals("c", b.getRoot().getData());
		String[] s2 = {"a", "b", "c", "d"};
		this.inOrderCheck(s2);
		
		// Case 2
		b = new SPLT();
		b.insert("b");
		b.insert("a");
		b.insert("d");
		b.insert("c");
		assertEquals("c", b.getRoot().getData());
		String[] s3 = {"a", "b", "c", "d"};
		this.inOrderCheck(s3);
		
		// New 0
		b = new SPLT();
		b.insert("b");
		b.insert("a");
		b.insert("d");
		b.insert("c");
		b.insert("e");
		b.insert("0");
		assertEquals(b.getRoot().getData(), "0");
		String[] s4 = {"0", "a", "b", "c", "d", "e"};
		this.inOrderCheck(s4);
		
		// New 1
		b = new SPLT();
		b.insert("e");
		b.insert("c");
		b.insert("f");
		b.insert("b");
		b.insert("d");
		assertEquals("d", b.getRoot().getData());
		String[] s5 = {"b", "c", "d", "e", "f"};
		this.inOrderCheck(s5);
		
		// New 2
		b = new SPLT();
		b.insert("a");
		b.insert("b");
		b.insert("c");
		b.insert("a");
		assertEquals(b.getRoot().getData(), "a");
		String[] s6 = {"a", "b", "c"};
		this.inOrderCheck(s6);
	}
	
	@Test
	void testContainBasic() { 	// Can only check the element smaller than the first element 
								// or the element larger than the last element
								// without directly accessing the tree's structure
								// because of the uncertainty of the structure of the Splay tree.
		val = b.contains("false");
		assertFalse(val);
		
		val = b.contains(null);
		assertFalse(val);
		
		String[] s = {"hello", "world", "name", "my", "is", "ascii"};
		for (int i = 0; i < s.length; i++) {
			b.insert(s[i]);
		}
		val = b.contains("www");
		assertFalse(val);
		assertEquals("world", b.getRoot().getData());
		
		val = b.contains("abc");
		assertFalse(val);
		assertEquals("ascii", b.getRoot().getData());
		
		val = b.contains("name");
		assertTrue(val);
		assertEquals("name", b.getRoot().getData());
		
		val = b.contains("ascii");
		assertTrue(val);
		assertEquals("ascii", b.getRoot().getData());
		
		this.testSplayContain("world");
		this.testSplayContain("is");
		this.testSplayContain("hello");
	}
	
	@Test
	void testContainsAdvanced() {
		// Case 0
		b = new SPLT();
		b.insert("b");
		b.insert("a");
		b.insert("d");
		b.insert("c");
		b.insert("e");
		assertTrue(b.contains("d"));
		assertEquals("d", b.getRoot().getData());
		
		// Case 1
		b = new SPLT();
		b.insert("b");
		b.insert("a");
		b.insert("d");
		b.insert("c");
		b.insert("e");
		b.remove("d");
		assertFalse(b.contains("d"));
		assertTrue(b.contains("c"));
		assertTrue(b.contains("e"));
		assertEquals("e", b.getRoot().getData());
		
		// New 0
		b = new SPLT();
		b.insert("b");
		b.insert("c");
		assertFalse(b.contains("a"));
		assertEquals("b", b.getRoot().getData());
		
		// New 1
		b = new SPLT();
		b.insert("b");
		b.insert("a");
		b.insert("c");
		b.remove("c");
		assertFalse(b.contains("c"));
		assertEquals("b", b.getRoot().getData());
	}
	
	@Test
	void testRemove() {
		b.remove("false");
		assertTrue(b.empty());
		
		b.insert("boo");
		b.remove("boo");
		assertTrue(b.empty());
		
		String[] s = {"hello", "world", "name", "my", "is", "ascii"};
		for (int i = 0; i < s.length; i++) {
			b.insert(s[i]);
		}
		assertEquals(6, b.size());
		
		b.remove(null);

		b.remove("name");
		assertFalse(b.contains("name"));
		assertEquals(5, b.size());
		
		b.remove("zzz");
		assertTrue(b.contains("world"));
		assertEquals("world", b.getRoot().getData());
		assertEquals(5, b.size());
		
		b.remove("aaa");
		assertEquals("ascii", b.getRoot().getData());
		assertEquals(5, b.size());
		
		val = b.contains("my");
		assertTrue(val);
		b.remove("my");
		assertFalse(b.contains("my"));
		assertEquals(4, b.size());
		
		b.remove("world");
		assertEquals("is", b.getRoot().getData());
		assertEquals(3, b.size());
		
		b.remove("ascii");
		assertEquals("hello", b.getRoot().getData());
		assertEquals(2, b.size());
		
		b.insert("is");
		assertEquals("is", b.getRoot().getData());
		
	}
	
	@Test
	void testRemoveAdvanced() {
		// Case 0
		b = new SPLT();
		input = new ArrayList<String>();
		input.add("a");
		input.add("b");
		for (String s: input) {
			b.insert(s);
		}
		b.remove("a");
		assertEquals("b", b.getRoot().getData());
		String[] s1 = {"b"};
		this.inOrderCheck(s1);
		
		// Case 1
		b = new SPLT();
		input = new ArrayList<String>();
		input.add("b");
		input.add("a");
		input.add("c");
		for (String s: input) {
			b.insert(s);
		}
		b.remove("c");
		assertEquals("b", b.getRoot().getData());
		String[] s2 = {"a", "b"};
		this.inOrderCheck(s2);
		
		// Case 2
		b = new SPLT();
		input = new ArrayList<String>();
		input.add("b");
		input.add("a");
		input.add("d");
		input.add("c");
		input.add("e");
		for (String s: input) {
			b.insert(s);
		}
		b.remove("c");
		assertEquals("b", b.getRoot().getData());
		String[] s3 = {"a", "b", "d", "e"};
		this.inOrderCheck(s3);
		
		// Case 3
		b = new SPLT();
		input = new ArrayList<String>();
		input.add("c");
		input.add("d");
		input.add("b");
		input.add("f");
		input.add("e");
		for (String s: input) {
			b.insert(s);
		}
		b.remove("c");
		assertEquals("b", b.getRoot().getData());
		String[] s4 = {"b", "d", "e", "f"};
		this.inOrderCheck(s4);
		
		// Case 4
		b = new SPLT();
		input = new ArrayList<String>();
		input.add("d");
		input.add("a");
		input.add("c");
		input.add("b");
		input.add("e");
		input.add("f");
		for (String s: input) {
			b.insert(s);
		}
		b.remove("b");
		assertEquals("a", b.getRoot().getData());
		String[] s5 = {"a", "c", "d", "e", "f"};
		this.inOrderCheck(s5);
		
		// Case 5
		b = new SPLT();
		input = new ArrayList<String>();
		input.add("0");
		input.add("c");
		input.add("a");
		input.add("b");
		input.add("e");
		input.add("d");
		for (String s: input) {
			b.insert(s);
		}
		b.remove("c");
		b.remove("a");
		b.remove("d");
		assertEquals("b", b.getRoot().getData());
		String[] s6 = {"0", "b", "e"};
		this.inOrderCheck(s6);
		
		// New 0
		b = new SPLT();
		input = new ArrayList<String>();
		input.add("e");
		input.add("c");
		input.add("f");
		input.add("b");
		input.add("d");
		for (String s: input) {
			b.insert(s);
		}
		b.remove("d");
		assertEquals("c", b.getRoot().getData());
		String[] s7 = {"b", "c", "e", "f"};
		this.inOrderCheck(s7);
		
		// New 1
		b = new SPLT();
		b.insert("c");
		b.insert("b");
		b.insert("a");
		b.remove("0");
		assertEquals(b.getRoot().getData(), "a");
		String[] s8 = {"a", "b", "c"};
		this.inOrderCheck(s8);
	}
	
	@Test
	void testInsertAfterRemove() {
		// Case 0
		b = new SPLT();
		b.insert("a");
		b.remove("a");
		b.insert("b");
		assertEquals("b", b.getRoot().getData());
		String[] s0 = {"b"};
		this.inOrderCheck(s0);
		
		// Case 1
		b = new SPLT();
		b.insert("d");
		b.insert("b");
		b.remove("b");
		b.insert("b");
		b.insert("e");
		assertEquals("e", b.getRoot().getData());
		String[] s1 = {"b", "d", "e"};
		this.inOrderCheck(s1);
		
		// Case 2
		b = new SPLT();
		b.insert("b");
		b.insert("a");
		b.insert("d");
		b.insert("c");
		b.insert("e");
		b.remove("e");
		b.insert("f");
		b.insert("e");
		assertEquals("e", b.getRoot().getData());
		String[] s2 = {"a", "b", "c", "d", "e", "f"};
		this.inOrderCheck(s2);
	}
	
	@Test
	void testFindMinMax() {
		assertEquals(null, b.findMin());
		assertEquals(null, b.findMax());
		String[] s = {"hello", "world", "name", "my", "is", "ascii"};
		for (int i = 0; i < s.length; i++) {
			b.insert(s[i]);
		}
		
		assertEquals("ascii", b.findMin());
		assertEquals("ascii", b.getRoot().getData());
		
		assertEquals("world", b.findMax());
		assertEquals("world", b.getRoot().getData());
		
		val = b.contains("name");
		assertTrue(val);
		assertEquals("name", b.getRoot().getData());
		
		assertEquals("ascii", b.findMin());
		assertEquals("ascii", b.getRoot().getData());
	}
	
	@Test
	void testEmpty() {
		// Case 0
		b = new SPLT();
		assertTrue(b.empty());
		
		// Case 1
		b = new SPLT();
		input = new ArrayList<String>();
		input.add("b");
		input.add("a");
		input.add("d");
		input.add("c");
		input.add("e");
		for (String s: input) {
			b.insert(s);
		}
		for (String s: input) {
			b.remove(s);
		}
		assertTrue(b.empty());
		
		// New 0
		b = new SPLT();
		b.insert("a");
		b.insert("b");
		b.remove("a");
		assertFalse(b.empty());
	}
	
	@Test
	void testHeight() {
		// Case 0
		b = new SPLT();
		b.insert("b");
		b.insert("a");
		assertEquals(b.getRoot().getHeight(), 1);
	}
	
	void inOrderCheck(String[] s) {
		this.list = new ArrayList<String>();
		this.inOrderTraversal(b.root);
		assertEquals(s.length, list.size());
		for (int i = 0; i < s.length; i++) {
			assertEquals(s[i], list.get(i));
		}
	}
	
	void inOrderTraversal(BST_Node b) {
		if (b == null) return;
		inOrderTraversal(b.left);
		this.list.add(b.data);
		inOrderTraversal(b.right);
	}
	
	void testSplayContain(String s) { 	// Only works for data contained in the Splay Tree
										// Cannot automatically check if the root is "the last node accessed prior to reaching NULL pointer"
										// Because of the uncertainty in the tree's structure
		val = b.contains(s);
		assertTrue(val);
		assertEquals(s, b.getRoot().getData());
	}
	
	void testSplayInsert(String s, boolean expected_val) {
		int size = b.size();
		b.insert(s);
		if (expected_val) {
			assertEquals(size + 1, b.size());
		} else {
			assertEquals(size, b.size());
		}
		assertEquals(s, b.getRoot().getData());
	}

}
