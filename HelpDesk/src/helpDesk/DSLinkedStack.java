/**
 * COSC 2100 - Project 6
 * This is a help desk where instead of being one big waiting area, there are smaller queues for each level. The lower-levels queues have first priority
 * @author Rucheng Pan
 * Instructor Brylow
 * TA-BOT:MAILTO rucheng.pan@marquette.edu
 */

public class DSLinkedStack<T> implements DSStackInterface<T> {
	private DSLLNode<T> top;
	 
	  public DSLinkedStack()
	  {
	    setTop(null);
	  }

	 
	  public void push(T element)
	  {
	    setTop(new DSLLNode<T>(element, getTop()));
	  }
	 
	  public void pop() throws StackUnderflowException
	  {
	    if (!isEmpty())
	      setTop(getTop().getNext());
	    else
	      throw new StackUnderflowException("Top attempted on empty stack.");
	  }
	 
	  public T top() throws StackUnderflowException
	  {
	    if (!isEmpty())
	      return getTop().getData();
	    else
	      throw new StackUnderflowException("Top attempted on empty stack.");
	  }
	 
	  public boolean isEmpty()
	  {
	    return (getTop() == null);
	  }
	  
	  public boolean isFull()
	  {
	    // The Link-based stack is never full.
	    return false;
	  }


	public DSLLNode<T> getTop() {
		return top;
	}


	public void setTop(DSLLNode<T> top) {
		this.top = top;
	}
}
