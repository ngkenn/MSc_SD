import javax.xml.bind.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordProcessor2 {


    /**
     * @param args
     */
    public static void main(String[] args) {
        BSTBag<String> bag = new BSTBag<>();
        BSTBag<String> bag1 = new BSTBag<>();
        BSTBag<String> bag2 = new BSTBag<>();
        Scanner scan;

        for (String fileName : args) {
            try {
                scan = new Scanner(new File(fileName));
                while (scan.hasNext()) {
                    String word = scan.next();

                    bag.add(word);

                }
                scan.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(bag.contains("xylophone")){
            System.out.println("Yeah boiiii");
        }
     //   System.out.println(bag.contains("scoobyDoo"));
        System.out.println(bag.contains("scoobyDoo"));
    //    bag.add("scoobyDoo");

        bag.add("scoobyDoo");
        System.out.println(bag.contains("scoobyDoo"));
        bag.remove("scoobyDoo");
        System.out.println(bag.contains("scoobyDoo"));

        bag.add("scoobyDoo");
        bag.add("scoobyDoo");


        bag2.add("Wizards");
        System.out.println("Equals: "+ bag.equals(bag2));

        bag1.add("Wizards");
        System.out.println(bag1.equals(bag2));

    }
}






