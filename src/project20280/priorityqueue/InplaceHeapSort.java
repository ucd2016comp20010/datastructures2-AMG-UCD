package project20280.priorityqueue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import project20280.list.DoublyLinkedList;
import project20280.tree.LinkedBinaryTree;

public class InplaceHeapSort {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        PrintWriter printWriter = new PrintWriter(new FileWriter("InplaceHeapSortData.csv"));
        for (int i = 1000; i <= 1000000; i+=1000) {
            final int k = i;
            Runnable worker = () -> {
                Integer[] arr = new Integer[k];
                for (int j = 0; j < k; j++) {
                    arr[j] = random.nextInt(1, k);
                    }
                HeapPriorityQueue<Integer, Integer> hpq = HeapPriorityQueue.heapSort(arr);
            };

            double result = Timer.measure(worker);
            printWriter.println(i +", "+ result);
        }
        printWriter.close();
    }    
}

