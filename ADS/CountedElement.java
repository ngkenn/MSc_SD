
/*
Student Number: 2025066K
Name: Neil Kennedy
 */

public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	private E element;
	private int count;

	public CountedElement(E e, int count){
		this.element = e;
		this.count = count;
	}
	
	public CountedElement(E e){
		this.element = e;
		this.count = 1;
	}

	public E getElement(){
		return this.element;
	}
	public int getCount(){
		return this.count;
	}

	@Override
	public String toString() {

		String elementStr = element.toString();
		String str = "("+elementStr+", "+count+")";

		return str;
	}

	/**
	 * Setters
	 */
	public void setElement(E e){
		this.element = e;
	}
	public void setCount(int c){
		this.count = c;
	}


	/**
	 * CompareTo method
	 * @param sC1
	 * @return
	 */
	public int compareTo(CountedElement<E> sC1) {
		int comp = this.element.compareTo(sC1.getElement());

    	if(comp > 0) return 1;

		else if(comp < 0) return -1;

		else return 0;

	}

}
