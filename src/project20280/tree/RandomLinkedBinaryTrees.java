package project20280.tree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RandomLinkedBinaryTrees {
    public static void main(String[] args) throws IOException {

        LinkedBinaryTree<Integer> bt = LinkedBinaryTree.makeRandom(0);

        PrintWriter printWriter = new PrintWriter(new FileWriter("randAvgHeightData.csv"));
        double heightsum = 0;
        for (int i = 50; i <= 5000; i += 50) {
            heightsum = 0;
            for (int j = 0; j < 100; ++j) {
                bt = LinkedBinaryTree.makeRandom(i);
                //System.out.println(bt.toBinaryTreeString());
                //System.out.println(bt.height());

                heightsum += bt.height();
            }
            heightsum /= 100;
            printWriter.println(i +", "+ heightsum);
        }
        printWriter.close();
    }
}