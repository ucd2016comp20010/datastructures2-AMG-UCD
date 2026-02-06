package project20280.stacksqueues;

import project20280.interfaces.Stack;
import project20280.list.DoublyLinkedList;

public class LinkedStack<E> implements Stack<E> {

    DoublyLinkedList<E> ll;

    public static void main(String[] args) {

        System.out.println("10111".equals(convertToBinary(23)));
        System.out.println("111001000000101011000010011101010110110001100010000000000000".equals(convertToBinary(1027010000000000000L)));
    }

    public LinkedStack() {
        ll = new DoublyLinkedList<E>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void push(E e) {
        ll.addFirst(e);
    }

    @Override
    public E top() {
        return ll.get(0);
    }

    @Override
    public E pop() {
        return ll.removeFirst();
    }

    public String toString() {
        return ll.toString();
    }

    static String convertToBinary(long dec_num) {
        StringBuilder bin = new StringBuilder();

        while (dec_num > 0) {
            long bit = dec_num % 2;
            bin.append((char) ('0' + bit));
            dec_num /= 2;
        }
        
        bin.reverse();
        return bin.toString();

    }
}
