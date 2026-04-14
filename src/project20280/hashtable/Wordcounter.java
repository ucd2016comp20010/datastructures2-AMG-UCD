package project20280.hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import project20280.interfaces.Entry;
import project20280.list.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Wordcounter {
    public static void main(String []args) throws FileNotFoundException {
        File f = new File("/home/aidan-mac-gamhna/Documents/S2T2/Data Structures/datastructures2-AMG-UCD/src/project20280/hashtable/sample_text.txt"); // check the path to the file
        ChainHashMap<String, Integer> counter = new ChainHashMap<String, Integer>();

         // use a Scanner to read words from the file
        Scanner scanner = new Scanner(f);
        while(scanner.hasNext()) { // read the file word at a time
            String word = scanner.next().toLowerCase();
            //System.out.println("word:" + word);
            Integer count = counter.get(word);
            if (count == null) count = 0;
            counter.put(word, 1 + count);
        }

        ArrayList<Entry<String,Integer>> topTen = new ArrayList<>();
        for (Entry<String,Integer> ent : counter.entrySet()) {
            topTen.add(ent);
        }

        Collections.sort(topTen, (a, b) -> b.getValue().compareTo(a.getValue()));

        for (int i = 0 ; i < 10; i++) {
            System.out.println((i + 1) + " " + topTen.get(i).getKey() + " " + topTen.get(i).getValue());
        }
    }
}
