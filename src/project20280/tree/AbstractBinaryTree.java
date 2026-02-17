package project20280.tree;

import project20280.interfaces.BinaryTree;
import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract base class providing some functionality of the BinaryTree interface.
 * <p>
 * The following five methods remain abstract, and must be implemented
 * by a concrete subclass: size, root, parent, left, right.
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E>
        implements BinaryTree<E> {

    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        for (Position<E> curr : positions()) {
            if (curr == p){
                if (left(parent(p)) == p) return right(parent(p));
                else if (right(parent(p)) == p) return left(parent(p));
                return null;       
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        for (Position<E> curr : positions()){
            if (curr == p) {
                int count = 0;
                for (Position<E> child : positions()) {
                    if (parent(child) == p) {
                        count++;
                    }
                }
                return count;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        for (Position<E> curr : positions()) {
             if (curr == p) {
                List<Position<E>> snapshot = new ArrayList<>(2);    // max capacity of 2
                if (left(p) != null) snapshot.add(left(p));
                if (right(p) != null) snapshot.add(right(p));
                return snapshot;
            }
        }
        throw new IllegalArgumentException();
    }

    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null) inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if (right(p) != null) inorderSubtree(right(p), snapshot);
    }

    public Iterable<Position<E>> inorder() {
        ArrayList<Position<E>> snapshot = new ArrayList<>( );
        if (!isEmpty()) inorderSubtree(root(), snapshot);
        return snapshot;

    }

    @Override
    public Iterable<Position<E>> positions() {
        return inorder();
    }
}

