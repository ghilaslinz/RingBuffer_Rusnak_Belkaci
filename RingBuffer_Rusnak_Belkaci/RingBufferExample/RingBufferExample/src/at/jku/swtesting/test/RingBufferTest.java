package at.jku.swtesting.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.fastinfoset.sax.Properties;

import at.jku.swtesting.RingBuffer;

/*
 * Authors : David Rusnak, Ghilas Belkaci*/
public class RingBufferTest {
private static int CAPACITY = 10;
private RingBuffer<Object> rb;

	@Before
	public void setUp() throws Exception {
		rb = new RingBuffer<>(CAPACITY);
	}

	@After
	public void tearDown() throws Exception {
		rb = null;
	}

	@Test (expected = IllegalArgumentException.class)
	public void testRingBuffer() {
		 new RingBuffer<>(-5);
	}

	@Test
	public void testCapacity() {
		assertEquals(CAPACITY, rb.capacity());
	}

	@Test
	public void testSize() {
		rb.enqueue(new Integer(7));
		assertEquals(1, rb.size());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(rb.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(rb.isFull());
		//fill it up
		for (int i = 0; i < CAPACITY; i++) {
			rb.enqueue(new Integer(5));
		}
		assertTrue(rb.isFull());
	}

	@Test
	public void testDequeue() {
		Object element = new Integer(7);
		rb.enqueue(element);
		assertEquals(element, rb.dequeue());
	}

	@Test(expected = RuntimeException.class)
	public void testDequeueThrowsException() {
		rb.dequeue();
	}
	
	@Test
	public void testPeek()  {
		Object element = new Integer(7);
		rb.enqueue(element);
		assertEquals(element, rb.peek());
	}
	
	@Test
	public void testPeekDoesNotChangeTheBuffer()  {
		Object element = new Integer(7);
		rb.enqueue(element);
		rb.peek();
		assertEquals(1, rb.size());
	}
	
	@Test(expected = RuntimeException.class)
	public void testPeekThrowsException()  {
		rb.peek();
	}

}
