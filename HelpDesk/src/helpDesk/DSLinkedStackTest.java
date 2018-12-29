package helpDesk;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Test;

 class DSLinkedStackTest {

	public void testTopException() {
		DSLinkedStack<Integer> stack = new DSLinkedStack<Integer>();
		try
		{
			stack.top();
			fail();
		}
		catch (StackUnderflowException e)
		{
		}
	}


	public void testPopException() {
		DSLinkedStack<Integer> stack = new DSLinkedStack<Integer>();
		try
		{
			stack.pop();
			fail();
		}
		catch (StackUnderflowException e)
		{
		}
	}

	@Test
	public void testPushPopTop1() {
		DSLinkedStack<Integer> stack = new DSLinkedStack<Integer>();
		stack.push(5);
		stack.push(7);
		stack.push(6);
		stack.push(9);
		try
		{
			assertTrue(stack.top() == 9);
			stack.pop();
			assertTrue(stack.top() == 6);
			stack.pop();
			assertTrue(stack.top() == 7);
			stack.pop();
			assertTrue(stack.top() == 5);
			stack.pop();			
		}
		catch (StackUnderflowException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void testPushPopTop2() {
		DSLinkedStack<Integer> stack = new DSLinkedStack<Integer>();
		stack.push(2);
		stack.push(3);
		stack.push(3);
		stack.push(4);
		try
		{
			assertTrue(stack.top() == 4);
			stack.pop();
			assertTrue(stack.top() == 3);
			stack.pop();
			assertTrue(stack.top() == 3);
			stack.pop();
			assertTrue(stack.top() == 2);
			stack.pop();			
		}
		catch (StackUnderflowException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void testPushPopTop3() {
		DSLinkedStack<Integer> stack = new DSLinkedStack<Integer>();
		try
		{
			stack.push(5);
			stack.pop();
			stack.push(3);
			stack.push(7);
			stack.pop();
			assertTrue(stack.top() == 3);
			stack.pop();
		}
		catch (StackUnderflowException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void testIsEmpty() {
		DSLinkedStack<Integer> stack = new DSLinkedStack<Integer>();
		assertTrue(stack.isEmpty());
		stack.push(5);
		assertFalse(stack.isEmpty());
		try
		{
			stack.pop();
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
		assertTrue(stack.isEmpty());
	}
}
