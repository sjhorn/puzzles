package com.hornmicro.linkedlists;


import org.junit.Before;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class ListFlattening {
	
	static class DLinkedList {
		public Node head;
		public Node tail;
	}
	
	static class Node {
		Node next;
		Node prev;
		Node child;
		int value;
	}
	
	public DLinkedList flatten(DLinkedList list) {
		DLinkedList flat = new DLinkedList();
		flat.head = list.head;
		flat.tail = list.tail;
		Node curPos = list.head;
		while(curPos.next != null) {
			if(curPos.child != null) {
				flat.tail.next = curPos.child;
				curPos.child.prev = flat.tail;
				while(flat.tail.next != null) {
					flat.tail = flat.tail.next;
				}
			}
			curPos = curPos.next;
		}
		flat.tail = curPos;
		return flat;
	}
	
	public DLinkedList unflatten(DLinkedList list) {
		DLinkedList original = new DLinkedList();
		original.head = list.head;
		
		exploreAndSeparate(original.head);
		
		Node curPos = original.head;
		while(curPos.next != null) {
			curPos = curPos.next;
		}
		original.tail = curPos;
		return original;
	}
	
	private void exploreAndSeparate(Node child) {
		
		Node curNode = child;
		while (curNode != null) {
			System.out.println("Visiting "+ curNode.value);
			if(curNode.child != null) {
				if(curNode.child.prev != null) {
					curNode.child.prev.next = null;
					curNode.child.prev = null;
				}
				exploreAndSeparate(curNode.child);
			}
			curNode = curNode.next;
		}
	}
	
	
	public static class TestFlattening {
		DLinkedList list;
		Node[] nodes;
		private void setupNode(int idx,  int prev, int next, int child, int value) {
			nodes[idx].prev = prev > 0 ? nodes[prev] : null;
			nodes[idx].next = next > 0 ? nodes[next] : null;
			nodes[idx].child = child > 0 ? nodes[child] : null;
			nodes[idx].value = value;
		}
		
		@Before
		public void setUp() {
			nodes = new Node[17];
			for(int i = 0; i < 17; i++) {
				nodes[i] = new Node();
			}

			// Top Level
			setupNode( 0, -1,  1,  5, 5);
			setupNode( 1,  0,  2, -1, 33);
			setupNode( 2,  1,  3, -1, 17);
			setupNode( 3,  2,  4,  8, 2);
			setupNode( 4,  3, -1, -1, 1);
			
			// Second Level
			setupNode( 5, -1,  6, -1, 6);
			setupNode( 6,  5,  7, 10, 25);
			setupNode( 7,  6, -1, 11, 6);
			
			setupNode( 8, -1,  9, 12, 2);
			setupNode( 9,  8, -1, -1, 7);
			
			// Third Level
			setupNode(10, -1, -1, -1, 8);
			
			setupNode(11, -1, -1, 14, 9);
			
			setupNode(12, -1, 13, 15, 12);
			setupNode(13, 13, -1, -1, 5);
			
			// Forth Level
			setupNode(14, -1, -1, -1, 7);
			
			setupNode(15, -1, 16, -1, 21);
			setupNode(16, 15, -1, -1, 3);
			
			list = new DLinkedList();
			list.head = nodes[0];
			list.tail = nodes[4];
			
		}
		
		private void dump(Node head, boolean children) {
			Node item = head;
			System.out.print("\n[");
	    	while(item != null) {
	    		System.out.print(item.value + ", ");
	    		if(item.child != null && children) {
	    			dump(item.child, true);
	    		}
	    		item = item.next;
	    	}
	    	System.out.print(" ]");
		}
		
		@Test
		public void testFlattening() {
			dump(list.head, true);
			System.out.println("\n\n----\n\n");
			DLinkedList newList = new ListFlattening().flatten(list);
			dump(newList.head, false);
			System.out.println("\n\n----\n\n");
			dump(new ListFlattening().unflatten(newList).head, true);
		}
	}
	
	public static void main(String[] args) {
		JUnitCore runner = new JUnitCore();
		runner.addListener(new TextListener(new RealSystem()));
		runner.run(TestFlattening.class);
	}

}
