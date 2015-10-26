import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLinkedList {

	private LinkedList list;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList();
	}

	@Test
	public void testAppend() {
		list.append(1);
		assertEquals(1, list.head.value);
		assertEquals(1, list.tail.value);
		list.append(2);
		assertEquals(2, list.tail.value);
		assertEquals(2, list.head.right.value);
		assertEquals(list.head.right, list.tail);
		assertEquals(list.tail.left, list.head);
	}

	@Test
	public void testGet() {
		list.append(1);
		assertEquals(list.get(0), list.head);
		list.append(2);
		assertEquals(list.get(1), list.tail);
		assertEquals(list.get(1).value, 2);
	}

	@Test
	public void testSize() {
		assertEquals(list.size(), 0);
		list.append(1);
		assertEquals(list.size(), 1);
		list.append(2);
		assertEquals(list.size(), 2);
	}

	@Test
	public void testNodeOf() {
		list.append(1);
		list.append(2);
		assertEquals(list.nodeOf(1), list.head);
		assertEquals(list.nodeOf(3), null);
	}
	
	@Test
	public void testRemoveAt() {
		list.append(1);
		list.append(2);
		list.append(3);
		Node second = list.head.right;
		list.removeAt(second);
		assertEquals(list.head.right, list.tail);
		assertEquals(list.tail.left, list.head);	
	}
    @Test
    public void testCopy() {
    	list.append(1);
		list.append(2);
		list.append(3);
        LinkedList copy = list.copy();

        assertEquals(copy.size(), list.size());
        for (int i = 0; i < list.size(); ++i) {
            assertEquals(list.get(i).value, copy.get(i).value);
            assertNotEquals(list.get(i), copy.get(i));
        }
    }
    @Test
    public void testReverse() {
    	list.append(1);
		list.append(2);
		list.append(3);
        LinkedList reversed = list.reverse();
        int size = list.size();
        assertEquals(reversed.size(), size);
        
        for (int i = 0; i < size; ++i) {
            assertEquals(list.get(i).value, reversed.get(size - i - 1).value);
            assertNotEquals(list.get(i), reversed.get(size - i - 1));
        }
    }
    @Test
    public void testEquals() {
    	list.append(1);
        LinkedList copy = list.copy();
        
        assertTrue(list.equals(list));
        assertTrue(list.equals(copy));
        copy.append(6);
        assertFalse(list.equals(copy));
    }

}
