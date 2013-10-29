package com.hornmicro.LinkedLists;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class MtoLast extends ListTailProblem {

	public Object mToLast(int m) {
		LinkedListItem curItem = head;
		LinkedListItem mItem = null;
		while (curItem != null) {
			if(m == 0) {
				mItem = head;
			} else if (m < 0) {
				mItem = mItem.next;
			}
			
			if(m >= 0) {
				m--;
			}
			curItem = curItem.next;
		}
		return mItem;
	}
	
	 static public class TestMToLast {
	        final LinkedListItem one = new LinkedListItem("one");
	        final LinkedListItem two = new LinkedListItem("two"); 
	        final LinkedListItem three = new LinkedListItem("three");
	        final LinkedListItem four = new LinkedListItem("four");
	        
	        MtoLast list;
	        
	        @Before 
	        public void setUp() {
	        	list = new MtoLast();
	        	list.add(one);
	            list.add(two);
	            list.add(three);
	        }
	        
	        @Test
	        public void testMToLast() {
	        	Assert.assertTrue(list.mToLast(3) == null);
	        	Assert.assertTrue(list.mToLast(2).equals(one));
	        	Assert.assertTrue(list.mToLast(1).equals(two));
	        	Assert.assertTrue(list.mToLast(0).equals(three));
	        }
	 }
	
	
	public static void main(String[] args) {
		JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(new RealSystem()));
        runner.run(TestMToLast.class);
	}

}
