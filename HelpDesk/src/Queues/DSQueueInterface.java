/**
 * COSC 2100 - Project 6
 * This is a help desk where instead of being one big waiting area, there are smaller queues for each level. The lower-levels queues have first priority
 * @author Rucheng Pan
 * Instructor Brylow
 * TA-BOT:MAILTO rucheng.pan@marquette.edu
 */

	public interface DSQueueInterface<T>
	{
	  void enqueue(T element) throws DSQueueOverflowException;
	  // Adds an element to the end of the queue.
	  
	  T dequeue() throws DSQueueUnderflowException;
	  // Remove an element from the front of the queue.
	  
	  boolean isFull();
	  
	  boolean isEmpty();
	  
	  int size();
	  // Returns number of elements currently stored in the queue.

	}

