package binarytree;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.JUnitCore;
import org.junit.internal.TextListener;
import org.junit.internal.RealSystem;
import java.util.List;
import java.util.ArrayList;

public class BinaryTree {
    public Node<Integer> root;
    
    public void add(int item) {
        if (root == null) {
            root = new Node<Integer>(item);
            return;
        }
        addOrWalk(root, item);
    }
    
    private void addOrWalk(Node<Integer> item, int value) {
        if (item.value.compareTo(value) < 0) {
            if (item.right == null) {
                item.right = new Node<Integer>(value);
                return;
            }
            addOrWalk(item.right, value);
        } else {
            if (item.left == null) {
                item.left = new Node<Integer>(value);
                return;
            }
            addOrWalk(item.left, value);
        }
    }
    
    public List<Integer> preOrderTraversal() {
        List<Integer> list = new ArrayList<Integer>();
        walkAndAdd(root, list);
        return list;
    }
    
    private void walkAndAdd(Node<Integer> item, List<Integer> list) {
        if (item.left != null) {
            walkAndAdd(item.left, list);
        }
        list.add(item.value);
        if (item.right != null) {
            walkAndAdd(item.right, list);
        }
    }
    
    public static class TestTree {
        BinaryTree bt;
        
        @Before
        public void setUp() {
            bt = new BinaryTree();
            bt.add(7);
            bt.add(2);
            bt.add(5);
            bt.add(2);
            bt.add(12);
            bt.add(4);
            bt.add(8);
        }
        
        @Test
        public void testMe() {
            System.out.println("");
            BTreePrinter.printNode(bt.root);
            System.out.println(bt.preOrderTraversal());
        }
    }
    
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(new RealSystem()));
        runner.run(TestTree.class);
    }

}
