public interface Stack<E> {
	// Each Stack<E> object is a homogeneous stack whose elements are of type E.
	/////////////// Accessors ///////////////
	public boolean empty ();	// Return true if and only if this stack is empty.
	public E peek ();	// Return the element at the top of this stack.
    //////////////Transformers ///////////////
    public void clear ();	// Make this stack empty.
    
    public void push (E it);	// Add it as the top element of this stack.
    
    public E pop ();	// Remove and return the element at the top of this stack.
}

