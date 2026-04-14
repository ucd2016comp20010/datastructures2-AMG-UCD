package project20280.hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import project20280.interfaces.Entry;

public class Q6 {
    public static void main(String []args) throws FileNotFoundException {
        File f = new File("/home/aidan-mac-gamhna/Documents/S2T2/Data Structures/datastructures2-AMG-UCD/src/project20280/hashtable/words.txt"); // check the path to the file
        //int lowCols = 9999999;

        //for (int i = 0; i <= 31; ++i) {
            ChainHashMap<Integer, Integer> counter = new ChainHashMap<Integer, Integer>();
            // use a Scanner to read words from the file
            Scanner scanner = new Scanner(f);
            while(scanner.hasNext()) { // read the file word at a time
                String word = scanner.next().toLowerCase();
                //System.out.println("word:" + word);
                int curHash = hashCode(word);
                Integer count = counter.get(curHash);
                if (count == null) count = 0;
                counter.put(curHash, 1 + count);
            }

            int collisions = 0;

            for (Entry<Integer,Integer> ent : counter.entrySet()) {
                if (ent.getValue() > 1) {collisions += ent.getValue();}
            }

            //lowCols = Math.min(lowCols, collisions);
            //System.out.println(lowCols);

        //}
        System.out.println(lowCols);
    }

    public static int main_hash_poly(String s, int a) {
        int h = 0;
        int n = s.length();
        for(int i=0; i<n; i++) {
            char s_i = (char) s.charAt(i);
            int v = s_i * ((int) Math.pow(a,n-i-1));
            h += v;
        }
        return h;
    }

    public static int main_hash_cyclic(String s, int shift) {
        int h = 0;
        //int shift = 5;
        for(int i = 0; i < s.length(); ++i) {
            h = (h << shift) | (h >>> (32-shift));
            h += (int) s.charAt(i);
            }
        return h;
    }

    public static int hashCode(String s) {
        int hash = 0;
        int skip = Math.max(1, s.length() / 8);
        for (int i = 0; i < s.length(); i += skip) {
            hash = (hash * 37) + s.charAt(i);
        }
        return hash;
    }
}
