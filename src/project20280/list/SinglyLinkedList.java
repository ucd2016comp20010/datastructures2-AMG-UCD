package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            
        private Node<E> next;

        public Node(E e, Node<E> n) {
            this.next = n;
            this.element = e;
        }

        // Accessor methods

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    private Node<E> head = null;               // head node of the list (or null if empty)
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        Node<E> target = head;
        int counter = 0;
        
        while (counter < position) {
            target = target.getNext();
            counter++;
        }

        return target.getElement();
    }

    @Override
    public void add(int position, E e) {
        Node<E> newest = new Node<E>(e, null);
        Node<E> last = head;
        int counter = 0;

        while(counter > position - 1) {
            last = last.getNext();
            counter++;
        }

        newest.setNext(last.getNext());
        last.setNext(newest);
        size++;
    }

    @Override
    public void addFirst(E e) {
        head = new Node<E>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newest = new Node<E>(e, null);
        Node<E> last = head;
        if(last == null) {
            head = newest;
        }

        else {
            while (last.getNext() != null) { 
                last = last.getNext();
            }

            last.setNext(newest);
        }

        size++;
    }

    @Override
    public E remove(int position) {
        Node<E> last = head;
        Node<E> removed = null;
        int counter = 0;

        while(counter > position - 1) {
            last = last.getNext();
            counter++;
        }

        removed = last.getNext();
        last.setNext(removed.getNext());
        removed.setNext(null);

        size--;
        return removed.getElement();
    }

    @Override
    public E removeFirst() {
        Node<E> removed = head;
        head = head.getNext();

        return removed.getElement();
    }

    @Override
    public E removeLast() {
        Node<E> removed = null;
        Node<E> last = head;
        while (last.getNext() != null) {
            last = last.getNext(); 
        }

        removed = last.getNext();
        last.setNext(null);

        return removed.getElement();
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

    }
}
