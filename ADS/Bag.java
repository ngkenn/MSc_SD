// package assEx2018.filesForExercise;

import java.util.Iterator;

public interface Bag<E> extends Iterable<E> {
	// Each Bag<E> object is a homogeneous bag whose
	// members are of type E.
	// ////////// Accessors ////////////
	public boolean isEmpty(); // Return true if and only if this bag is empty.

	public int size(); // Return the size of this set.

	public boolean contains(E element);

	// Return true if and only if element is a member of this bag.

	public boolean equals(Bag<E> that);

	// Return true if and only if this bag is equal to that.

	// //////////Transformers ////////////

	public void clear(); // Make this bag empty.

	public void add(E element); // Add element to bag.
	// increment the number of element items in the bag

	public void remove(E element); // Remove it from this set.
	// Do nothing if no item in bag pertaining to element
	// otherwise decrement number of element items (lazy deletion)

	// ////////// Iterator ////////////

	public Iterator<E> iterator();
	// Return an iterator that will visit all members of this
	// bag, in no particular order
}
