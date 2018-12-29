package Queues;
public class DSLinkedQueue<T> implements DSQueueInterface<T> 
{
  protected DSLLNode<T> front;
  protected DSLLNode<T> rear;
  protected int numElements = 0;
  
  public DSLinkedQueue()
  {
    front = null;
    rear  = null;
  }
  
  public void enqueue(T element)
  {
    DSLLNode<T> newNode = new DSLLNode<T>(element);
    if (rear == null)
      front = newNode;
    else
      rear.setNext(newNode);
    rear = newNode;
    numElements++;
  }
  public T top() {
	  T element;
	  element = front.getData();
	  return element;
  }
  public T dequeue() throws DSQueueUnderflowException
  {
    if (isEmpty()) throw new DSQueueUnderflowException("Dequeue attemped on empty queue!");
    else
    {
      T element;
      element = front.getData();
      front = front.getNext();
      if (front == null)
        rear = null;
      numElements--;
      return element;
    }
  }
  
  public boolean isEmpty()
  {
    if (front == null)
      return true;
    else
      return false;
  }
  
  public boolean isFull()
  {
    return false;
  }
  
  public int size()
  {
    return numElements;
  }
}