package project20280.tree;

import project20280.interfaces.Position;
import project20280.stacksqueues.LinkedDeque;

import java.util.ArrayList;
import java.util.Arrays;
//import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.HashMap;
import java.util.Queue;

import javax.swing.tree.TreeNode;

import org.w3c.dom.NodeList;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    public static void main(String [] args) {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());
        System.out.println(bt.height());
        
        Integer [] inorder= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
        18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        Integer [] preorder= {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16,
        17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};

        LinkedBinaryTree<Integer> bs = new LinkedBinaryTree <>();
        bs.construct(inorder, preorder);
        System.out.println(bs.toBinaryTreeString());
        System.out.println(bs.diameter(bs.root));
        bs.printLeavesBase();
    }

    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; 
        if (node.getParent() == node)
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }
    /*
    public void insert(E e) {
        // TODO

    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        return null;
    }
        */

    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;

        
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) {
            throw new IllegalArgumentException("p already has a right child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");

        size += t1.size() + t2.size();

        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }

        if (!t2.isEmpty( )) {
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2) throw new IllegalArgumentException("p has two children");

        Node<E> child = (node.getLeft( ) != null ? node.getLeft( ) : node.getRight( ) );
        if (child != null) child.setParent(node.getParent( ));
        if (node == root) root = child;

        else {
            Node<E> parent = node.getParent( );
            if (node == parent.getLeft( )) parent.setLeft(child);
            else parent.setRight(child);
        }
        size--;
        E temp = node.getElement( );

        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);

        return temp;
    }

    public String toString() {
        return positions().toString();
    }
    
    /*
    public void createLevelOrder(ArrayList<E> l) {
        // TODO
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        // TODO
        return null;
    }
    */

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        if (i < arr.length) {
            Node<E> n = createNode(arr[i], p, null, null);
            n.left = createLevelOrderHelper(arr, n.left, 2 * i + 1);
            n.right = createLevelOrderHelper(arr, n.right, 2 * i + 2);
            ++size;
            return n;
        }
        return p;

    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }

    // DIAMETER ========================================================================================================================


    static int maxDiameter = 0;

    int diameterRecur(Node<E> root) {
        if (root == null)
            return 0;

        int lHeight = diameterRecur(root.left);
        int rHeight = diameterRecur(root.right);

        if (lHeight + rHeight > maxDiameter) {
            maxDiameter = lHeight + rHeight;
            }
        return 1 + Math.max(lHeight, rHeight);
    }

    int diameter(Node<E> root) {
        maxDiameter = 0; 
        diameterRecur(root);
        return maxDiameter;
    }


    // PREORDER INORDER BUILD ========================================================================================================================

    public void construct(E[] inorder, E[] preorder) {
        if (inorder == null || preorder == null || inorder.length != preorder.length) {
            throw new IllegalArgumentException("Invalid input arrays");
        }
        
        root = buildTree(preorder, 0, preorder.length - 1, 
                         inorder, 0, inorder.length - 1);
    }
    
    private Node<E> buildTree(E[] preorder, int preStart, int preEnd, E[] inorder, int inStart, int inEnd) {
        
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        
        E rootValue = preorder[preStart];
        Node<E> root = createNode(rootValue, null, null, null);
        int rootIndexInInorder = findInorderIndex(inorder, inStart, inEnd, rootValue);
        int leftSubtreeSize = rootIndexInInorder - inStart;
        
    
        root.setLeft(buildTree(preorder, preStart + 1, preStart + leftSubtreeSize, inorder, inStart, rootIndexInInorder - 1));
        root.setRight(buildTree(preorder, preStart + leftSubtreeSize + 1, preEnd, inorder, rootIndexInInorder + 1, inEnd));
        
        return root;
    }
    
    private int findInorderIndex(E[] inorder, int start, int end, E target) {
        for (int i = start; i <= end; i++) {
            if (inorder[i].equals(target)) {
                return i;
            }
        }
        throw new RuntimeException("Element not found in inorder traversal");
    }

// PATH TO LEAVES ================================================================================================================================================================

        public ArrayList<ArrayList<E>> rootToLeafPaths() {
        ArrayList<ArrayList<E>> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        findPaths(root, new ArrayList<>(), paths);
        return paths;
    }

    private void findPaths(Node<E> node, ArrayList<E> currentPath, ArrayList<ArrayList<E>> paths) {
        if (node == null) {
            return;
        }
        
        currentPath.add(node.getElement());
        
        if (node.getLeft() == null && node.getRight() == null) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            
            findPaths(node.getLeft(), currentPath, paths);
            findPaths(node.getRight(), currentPath, paths);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public void printLeavesBase() {
        leafNodes(root);
    }

// RECURSIVE LEAF PRINTER =============================================================================================
    public void leafNodes(Node<E> r) {
        if(r!= null) {
            leafNodes(r.left);
            leafNodes(r.right);
 
            if(r.left == null && r.right == null) {
                System.out.println(r.element);
            }
        }
    }
}



