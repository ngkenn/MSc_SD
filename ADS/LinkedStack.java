import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {
	private Node<E> top;
	/////////////// Inner class ///////////////
	private static class Node<E> {
		public E element;
		public Node<E> next;
		
		public Node (E x, Node<E> n) {
			element = x;  next = n;
		}
	}
	
     /////////////// Constructor ///////////////
     public LinkedStack () {
    	 top = null;
     }
     
     /////////////// Accessors ///////////////
     public boolean empty () {
    	 return (top == null);
     }

     public E peek () {
    	 if (top == null)  throw new NoSuchElementException("Can't peek empty stack");
    	 return top.element;
    	 }


    //////////////Transformers ///////////////
    public void clear () {
    	top = null;
    }

    public void push (E it) {
    	Node<E> temp = new Node<E>(it,top);
    	top = temp;
    }

		
	
	public E pop() {
		if (top == null)  throw new NoSuchElementException("Can't pop empty stack");
		E topElem = top.element;
		top = top.next;
		return topElem;
	}

	

}
