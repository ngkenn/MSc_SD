
//import classes for file input - scanner etc.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
//import implementing set (eg. TreeSet)

/*
Student Number: 2025066K
Name: Neil Kennedy
 */


public class WordProcessor {
	private static <E> String displaySet(Set<E> inputSet) {
		StringBuilder sb = new StringBuilder();
		int count = 0;

		for (E e : inputSet) {
			sb.append(e.toString());
			sb.append(", ");
			count++;

			// When 5 elements have been displayed, start a new line
			if (count == 5) {
				sb.append("\n");
				count = 0;
			}

		}

		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Scanner scan;
		//create a set of type String called wordSet
		Set<String> wordSet = new TreeSet<>();
		//create a set of type CountedElement<String> called countedWordSet 
		Set<CountedElement<String>> countedWordSet = new TreeSet<>();

		// For each of the filenames in the program arguments,
        // scan the contents and add to the set
		for(String fileName: args){
		    try{
		        scan = new Scanner(new File(fileName));
                while(scan.hasNext()){
                    String word = scan.next();
                    if(!wordSet.contains(word)){
                        wordSet.add(word);
                        CountedElement<String> elem = new CountedElement<>(word);
                        countedWordSet.add(elem);
                    }

                    // Otherwise, find the duplicate elements and increment
                    else{
                        Iterator<CountedElement<String>> iterator = countedWordSet.iterator();
                        while(iterator.hasNext()){
                            CountedElement<String> cE = iterator.next();
                            String e = cE.getElement();
                            if(e.compareTo(word) == 0){
                            	int cECount = cE.getCount();
                            	cE.setCount(cECount+1);
                            }
                        }
                    }
                }
                scan.close();
            }

            catch (FileNotFoundException e){
		        e.printStackTrace();
            }
        }



	System.out.println(displaySet(countedWordSet));

	}
}
