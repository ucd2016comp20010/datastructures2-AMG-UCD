package project20280.priorityqueue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import project20280.list.DoublyLinkedList;
import project20280.tree.LinkedBinaryTree;

public class PQSorting {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        PrintWriter printWriter = new PrintWriter(new FileWriter("PQSortData.csv"));
        for (int i = 1000; i <= 1000000; i+= 1000) {
            final int k = i;
            Runnable worker = () -> {
                DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
                for (int j = 0; j < k; j++) {
                    dll.addLast(random.nextInt(1, k));
                    }
                HeapPriorityQueue<Integer, Integer> hpq = new HeapPriorityQueue<>();
                HeapPriorityQueue.pqSort(dll, hpq);
            };

            double result = Timer.measure(worker);
            printWriter.println(i +", "+ result);
        }
        printWriter.close();
    }    
}
