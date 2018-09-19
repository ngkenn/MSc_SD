
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Student Number: 2025066K
Name: Neil Kennedy
 */

public class BSTBag<E extends Comparable<E>> implements Bag<E> {

    private BSTBag.Node<E> root;

    /**
     * BSTBag constructor
     */
    public BSTBag() {
        root = null;
    }


    /**
     * Inner class for Node
     */
    private static class Node<E extends Comparable<E>> {
        protected CountedElement<E> element;
        protected Node<E> left, right;

        /**
         * Node Constructor
         *
         * @param e Creates a node in the BST
         */
        private Node(E e) {
            this.element = new CountedElement<>(e);
            this.left = null;
            this.right = null;
        }

        /**
         * Method to delete the topmost element
         *
         * @return - The node to be deleted
         */
        public Node<E> deleteTopmost() {
            if (this.left == null) {
                return this.right;
            } else if (this.right == null) {
                return this.left;
            } else { // This Node has 2 children
                this.element = this.right.getLeftmost();
                this.right = this.right.deleteLeftmost();
                return this;
            }

        }

        /**
         * Method to get the leftmost element
         *
         * @return - the element
         */
        private CountedElement<E> getLeftmost() {
            Node<E> curr = this;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr.element;
        }

        /**
         * Method to delete the leftmost element
         *
         * @return - the element to be deleted
         */
        public Node<E> deleteLeftmost() {
            if (this.left == null) {
                return this.right;
            } else {
                Node<E> parent = this;
                Node<E> curr = this.left;
                while (curr.left != null) {
                    parent = curr;
                    curr = curr.left;
                }
                parent.left = curr.right;
                return this;
            }
        }


    }

    /**
     * Check if the tree is empty
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        if (root == null) return true;
        else return false;
    }


    /**
     * Method to return the size of the tree
     *
     * @return
     */
    public int size() {
        return (size(this.root));
    }

    /**
     * Recursive size method
     *
     * @param node
     * @return
     */
    private int size(Node node) {
        if (node == null) return (0);
        else {
            return (size(node.left) + 1 + size(node.right));
        }
    }
    // Return the size of this set.

    /**
     * Check if a BSTBag contains a specified element
     *
     * @param element - the element to search for
     * @return - return true if the element is found
     */
    public boolean contains(E element) {
        int dir;
        Node<E> parent = null;
        Node<E> curr = root;

        for (; ; ) {
            // if curr is null, element is not in bag, return false
            if (curr == null) return false;

            // otherwise, set dir to
            dir = element.compareTo(curr.element.getElement());
            if (dir == 0) {
                return true;
            } else if (dir < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }


    /**
     * Checks if one bag is equal to another
     *
     * @param that - the bag to be compared against this
     * @return - true or false
     */
    public boolean equals(Bag<E> that) {
        Iterator<E> thatIterator = that.iterator();

        // Loop while the iterator for that finds more elements
        while (thatIterator.hasNext()) {

            // If something is found in that which isn't contained in this,
            // return false
            if (!this.contains(thatIterator.next())) {
                return false;
            }
        }
        return true;
    }


    // //////////Transformers ////////////

    /**
     * Method to make this bag empty
     */
    public void clear() {
        //set root to null
        root = null;
    }

    /**
     * Method to add an element to BSTBag
     * Searches the tree for the correct position
     *
     * @param element - the element to be added
     */
    public void add(E element) {
        int dir = 0;
        Node<E> parent = null;
        Node<E> curr = root;

        for (; ; ) {

            // if the current node is null, that is the correct place for the new element
            if (curr == null) {
                Node<E> newElem = new Node<>(element);

                // Set to root if root is null
                if (root == null) {
                    root = newElem;
                }
                // Otherwise, set to either left or right of parent based on dir
                else if (dir < 0) {
                    parent.left = newElem;
                } else {
                    parent.right = newElem;
                }
                return;
            } else {

                // If the current node is non null, keep looking for the position
                dir = element.compareTo(curr.element.getElement());

                // If the current node is non-null, and the direction is 0,
                // then the node to be added is a duplicate
                // so, don't actually add a new node, just increment the count and return
                if (dir == 0) {
                    curr.element.setCount(curr.element.getCount() + 1);
                    return;
                }

                // otherwise, set the new node
                parent = curr;
                if (dir < 0) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
        }

    }
    /**
     * Method to remove an element from BSTBag
     * Searches the tree for the element, then remove
     *
     * @param element - the element to be removed
     */
    public void remove(E element) {
        if (!this.contains(element)) {
            System.out.println("BSTBag does not contain this element");
        } else {
            int dir;
            Node<E> parent = null;
            Node<E> curr = root;

            for (; ; ) {
                if (curr == null) return;

                dir = element.compareTo(curr.element.getElement());

                // If the Node to be deleted is curr:
                if (dir == 0) {
                    // Check if the count is greater than 1. If so,
                    // don't delete, just decrement the count
                   int elementCount = curr.element.getCount();
                    if(elementCount>1){
                        curr.element.setCount(elementCount-1);
                    }

                    // If there is only one such element, delete
                    else {
                        Node<E> del = curr.deleteTopmost();
                        if (curr == root) {
                            root = del;
                        } else if (curr == parent.left) {
                            parent.left = del;
                        } else {
                            parent.right = del;
                        }
                        return;
                    }
                }

                // If the node to be deleted isn't curr, keep looking
                parent = curr;
                if (dir < 0) {
                    curr = parent.left;
                } else {
                    curr = parent.right;
                }
            }
        }
    }

    ; // Remove it from this set.
    // Do nothing if no item in bag pertaining to element
    // otherwise decrement number of element items (lazy deletion)


    // ////////// Iterator ////////////
    public Iterator<E> iterator() {
        InOrderIterator i = new InOrderIterator();
        return i;
    }

    /**
     * Nested class to create an InOrderIterator
     */
    public class InOrderIterator implements Iterator<E> {
        private Stack<BSTBag.Node<E>> track;

        private InOrderIterator() {
            track = new LinkedStack<>();
            for (BSTBag.Node<E> curr = root; curr != null; curr = curr.left) {
                track.push(curr);
            }
        }

        public boolean hasNext() {
            return (!track.empty());
        }

        public E next() {
            if (track.empty()) {
                throw new NoSuchElementException();
            }
            BSTBag.Node<E> place = track.pop();
            for (BSTBag.Node<E> curr = place.right; curr != null; curr = curr.left) {
                track.push(curr);
            }
            return place.element.getElement();
        }
    }
}
