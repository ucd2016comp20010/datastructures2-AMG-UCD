package project20280.tree;

import project20280.interfaces.Position;
import project20280.interfaces.Tree;
import project20280.stacksqueues.LinkedQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public abstract class AbstractTree<E> implements Tree<E> {

    @Override
    public boolean isInternal(Position<E> p) {
        for (Position<E> curr : positions()) if (curr == p) return numChildren(p) > 0;
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isExternal(Position<E> p) {
        for (Position<E> curr : positions()) if (curr == p) return numChildren(p) == 0;
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    @Override
    public int numChildren(Position<E> p) {
        for (Position<E> curr : positions()) {
            if (curr == p) {
                int count = 0;
                for (Position<E> child : children(p)) count++;
                return count;
            }
        } 
        throw new IllegalArgumentException();
    }

    @Override
    public int size() {
        int count = 0;
        for (Position p : positions()) count++;
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    //---------- support for computing depth of nodes and height of (sub)trees ----------

    public int depth(Position<E> p) throws IllegalArgumentException {
        if (isRoot(p)) {return 0;} else {return 1 + depth(parent(p));}
    }

    
    private int heightBad() {             // works, but quadratic worst-case time
        int h = 0;
        for (Position<E> p : positions())
            if (isExternal(p))                // only consider leaf positions
                h = Math.max(h, depth(p));
        return h;
    }

    public int height_recursive(Position<E> p) {
        int h = 0;
        for (Position<E> pos: children(p)) {
            h = Math.max(h, height_recursive(pos) + 1);
        }

        return h;
    }

    public int height() throws IllegalArgumentException {
        return height_recursive(root());
    }

    //---------- support for various iterations of a tree ----------

    //---------------- nested ElementIterator class ----------------
    /* This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        } // return element!

        public void remove() {
            posIterator.remove();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override
    public Iterable<Position<E>> positions() {
        return preorder();
    }

    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> c : children(p)) {
            preorderSubtree(c, snapshot);
        }
    }
    
    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) preorderSubtree(root(), snapshot);
        return snapshot;
    }

    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p)) {
            postorderSubtree(c, snapshot);
        }
        snapshot.add(p);
    }

    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            postorderSubtree(root(), snapshot);
        return snapshot;
    }

    public Iterable<Position<E>> breadthfirst() {
        List<Position<E>> snapshot = new ArrayList<>( );
        if (!isEmpty()) {
            LinkedQueue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root( ));

            while (!fringe.isEmpty( )) {
                Position<E> p = fringe.dequeue( );
                snapshot.add(p);
                for (Position<E> c : children(p))
                fringe.enqueue(c);
            }
        }
        return snapshot;
    }
}