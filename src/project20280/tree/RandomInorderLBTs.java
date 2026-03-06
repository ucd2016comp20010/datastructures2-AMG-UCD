package project20280.tree;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.Timer;

public class RandomInorderLBTs {
    public static void main(String[] args) throws IOException {
        //Timer myTimer = new Timer();

        //LinkedBinaryTree<Integer> bt = LinkedBinaryTree.makeRandom(0);
        PrintWriter printWriter = new PrintWriter(new FileWriter("randAvgInorderData.csv"));
        for (int i = 10; i <= 10000; i++) {
            final int j = i;
            Runnable worker = () -> {
                LinkedBinaryTree<Integer> bt = LinkedBinaryTree.makeRandom(j);
                bt.inorder();
            };

            double result = Timer.measure(worker);
            printWriter.println(i +", "+ result);
        }
        printWriter.close();
    }
}