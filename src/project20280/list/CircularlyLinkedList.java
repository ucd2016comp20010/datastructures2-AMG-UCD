package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {
        
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        Node<E> curr = tail.getNext();
        int counter = 0;

        while (counter < i) {
            curr = curr.getNext();
            counter++;
        }
        
        return curr.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (isEmpty() && i <= 0) {
            tail = new Node<E>(e, null);
            tail.setNext(tail);
            size++;
            return;
        }

        else if(i <= 0) {
            tail.setNext(new Node<E>(e, tail.getNext()));
            size++;
            return;
        }

        if (isEmpty() || i > size) return;

        Node<E> curr = tail;  
        int counter = 0;  
        while (counter < i) {
            curr = curr.getNext();
            counter++;
        }
            
        curr.setNext(new Node<E>(e, curr.getNext()));
        if (i == size) {
            tail = curr.getNext();
        }
        size++;
    }

    @Override
    public E remove(int i) {

        if (isEmpty()) return null;

        Node<E> curr = tail;
        int counter = 0;

        while (counter < i) {
            curr = curr.getNext();
            counter++;
        }

        Node<E> removed = curr.getNext();
        curr.setNext(removed.getNext());
        removed.setNext(null);
        size--;

        return removed.getData();
    }

    public void rotate() {
        tail = tail.getNext();
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        add(size, e);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
