package DiGraph_A5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Graph_JUnit {
	DiGraph g;
	boolean x;
	HashMap<String, Long> m = new HashMap<String, Long>();
	ShortestPathInfo[] a;

	@BeforeEach
	void setUp() throws Exception {
		g = new DiGraph();
	}

	@Test
	void testAddNode() {
		// Return false if node number is not 0 or higher
		x = g.addNode(-2, "");
		assertFalse(x);
		assertEquals(g.numNodes(), 0);
		// --------
		// Use the following codes to test if the value is actually stored in the graph
		// Modify this to fit your implementation
//		assertTrue(g.vertices.isEmpty());
//		assertTrue(g.inEdges.isEmpty());
//		assertTrue(g.outEdges.isEmpty());
		
		
		// Return false if label is null
		x = g.addNode(3, null);
		assertFalse(x);
		assertEquals(g.numNodes(), 0);
		// --------
//		assertTrue(g.vertices.isEmpty());
//		assertTrue(g.inEdges.isEmpty());
//		assertTrue(g.outEdges.isEmpty());
		
		
		// Return true if valid
		x = g.addNode(1, "a");
		assertTrue(x);
		assertEquals(g.numNodes(), 1);
		// --------
//		assertEquals(g.nodeID.get("a"), 1);
//		assertTrue(g.inEdges.get("a").isEmpty());
//		assertTrue(g.outEdges.get("a").isEmpty());
		
		
		// Return false if node number is not unique
		x = g.addNode(1, "b");
		assertFalse(x);
		assertEquals(g.numNodes(), 1);
		// --------
//		assertEquals(g.nodeID.get("a"), 1);
//		assertFalse(g.vertices.containsValue("b"));
		
		
		// Return false if label is not unique
		x = g.addNode(3, "a");
		assertFalse(x);
		assertEquals(g.numNodes(), 1);
		// --------
//		assertFalse(g.vertices.containsKey((long)3));
//		assertTrue(g.vertices.containsValue("a"));	
	}
	
	@Test
	void testAddEdge() {
		x = g.addNode(3, "a");
		assertTrue(x);
		x = g.addNode(5, "b");
		assertTrue(x);
		x = g.addNode(2, "c");
		assertEquals(g.numNodes(), 3);
		
		// Return false if edge number is less than 0
		x = g.addEdge(-2, "a", "b", 3, "avc");
		assertFalse(x);
		assertEquals(g.numEdges(), 0);
		
		// Return false if source is null
		x = g.addEdge(2, null, "b", 3, "avc");
		assertFalse(x);
		assertEquals(g.numEdges(), 0);
		
		// Return false if dest is null
		x = g.addEdge(2, "a", null, 3, "avc");
		assertFalse(x);
		assertEquals(g.numEdges(), 0);
		
		// Return false if source node is not in the graph
		x = g.addEdge(3, "d", "b", 3, "avc");
		assertFalse(x);
		assertEquals(g.numEdges(), 0);
		
		// Return false if dest node is not in the graph
		x = g.addEdge(3, "a", "d", 3, "avc");
		assertFalse(x);
		assertEquals(g.numEdges(), 0);
		
		// test normal function
		// In this graph, (a, 3) -(3)-> (b, 5)
		x = g.addEdge(2, "a", "b", 3, "abc");
		assertTrue(x);
		assertEquals(g.numEdges(), 1);
		
		// Return false if already has an edge between these two
		x = g.addEdge(5, "a", "b", 2, "avc");
		assertFalse(x);
		assertEquals(g.numEdges(), 1);
		
		// Return false if edge number is not unique
		x = g.addEdge(2, "a", "c", 3, "avc");
		assertFalse(x);
		assertEquals(g.numEdges(), 1);
		
		// test normal function
		// In this graph, (c, 2) -(5)-> (a, 3)
		x = g.addEdge(5, "c", "a", 5, null);
		assertTrue(x);
		assertEquals(g.numEdges(), 2);
		
		// Test normal Function
		// in this graph, (b, 5) -(4)-> (c, 2)
		x = g.addEdge(9, "b", "c", 4, "edf");
		assertTrue(x);
		assertEquals(g.numEdges(), 3);
		
		// Test normal Function
		// In this graph, (a, 3) -(6)-> (c, 2)
		x = g.addEdge(12, "a", "c", 6, "acv");
		assertTrue(x);
		assertEquals(g.numEdges(), 4);
		
		// Return false if already has an edge between these two
		x = g.addEdge(4, "b", "c", 3, null);
		assertFalse(x);
		assertEquals(g.numEdges(), 4);
		
		// Test Normal Function
		// In this graph, (a, 3) -(2)-> (a, 3)
		x = g.addEdge(15, "a", "a", 2, "eds");
		assertTrue(x);
		assertEquals(g.numEdges(), 5);
		
		// Return false if already has an edge between these two
		x = g.addEdge(233, "a", "a", 4, "edf");
		assertFalse(x);
		assertEquals(g.numEdges(), 5);
		
		// Test No Weight Argument
		x = g.addEdge(14, "c", "b", "abc");
		assertTrue(x);
		assertEquals(6, e());
	}
	
	@Test
	void testRemoveNodes() {
		x = g.addNode(3, "a");
		assertTrue(x);
		x = g.addNode(5, "b");
		assertTrue(x);
		x = g.addNode(2, "c");
		assertEquals(g.numNodes(), 3);
		
		// Remove Null should return false
		x = g.delNode(null);
		assertFalse(x);
		assertEquals(g.numNodes(), 3);
		
		// Remove a node that does not exist in the graph should return false
		x = g.delNode("d");
		assertFalse(x);
		assertEquals(g.numNodes(), 3);
		
		// (a, 3) -(3)-> (b, 5)
		x = g.addEdge(2, "a", "b", 3, "abc");
		assertTrue(x);
		assertEquals(g.numEdges(), 1);
		
		// (c, 2) -(5)-> (a, 3)
		x = g.addEdge(5, "c", "a", 5, null);
		assertTrue(x);
		assertEquals(g.numEdges(), 2);
		
		// (b, 5) -(4)-> (c, 2)
		x = g.addEdge(9, "b", "c", 4, "edf");
		assertTrue(x);
		assertEquals(g.numEdges(), 3);
		
		// (a, 3) -(6)-> (c, 2)
		x = g.addEdge(12, "a", "c", 6, "acv");
		assertTrue(x);
		assertEquals(g.numEdges(), 4);
		
		// (a, 3) -(2)-> (a, 3)
		x = g.addEdge(15, "a", "a", 2, "eds");
		assertTrue(x);
		assertEquals(g.numEdges(), 5);
		
		// (b, 5) -(12)-> (b, 5)
		x = g.addEdge(33, "b", "b", 12, "eddfa");
		assertTrue(x);
		assertEquals(g.numEdges(), 6);
		
		// Remove c
		x = g.delNode("c");
		assertTrue(x);
		assertEquals(3, g.numEdges());
		
		// Remove a
		x = g.delNode("a");
		assertTrue(x);
		assertEquals(g.numEdges(), 1);
		
		// Remove b
		x = g.delNode("b");
		assertTrue(x);
		assertEquals(0, g.numEdges());
	}
	
	@Test
	void testRemoveNodesAdvanced() {
		this.testRemoveNodeUnconnected("test");
		this.testRemoveNodeCycle("test");
		this.testRemoveNodeSelf("test");
		this.testRemoveInsert("");
		this.testRemoveOutNode("");
	}
	
	@Test
	void testRemoveEdgeBasic() {
		x = g.addNode(3, "a");
		x = g.addNode(5, "b");
		x = g.addNode(2, "c");
		assertEquals(g.numNodes(), 3);
		
		// (a, 3) -(3)-> (b, 5)
		x = g.addEdge(2, "a", "b", 3, "abc");
		
		// (c, 2) -(5)-> (a, 3)
		x = g.addEdge(5, "c", "a", 5, null);
		
		// (b, 5) -(4)-> (c, 2)
		x = g.addEdge(9, "b", "c", 4, "edf");
		
		// (a, 3) -(6)-> (c, 2)
		x = g.addEdge(12, "a", "c", 6, "acv");
		
		// (a, 3) -(2)-> (a, 3)
		x = g.addEdge(15, "a", "a", 2, "eds");

		// (b, 5) -(12)-> (b, 5)
		x = g.addEdge(33, "b", "b", 12, "eddfa");
		assertTrue(x);
		assertEquals(g.numEdges(), 6);
		
		// Return false if source is null
		x = g.delEdge(null, "c");
		assertFalse(x);
		assertEquals(6, e());
		
		// Return false if dest is null
		x = g.delEdge("a", null);
		assertFalse(x);
		assertEquals(6, e());
		
		// Return false if source node does not exist
		x = g.delEdge("e", "c");
		assertFalse(x);
		assertEquals(6, e());
		
		// Return false if destination node does not exist
		x = g.delEdge("a", "e");
		assertFalse(x);
		assertEquals(6, e());
		
		// Return false if edge does not exist
		x = g.delEdge("c", "c");
		assertFalse(x);
		assertEquals(6, e());
		
		// Return true if successfully removed
		x = g.delEdge("b", "b");
		assertTrue(x);
		assertEquals(5, e());
		
		// Return true if successfully removed
		x = g.delEdge("a", "c");
		assertTrue(x);
		assertEquals(4, e());
		
		// Return false if edge is removed
		x = g.delEdge("a", "c");
		assertFalse(x);
		assertEquals(4, e());
		
		// test for the same label added twice after deletion
		x = g.addEdge(12, "a", "c", 6, "avc");
		assertTrue(x);
		assertEquals(5, e());
	}
	
	@Test
	void testShortestPathBasic() {
		g = new DiGraph();
		g.addNode(3, "A");
		g.addNode(1, "B");
		g.addNode(2, "C");
		g.addNode(4, "D");
		g.addNode(5, "E");
		g.addNode(6, "F");
		g.addNode(7, "G");
		assertEquals(7, n());
		
		g.addEdge(1, "A", "B", 2, "");
		g.addEdge(2, "B", "E", 1, "");
		g.addEdge(3, "A", "D", 1, "");
		g.addEdge(4, "B", "D", 3, "");
		g.addEdge(5, "C", "A", 4, "");
		g.addEdge(6, "D", "C", 2, "");
		g.addEdge(7, "C", "F", 5, "");
		g.addEdge(8, "D", "F", 8, "");
		g.addEdge(9, "G", "F", 1, "");
		g.addEdge(0, "D", "G", 4, "");
		g.addEdge(11, "D", "E", 3, "");
		g.addEdge(12, "E", "G", 6, "");
		assertEquals(12, e());
		
		// Should return null if node is not in the graph
		a = g.shortestPath("H");
		assertEquals(null, a);
		
		// Test Normal Functionality
		a = g.shortestPath("A");
		convertArray();
		ValPair[] v1 = {
				new ValPair("A", 0), 
				new ValPair("B", 2), 
				new ValPair("C", 3),
				new ValPair("D", 1),
				new ValPair("E", 3),
				new ValPair("F", 6),
				new ValPair("G", 5)
				};
		
		for (ValPair e: v1) {
			assertEquals(e.weight, m.get(e.label));
		}
		
		// Test Normal Functionality
		a = g.shortestPath("G");
		convertArray();
		ValPair[] v2 = {
				new ValPair("A", -1), 
				new ValPair("B", -1), 
				new ValPair("C", -1),
				new ValPair("D", -1),
				new ValPair("E", -1),
				new ValPair("F", 1),
				new ValPair("G", 0)
				};
		
		for (ValPair e: v2) {
			assertEquals(e.weight, m.get(e.label));
		}
	}
	
	
	
	void setUpNode() {
		g = new DiGraph();
		g.addNode(3, "a");
		g.addNode(5, "b");
		g.addNode(7, "c");
		g.addNode(11, "d");
		g.addNode(13, "e");
		g.addNode(17, "f");
		g.addNode(23, "g");
		assertEquals(7, g.numNodes());
		assertEquals(0, e());
	}
	
	void convertArray() {
		for (ShortestPathInfo e: a) {
			m.put(e.getDest(), e.getTotalWeight());
		}
	}
	
	void testRemoveOutNode(String s) {
		setUpNode();
		g.addEdge(1, "b", "a", 6, s);
		g.addEdge(2, "c", "a", 6, s);
		g.addEdge(3, "d", "a", 6, s);
		g.addEdge(4, "e", "a", 6, s);
		g.addEdge(5, "f", "a", 6, s);
		g.addEdge(6, "g", "a", 6, s);
		g.addEdge(7, "b", "c", 6, s);
		g.addEdge(8, "d", "c", 6, s);
		g.addEdge(9, "f", "b", 6, s);
		g.addEdge(10, "e", "e", 6, s);
		g.addEdge(11, "c", "f", 6, s);
		g.addEdge(12, "f", "d", 6, s);
		assertEquals(12, e());
		assertEquals(7, n());
		g.delNode("a");
		assertEquals(6, e());
		assertEquals(6, n());
		g.addNode(3, "a");
		assertEquals(7, n());
		g.addEdge(1, "b", "a", 6, s);
		g.addEdge(2, "c", "a", 6, s);
		g.addEdge(3, "d", "a", 6, s);
		g.addEdge(4, "e", "a", 6, s);
		g.addEdge(5, "f", "a", 6, s);
		g.addEdge(6, "g", "a", 6, s);
		assertEquals(12, e());
	}
	
	void testRemoveNodeUnconnected(String s) {
		setUpNode();
		assertEquals(0, g.numEdges());
		g.delNode("a");
		assertEquals(0, g.numEdges());
	}
	
	void testRemoveNodeSelf(String s) {
		setUpNode();
		g.addEdge(3, "a", "a", 6, s);
		g.addEdge(2, "a", "b", 5, s);
		g.addEdge(1, "a", "c", 4, s);
		g.addEdge(4, "a", "d", 3, s);
		g.addEdge(5, "a", "e", 2, s);
		g.addEdge(6, "a", "f", 1, s);
		g.addEdge(7, "a", "g", 2, s);
		g.addEdge(8, "b", "a", 3, s);
		g.addEdge(9, "c", "a", 2, s);
		g.addEdge(10, "d", "a", 5, s);
		g.addEdge(81, "e", "a", 4, s);
		g.addEdge(82, "f", "a", 6, s);
		g.addEdge(83, "g", "a", 8, s);
		assertEquals(13, e());
		g.delNode("a");
		assertEquals(0, e());
	}
	
	void testRemoveInsert(String s) {
		setUpNode();
		g.addEdge(3, "a", "a", 6, s);
		g.addEdge(2, "a", "b", 5, s);
		g.addEdge(1, "a", "c", 4, s);
		g.addEdge(4, "f", "d", 3, s);
		g.addEdge(5, "d", "e", 2, s);
		g.addEdge(6, "d", "f", 1, s);
		g.addEdge(7, "g", "g", 2, s);
		g.addEdge(31, "b", "a", 6, s);
		g.addEdge(21, "b", "b", 5, s);
		g.addEdge(11, "b", "c", 4, s);
		g.addEdge(41, "b", "d", 3, s);
		g.addEdge(51, "c", "e", 2, s);
		g.addEdge(61, "c", "f", 1, s);
		g.addEdge(71, "e", "g", 2, s);
		assertEquals(14, e());
		assertEquals(7, n());
		g.delNode("a");
		assertEquals(10, e());
		assertEquals(6, n());
		g.addNode(3, "a");
		assertEquals(7, n());
		g.addEdge(3, "a", "a", 6, s);
		g.addEdge(2, "a", "d", 6, s);
		g.addEdge(1, "a", "f", 6, s);
		assertEquals(13, e());
	}
	
	void testRemoveNodeCycle(String s) {
		setUpNode();
		assertEquals(0, e());
		g.addEdge(3, "a", "b", 6, s);
		g.addEdge(5, "b", "a", 2, s);
		g.addEdge(1, "c", "e", 7, s);
		g.addEdge(7, "e", "a", 7, s);
		g.addEdge(4, "f", "b", 7, s);
		g.addEdge(9, "a", "a", 7, s);
		g.addEdge(12, "d", "c", 7, s);
		g.addEdge(2, "a", "g", 7, s);
		assertEquals(8, e());
		g.delNode("a");
		assertEquals(3, e());
		assertEquals(6, n());
		g.delNode("b");
		assertEquals(2, e());
		assertEquals(5, n());
		g.delNode("d");
		assertEquals(1, e());
		assertEquals(4, n());
		g.delNode("c");
		assertEquals(0, e());
		assertEquals(3, n());
	}
	
	long e() {
		return g.numEdges();
	}
	
	long n() {
		return g.numNodes();
	}
	
}

class ValPair {
	String label;
	long weight;
	
	public ValPair(String label, long weight) {
		this.label = label;
		this.weight = weight;
	}
}