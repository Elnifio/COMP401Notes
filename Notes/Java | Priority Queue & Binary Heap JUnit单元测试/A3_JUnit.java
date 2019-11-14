/**
 * 
 */
package MinBinHeap_A3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author elnifio
 *
 */
class A3_JUnit {

	/**
	 * @throws java.lang.Exception
	 */
	
	private Heap_Interface normal_heap;
	private int arr_size = 1000; 		// Denotes the size of array created
	
	private int num_size = 200000; 		// Denotes the range of random number
	
	private int repetition_time = 50; 	// Denotes the number of random tests
										// Increase Coverage & Credibility
	
	private int negative_val = -30; 	// Denotes the difference that should be subtracted from any value
										// Assume that all negative indices are allowed
										// You should change this JUnit test according to specific requirement
	
	private int get_arr_size() {
		Random rn = new Random();
		int out = (rn.nextInt() % 2 == 0)?(1):(0);
		return out + arr_size;
	}
	
	
	@BeforeEach
	void setUp() throws Exception {
		normal_heap = new MinBinHeap();
		
	}

	@Test
	void testSetUp() {
		assertEquals(-100000, normal_heap.getHeap()[0].getPriority());
		assertEquals(10000, normal_heap.getHeap().length);
	}
	
	@Test
	void buildTestReverseOrder() {
		int[] collection = {5, 4, 3, 2, 1};
		EntryPair[] col_entry = this.arr_converter(collection);
		this.normal_heap.build(col_entry);
		this.test_order();
	}
	
	@Test
	void buildTestInOrder() {
		int[] collection = {1, 2, 3};
		EntryPair[] col_entry = this.arr_converter(collection);
		this.normal_heap.build(col_entry);
		this.test_order();
	}
	
	@Test
	void buildTestRandomOrder() {
		int[] collection = {1, 4, 2, 8, 5, 7};
		EntryPair[] col_entry = this.arr_converter(collection);
		this.normal_heap.build(col_entry);
		this.test_order();
	}
	
	@Test
	void testInsertReverseOrder() {
		this.normal_heap = new MinBinHeap();
		int[] list = {5, 4, 3, 2, 1};
		this.arr_inserter(list);
		this.test_order();
	}
	
	@Test
	void testInsertInOrder() {
		this.normal_heap = new MinBinHeap();
		int[] list = {1, 2, 3};
		this.arr_inserter(list);
		this.test_order();
	}
	
	@Test
	void testInsertRandom() {
		this.normal_heap = new MinBinHeap();
		int[] list = {2, 8, 5, 7, 1, 4};
		this.arr_inserter(list);
		this.test_order();
	}
	
	@Test
	void testDelMinReverseOrder() {
		this.normal_heap = new MinBinHeap();
		this.normal_heap.insert(new EntryPair("", 5));
		this.normal_heap.insert(new EntryPair("", 4));
		this.normal_heap.insert(new EntryPair("", 3));
		this.normal_heap.insert(new EntryPair("", 2));
		this.normal_heap.insert(new EntryPair("", 1));
		
		this.normal_heap.delMin();
		this.normal_heap.delMin();
		
		this.test_order();
	}
	
	@Test
	void testDelMinInOrder() {
		this.normal_heap = new MinBinHeap();
		this.normal_heap.insert(new EntryPair("", 1));
		this.normal_heap.insert(new EntryPair("", 2));
		this.normal_heap.insert(new EntryPair("", 3));
		
		this.normal_heap.delMin();
		this.normal_heap.delMin();
		assertEquals(3, this.normal_heap.getMin().getPriority());
	}
	
	@Test
	void testInsertAfterDel() {
		this.normal_heap = new MinBinHeap();
		this.normal_heap.insert(new EntryPair("", 1));
		assertEquals(1, this.normal_heap.getMin().getPriority());
		assertEquals(1, this.normal_heap.size());
		
		this.normal_heap.delMin();
		assertEquals(null, this.normal_heap.getMin());
		assertEquals(0, this.normal_heap.size());
		
		this.normal_heap.insert(new EntryPair("", 1));
		assertEquals(1, this.normal_heap.getMin().getPriority());
		assertEquals(1, this.normal_heap.size());
		
	}
	
