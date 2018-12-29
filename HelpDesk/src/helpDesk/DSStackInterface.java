/**
 * COSC 2100 - Project 6
 * This is a help desk where instead of being one big waiting area, there are smaller queues for each level. The lower-levels queues have first priority
 * @author Rucheng Pan
 * Instructor Brylow
 * TA-BOT:MAILTO rucheng.pan@morbius.marquette.edu
 */

public interface DSStackInterface<T> {
	void push(T element) throws StackOverflowException;
	  // Throws StackOverflowException if this stack is full,
	  // otherwise places element at top of stack.
	 
	  void pop() throws StackUnderflowException;
	  // Throws StackUnderflowException if this stack is empty,
	  // otherwise removed top element from this stack.
	  
	  T top() throws StackUnderflowException;
	  // Throws StackUnderflowException if this stack is empty,
	  // otherwise returns top element of stack.
	  
	  boolean isFull();
	  // Returns true if this stack is full, otherwise false.
	  
	  boolean isEmpty();
	  // Returns true if this stack is empty, otherwise false.
}
