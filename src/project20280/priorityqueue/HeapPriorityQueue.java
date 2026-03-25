package project20280.priorityqueue;

/*
 */

import project20280.interfaces.Entry;
import project20280.list.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    public HeapPriorityQueue() {
        super();
    }

    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    public HeapPriorityQueue(K[] keys, V[] values) {
        super( );
        for (int j=0; j < Math.min(keys.length, values.length); j++) {
            heap.add(new PQEntry<>(keys[j], values[j]));
        }
        heapify( );
    }

    // protected utilities
    protected int parent(int j) {return (j - 1) / 2; }

    protected int left(int j) {return (2 * j) + 1;}

    protected int right(int j) {return (2 * j) + 2;}

    protected boolean hasLeft(int j) {return left(j) < heap.size( );}

    protected boolean hasRight(int j) {return right(j) < heap.size( );}

    protected void swap(int i, int j) {
        Entry<K,V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    protected void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) break;
            swap(j, p);
            j = p;
        }
    }

    protected void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
                    smallChildIndex = rightIndex;
                }
            }
            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0) {
                break;
            }
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    protected void heapify() {
        int startIndex = parent(size( ) - 1);
        for (int j=startIndex; j >= 0; j--) {
            downheap(j);
        }
    }

    // public methods
    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public Entry<K, V> min() {
        return heap.get(0);
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upheap(heap.size( ) - 1);
        return newest;
    }

    @Override
    public Entry<K, V> removeMin() {
        if (heap.isEmpty( )) return null;
        Entry<K,V> answer = heap.get(0);
        swap(0, heap.size( ) - 1);
        heap.remove(heap.size( ) - 1);
        downheap(0);
        return answer;
    }

    public String toString() {
        return heap.toString();
    }

    @SuppressWarnings("unused")
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            //System.out.println("-> " +left + ", " + j + ", " + right);
            Entry<K, V> e_left, e_right;
            e_left = left < heap.size() ? heap.get(left) : null;
            e_right = right < heap.size() ? heap.get(right) : null;
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                System.out.println("Invalid left child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                System.out.println("Invalid right child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);

        System.out.println("elements: " + rands);
        System.out.println("after adding elements: " + pq);

        System.out.println("min element: " + pq.min());

        pq.removeMin();
        System.out.println("after removeMin: " + pq);
        // [             1,
        //        2,            4,
        //   23,     21,      5, 12,
        // 24, 26, 35, 33, 15]
    }

    public static <E> void pqSort(DoublyLinkedList<E> S, HeapPriorityQueue<E,?> P) {
        int n = S.size( );
        for (int j=0; j < n; j++) {
            E element = S.removeFirst();
            P.insert(element, null); 
        }

        for (int j=0; j < n; j++) {
            E element = P.removeMin( ).getKey( );
            S.addLast(element);
        }
    }

    public static <E> HeapPriorityQueue<E,E> heapSort(E[] arr){
        HeapPriorityQueue<E,E> hpq = new HeapPriorityQueue<>();
        for (E i : arr) {
            hpq.insert(i,i);
        }

        for (int i = arr.length - 1; i > 1; i--) {
            hpq.swap(i, 0);
            hpq.downheap(0);    
        }

        return hpq;
    }
    
}