	@Test
	void testInsertAfterDel2() {
		this.normal_heap = new MinBinHeap();
		this.normal_heap.insert(new EntryPair("", 4));
		this.normal_heap.insert(new EntryPair("", 1));
		this.normal_heap.insert(new EntryPair("", 2));
		assertEquals(1, this.normal_heap.getMin().getPriority());
		assertEquals(3, this.normal_heap.size());
		
		this.normal_heap.delMin();
		assertEquals(2, this.normal_heap.getMin().getPriority());
		assertEquals(2, this.normal_heap.size());
		
		this.normal_heap.insert(new EntryPair("", 0));
		this.normal_heap.insert(new EntryPair("", 3));
		this.normal_heap.insert(new EntryPair("", 7));
		assertEquals(0, this.normal_heap.getMin().getPriority());
		assertEquals(5, this.normal_heap.size());
		
		this.normal_heap.delMin();
		assertEquals(2, this.normal_heap.getMin().getPriority());
		assertEquals(4, this.normal_heap.size());
		
		this.normal_heap.insert(new EntryPair("", 0));
		assertEquals(0, this.normal_heap.getMin().getPriority());
		assertEquals(5, this.normal_heap.size());
		
		this.test_order();
	}
	
	@Test
	void sizeTestInsert() {
		this.normal_heap = new MinBinHeap();
		this.normal_heap.insert(new EntryPair("", 1));
		this.normal_heap.insert(new EntryPair("", 2));
		assertEquals(1, this.normal_heap.getMin().getPriority());
		assertEquals(2, this.normal_heap.size());
	}
	
	@Test
	void sizeTestDeleteAfterInsert() {
		this.normal_heap = new MinBinHeap();
		this.normal_heap.insert(new EntryPair("", 5));
		this.normal_heap.insert(new EntryPair("", 4));
		this.normal_heap.insert(new EntryPair("", 3));
		this.normal_heap.insert(new EntryPair("", 2));
		this.normal_heap.insert(new EntryPair("", 1));
		assertEquals(1, this.normal_heap.getMin().getPriority());
		assertEquals(5, this.normal_heap.size());
		
		this.normal_heap.delMin();
		this.normal_heap.delMin();
		this.normal_heap.delMin();
		assertEquals(4, this.normal_heap.getMin().getPriority());
		assertEquals(2, this.normal_heap.size());
	}
	
	@Test
	void sizeTestDeleteAll() {
		this.normal_heap = new MinBinHeap();
		this.normal_heap.insert(new EntryPair("", 1));
		this.normal_heap.insert(new EntryPair("", 2));
		this.normal_heap.insert(new EntryPair("", 3));
		assertEquals(1, this.normal_heap.getMin().getPriority());
		assertEquals(3, this.normal_heap.size());
		
		this.normal_heap.delMin();
		this.normal_heap.delMin();
		this.normal_heap.delMin();
		assertEquals(0, this.normal_heap.size());
		assertEquals(null, this.normal_heap.getMin());
		
//		try {
//			this.normal_heap.getMin().getPriority();
//			fail("Not null pointer.");
//		} catch (NullPointerException e) {
//			
//		} catch (RuntimeException f) {
//			fail("Illegal Exception.");
//		}
	}
	
	@Test
	void getMinAndSize() {
		this.normal_heap = new MinBinHeap();
		assertEquals(0, this.normal_heap.size());
		
		this.normal_heap.insert(new EntryPair("", 1));
		this.normal_heap.getMin();
		assertEquals(1, this.normal_heap.size());
		
		this.normal_heap.insert(new EntryPair("", 2));
		this.normal_heap.getMin();
		assertEquals(2, this.normal_heap.size());
		
		this.normal_heap.insert(new EntryPair("", 3));
		this.normal_heap.getMin();
		assertEquals(3, this.normal_heap.size());
		
		this.normal_heap.delMin();
		assertEquals(2, this.normal_heap.size());
		this.normal_heap.getMin();
		assertEquals(2, this.normal_heap.size());
	}
	
