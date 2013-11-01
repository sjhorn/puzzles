package com.hornmicro.linkedlists;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class ListTailProblem extends LinkedList {
    public LinkedListItem tail;
    
    public void dump() {
    	ArrayList<Object> list = new ArrayList<Object>();
    	LinkedListItem item = head;
    	int iterations = 0;
    	while(item != null && iterations < 10) {
    		list.add(item.data);
    		item = item.next;
    		iterations++;
    	}
    	System.out.println(list);
    }
    
    public boolean add(Object data) {
        LinkedListItem item = new LinkedListItem();
        item.data = data;
        add(item);
        return true;
    }

    public boolean add(LinkedListItem item) {
        if (head == null || tail == null) {
            head = item;
            tail = item;
            item.next = null;
        } else {
            tail.next = item;
            tail = item;
        }
        item.next = null;
        return true;
    }

	public boolean remove(LinkedListItem item) {
        if (head == null) {
            return false;
        }
        if (head.equals(item)) {
            head = head.next;
            if(head == null) {
                tail = null;
            }
            return true;
        }
        LinkedListItem curItem = head;
        while (curItem.next != null) {
        	LinkedListItem nextItem = curItem.next;
            if (nextItem.equals(item)) {
                if(nextItem == tail) {
                    tail = curItem;
                    curItem.next = null;
                } else {
                    curItem.next = nextItem.next;
                }
                return true;
            }
            curItem = nextItem;
        }
		return false;
	}
	
    public boolean insertAfter(LinkedListItem item, Object data) {
    	
    	// Empty linked list
    	if(head == null) {
    		if(item != null) {
    			return false;
    		} else {
    			head = tail = new LinkedListItem(data);
    			return true;
    		}
    	}
    	
    	// item is null insert at front
    	if(item == null) {
    		LinkedListItem curHead = head;
    		head = new LinkedListItem(data);
    		head.next = curHead;
    		return true;
    	}
    	
    	// item is head
    	if (head.equals(item)) {
    		LinkedListItem afterHead = head.next;
            head.next = new LinkedListItem(data);
            head.next.next = afterHead;
            return true;
        }
    	
    	// search for the item
    	LinkedListItem curItem = head;
        while (curItem.next != null) {
        	LinkedListItem nextItem = curItem.next;
            if (nextItem.equals(item)) {
                if(nextItem == tail) {
                	tail = new LinkedListItem(data);
                	nextItem.next = tail;
                } else {
                    curItem.next = new LinkedListItem(data);
                    curItem.next.next = nextItem;
                }
                return true;
            }
            curItem = nextItem;
        }
        return false;
    }

    static public class TestTail {
        final LinkedListItem one = new LinkedListItem("one");
        final LinkedListItem two = new LinkedListItem("two"); 
        final LinkedListItem three = new LinkedListItem("three");
        final LinkedListItem four = new LinkedListItem("four");
        
        ListTailProblem ltp;
        
        @Before 
        public void setUp() {
        	ltp = new ListTailProblem();
        	ltp.add(one);
            ltp.add(two);
            ltp.add(three);
        }

        @Test
        public void testAdd() {
            Assert.assertTrue(ltp.head.equals(one));
            Assert.assertTrue(ltp.tail.equals(three));
        }
        
        @Test
        public void testInsertAfter() {
        	
        	// Insert after head
        	Assert.assertTrue(ltp.insertAfter(one, "test-after-one"));
        	Assert.assertTrue(ltp.head.next.data.equals("test-after-one"));
        	
        	// Insert at head
        	Assert.assertTrue(ltp.insertAfter(null, "test-at-front"));
        	Assert.assertTrue(ltp.head.data.equals("test-at-front"));
        	Assert.assertTrue(ltp.head.next.data.equals("one"));
        	
        	// Insert after tail
        	Assert.assertTrue(ltp.insertAfter(three, "test-at-back"));
        	Assert.assertTrue(ltp.tail.data.equals("test-at-back"));
        	
        	// Test attempt to insert after non-member
        	Assert.assertFalse(ltp.insertAfter(four, "no-can-do"));
        	
        	ListTailProblem ltp2 = new ListTailProblem();
        	
        	// Insert at head in empty list with element specified
        	Assert.assertFalse(ltp2.insertAfter(four, "what-the"));
        	
        	// Insert at head with null
        	Assert.assertTrue(ltp2.insertAfter(null, "all-good"));
        	Assert.assertTrue(ltp2.head.data.equals("all-good"));
        	Assert.assertTrue(ltp2.tail.data.equals("all-good"));
        	
        	ltp2.dump();
        	
        }
        
        @Test
        public void testRemove() {

        	// Check removing head
        	Assert.assertTrue(ltp.remove(one));
        	Assert.assertTrue(ltp.head.equals(two));
        	Assert.assertTrue(ltp.tail.equals(three));
        	
        	// Check removing tail
        	Assert.assertTrue(ltp.remove(three));
        	Assert.assertTrue(ltp.head.equals(two));
        	Assert.assertTrue(ltp.tail.equals(two));
        	
        	// Check removing item that dont exist
        	Assert.assertFalse(ltp.remove(three));
        	Assert.assertFalse(ltp.remove(one));
        	
        	
        	// Check add again after removing
        	Assert.assertTrue(ltp.add(one));
        	Assert.assertTrue(ltp.head.equals(two));
        	Assert.assertTrue(ltp.tail.equals(one));
        	
        	// Check emptying list 
        	Assert.assertTrue(ltp.remove(two));
        	Assert.assertTrue(ltp.remove(one));
        	
        	Assert.assertTrue(ltp.head == null);
        	Assert.assertTrue(ltp.tail == null);
        }
    }

    public static void main(String[] args) throws Exception {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(new RealSystem()));
        runner.run(TestTail.class);
    }
}
