package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E extends Comparable<E>> implements List<E> {

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
        int counter = 0;

        while(counter < position - 1) {
            last = last.getNext();
            counter++;
        }

        Node<E> removed = last.getNext();
        last.setNext(removed.getNext());
        removed.setNext(null);

        size--;
        return removed.getElement();
    }

    @Override
    public E removeFirst() {

        if (head == null) {
            return null;
        }

        else {
            Node<E> removed = head;
            head = head.getNext();
            size--;
            return removed.getElement();
        }
    }

    @Override
    public E removeLast() {

        Node<E> start = head;

    if (start.getNext() != null) {
        while (start.getNext().getNext() != null) {
            start = start.getNext();
        }

        Node<E> removed = start.getNext();
        start.setNext(null);
        removed.setNext(null);
        size--;

        return removed.getElement();
        }

        else {
            head = null;
            size--;
            return start.getElement();
        }
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
        //System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();
        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<Integer>();


        ll.addFirst(4);
        ll.addFirst(2);
        ll.addFirst(0);

        l2.addFirst(5);
        l2.addFirst(3);
        l2.addFirst(1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        //System.out.println(ll);
        //ll.remove(5);
        //System.out.println(ll);
        SinglyLinkedList<Integer> result = ll.sortedMerged(l2);
        System.out.println(result);
        result.reverse();
        System.out.println(result);

    }

    SinglyLinkedList<E> sortedMerged(SinglyLinkedList<E> b) {
        SinglyLinkedList<E> merged = new SinglyLinkedList<E>();

        Node<E> currA = head;
        Node<E> currB = b.head;
        Node<E> dummyNode = new Node<E>(null, null);
        Node<E> temp = dummyNode;
        

        while (currA != null && currB != null) {
            // Compare elements of both lists and
            // link the smaller node to the merged list
            if (currA.element.compareTo(currB.element) < 0) {
                temp.next = currA;
                currA = currA.next;
            } else {
                temp.next = currB;
                currB = currB.next;
            }
            // Move the temporary pointer
            // to the next node
            temp = temp.next;
        }

        // If any list still has remaining
        // elements, append them to the merged list
        if (currA != null) {
            temp.next = currA;
        } else {
            temp.next = currB;
        }

        merged.head = dummyNode.next;

        return merged;
    }

    public void reverse() {

        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> changing = null;
        Node<E> follow = head.next;

        while (curr != null) {
            changing = curr;
            changing.setNext(prev);
            prev = curr;
            curr = follow;
            
            if (follow != null) {
                follow = follow.next;
            }
        }
        head = prev;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
        Node<E> tmp = head;
        while (tmp != null) {
            twin.addLast(tmp.getElement());
            tmp = tmp.next;
        }
        return twin;
    }
}