	@Test
	void getMinTestBuild() {
		int[] list = {4, 5, 1, 3, 2};
		this.normal_heap.build(this.arr_converter(list));
		assertEquals(1, this.normal_heap.getMin().getPriority());
		this.test_order();
	}
	
	@Test
	void getMinTestInsert() {
		this.normal_heap = new MinBinHeap();
		this.normal_heap.insert(new EntryPair("", 3));
		this.normal_heap.insert(new EntryPair("", 7));
		this.normal_heap.insert(new EntryPair("", 100));
		this.normal_heap.insert(new EntryPair("", 5));
		this.normal_heap.insert(new EntryPair("", 0));
		assertEquals(0, this.normal_heap.getMin().getPriority());
		this.test_order();
	}
	
	@Test
	void getMinTestDelete() {
		int[] list = {1, 4, 2, 8, 5, 7};
		this.normal_heap.build(this.arr_converter(list));
		this.normal_heap.delMin();
		this.normal_heap.delMin();
		this.normal_heap.delMin();
		assertEquals(5, this.normal_heap.getMin().getPriority());
	}
	
	@Test
	void getMinAllInOne() {
		int[] list = {3, 8};
		this.normal_heap.build(this.arr_converter(list));
		this.normal_heap.insert(new EntryPair("", 1));
		this.normal_heap.insert(new EntryPair("", 6));
		this.normal_heap.insert(new EntryPair("", 5));
		this.normal_heap.insert(new EntryPair("", 4));
		this.normal_heap.insert(new EntryPair("", 7));
		this.normal_heap.insert(new EntryPair("", 2));
		this.normal_heap.insert(new EntryPair("", 9));
		this.normal_heap.insert(new EntryPair("", 0));
		this.normal_heap.delMin();
		this.normal_heap.delMin();
		this.normal_heap.delMin();
		assertEquals(7, this.normal_heap.size());
		assertEquals(3, this.normal_heap.getMin().getPriority());
		
		this.normal_heap.insert(new EntryPair("", 0));
		this.normal_heap.insert(new EntryPair("", 10));
		assertEquals(0, this.normal_heap.getMin().getPriority());
		assertEquals(9, this.normal_heap.size());
		this.test_order();
	}
	
	@Test
	void testEdgeCases() {
//		this.normal_heap = new MinBinHeap();
		assertEquals(null, normal_heap.getMin());
		assertEquals(0, normal_heap.size());
		normal_heap.delMin();
		assertEquals(null, normal_heap.getMin());
		assertEquals(0, normal_heap.size());
	}
	
	@Test
	void testDelMinLiteral() {
//		int[] list = {2, 3, 4, 16, 7, 11, 9, 31, 18, 21};
		int[] list = {2, 7, 5, 3};
		for (int i = 0; i < list.length; i++) {
			normal_heap.insert(new EntryPair("", list[i]));
		}
		
		assertEquals(2, this.normal_heap.getMin().getPriority());
		assertEquals("", this.normal_heap.getMin().getValue());
		this.normal_heap.delMin();
		assertEquals(3, this.normal_heap.getMin().getPriority());
		
	}
	
	@Test
	void testInsert() {
		assertEquals(0, this.normal_heap.size());
		this.normal_heap.insert(new EntryPair("", 1));
		assertEquals(1, this.normal_heap.size());
	}
	
	@Test
	void testRandomInsert() {
		
		for (int i = 0; i < this.repetition_time; i ++) {
			EntryPair[] list = create_arr();
			this.normal_heap = new MinBinHeap();
			
			for (int j = 0; j < list.length; j ++) {
				normal_heap.insert(list[j]);
			}
			assertEquals(list.length, this.normal_heap.size());
			this.test_order();
		}
		
	}
	
	@Test
	void testSortedInsert() {
		EntryPair[] list = create_sorted_arr();
		this.normal_heap = new MinBinHeap();
		
		for (int i = 0; i < list.length; i++) {
			normal_heap.insert(list[i]);
		}
		
		assertEquals(list.length, this.normal_heap.size());
		this.test_order();		
	}
	
