package com.hornmicro.binarytree;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.JUnitCore;
import org.junit.internal.TextListener;
import org.junit.internal.RealSystem;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import static org.junit.Assert.*;

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
    
    static class Snapshot {
        public Node<Integer> node;
        int stage;
        
        public Snapshot(int stage, Node<Integer> node) {
            this.stage = stage;
            this.node = node;
        }
    }
    
    public List<Integer> preOrderTraversal2() {
        List<Integer> list = new ArrayList<Integer>();
        
        LinkedList<Snapshot> stack = new LinkedList<Snapshot>();
        stack.push(new Snapshot(1, root));
        
        while (!stack.isEmpty()) {
            Snapshot snapshot = stack.pop();
            switch (snapshot.stage) {
                case 1:
                    list.add(snapshot.node.value);
                    
                    if(snapshot.node.left != null) {
                    	snapshot.stage = 2;
                        stack.push(snapshot);
                        stack.push(new Snapshot(1, snapshot.node.left));
                        continue;
                    }
                    break;
                case 2:
                    
                    if(snapshot.node.right != null) {
                        stack.push(new Snapshot(1, snapshot.node.right));
                        continue;
                    }
                    break;
            }
        }
        return list;
    }
    
    private void walkAndAdd(Node<Integer> item, List<Integer> list) {
        list.add(item.value);
        if (item.left != null) {
            walkAndAdd(item.left, list);
        }
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
        public void testPreorderTraversal() {
            System.out.println("");
            BTreePrinter.printNode(bt.root);
            
            List<Integer> list = bt.preOrderTraversal();
            System.out.println(list);
            assertArrayEquals(
                    new Integer[] { 7, 2, 2, 5, 4, 12, 8 }, 
                    list.toArray(new Integer[list.size()])
            );
        }
        
        @Test
        public void testPreorderTraversal2() {
            List<Integer> list = bt.preOrderTraversal2();
            System.out.println(list);
            assertArrayEquals(
                    new Integer[] { 7, 2, 2, 5, 4, 12, 8 }, 
                    list.toArray(new Integer[list.size()])
            );
        }
    }
    
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(new RealSystem()));
        runner.run(TestTree.class);
    }

}