	@Test
	void testReverseInsert() {
		EntryPair[] list = create_reverse_arr();
		this.normal_heap = new MinBinHeap();
		
		for (int i = 0; i < list.length; i++) {
			normal_heap.insert(list[i]);
		}
		
		assertEquals(list.length, this.normal_heap.size());
		this.test_order();	
	}
	
	@Test
	void randomTestBuild() {
		for (int i = 0; i < this.repetition_time; i++) {
			EntryPair[] list = create_arr();
			this.test_build(list);
		}	
	}
	
	@Test
	void sortedArrayTestBuild() {
		EntryPair[] list = create_sorted_arr();
		this.test_build(list);
	}
	
	@Test
	void reverseArrayTestBuild() {
		EntryPair[] list = create_reverse_arr();
		this.test_build(list);
	}
	
	public void arr_inserter(int[] list) {
		int counter = 0;
		for (int i = 0; i < list.length; i++) {
			boolean is_repeat = false;
			for (int j = i + 1; j < list.length; j++) {
				if (list[i] == list[j]) {
					is_repeat = true;
					break;
				}
			}
			
			if (!is_repeat) {
				this.normal_heap.insert(new EntryPair("", list[i]));
				counter += 1;
			}
		}
		
		assertEquals(counter, this.normal_heap.size());
	}
	
	public EntryPair[] arr_converter(int[] list) {
		EntryPair[] out = new EntryPair[list.length];
		int counter = 0;
		boolean repeat = false;
		for (int i = 0, k = 0; i < out.length; i++ ) {
			for (int j = i + 1; j < list.length; j++) {
				if (list[i] == list[j]) {
					repeat = true;
					break;
				}
			}
			if (!repeat) {
				out[k] = new EntryPair("", list[i]);
				k ++;
				counter ++;
			}
		}
		
		EntryPair[] out_arr = new EntryPair[counter];
		for (int i = 0; i < counter; i++) {
			out_arr[i] = out[i];
		}
		return out_arr;
	}
	
	public void test_build(EntryPair[] list) {
		this.normal_heap.build(list);
		assertEquals(list.length, this.normal_heap.size());
		this.test_order();
	}
	
	public void test_order() {
		int temp = this.normal_heap.getMin().getPriority();
		this.normal_heap.delMin();
		while (normal_heap.size() != 0) {
			assertTrue(temp < this.normal_heap.getMin().getPriority());
			temp = this.normal_heap.getMin().getPriority();
			this.normal_heap.delMin();
		}
		
	}

	public  EntryPair[] create_sorted_arr() {
		EntryPair[] out = new EntryPair[this.get_arr_size()];
		
		for (int i = 0; i < out.length; i ++) {
			out[i] = new EntryPair("", i + this.negative_val);
		}
		
		return out;
	}
	
	public EntryPair[] create_reverse_arr() {
		EntryPair[] out = new EntryPair[this.get_arr_size()];
		
		for (int i = 0; i < out.length; i++) {
			out[i] = new EntryPair("", (out.length - i + this.negative_val));
		}
		
		return out;
		
	}
	
	public EntryPair[] create_arr(int size) {
		Random rn = new Random();
		int counter = 0;
		EntryPair[] out_arr = new EntryPair[size];
		for (int i = 0; i < size; i++) {
			int next = rn.nextInt(this.num_size) + this.negative_val;
			boolean is_repeat = false;
			for (int j = 0; j < i; j++) {
				if (out_arr[j] != null) {
					if (out_arr[j].getPriority() == next) {
						is_repeat = true;
						break;
					}
				}
			}
			if (!is_repeat) {
				out_arr[i] = new EntryPair("", next);
				counter += 1;
			}
		}
		
		EntryPair[] out = new EntryPair[counter];
		for (int i = 0, j = 0; i < out_arr.length; i++) {
			if (out_arr[i] != null) {
				out[j] = out_arr[i];
				j += 1;
			}
		}

		return out;
	}
	
	public EntryPair[] create_arr() {
		return this.create_arr(this.get_arr_size());
	}
	
}
