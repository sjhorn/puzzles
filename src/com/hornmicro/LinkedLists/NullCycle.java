package com.hornmicro.linkedlists;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class NullCycle {

	static class LLItem  {
		public LLItem next;
		public int value;
		
		public LLItem(int value, LLItem next) {
			this.value = value;
			this.next = next;
		}
	}
	
	static class LList {
		public LLItem head;
	}
	
	public static boolean isAcyclic(LList list) {
		if(list.head == null) {
			return true;
		}
		LLItem slow = list.head;
		LLItem fast = list.head.next;
		while (true) {
			if(fast == null || fast.next == null) {
				return true;
			} else if (fast.equals(slow) || fast.next.equals(slow)){
				return false;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
	}
	
	
	public static class TestNullCycle {
		LList aclist;
		LList clist;
		
		private void dump() {
			System.out.println("\n\n");
			LList[] lists = new LList[] { aclist, clist };
			for(LList list : lists) {
				LLItem next = list.head;
				System.out.print("[");
				for(int i = 0; i < 6 && next != null; i++) {
					if(i > 0) {
						System.out.print(",");
					}
					System.out.print(next.value);
					next = next.next;
				}
				System.out.println("]");
			}
		}
		
		@Before 
		public void setUp() {
			aclist = new LList();
			LLItem[] acitems = new LLItem[5];
			acitems[4] = new LLItem(2, null);
			acitems[3] = new LLItem(6, acitems[4]);
			acitems[2] = new LLItem(4, acitems[3]);
			acitems[1] = new LLItem(2, acitems[2]);
			acitems[0] = new LLItem(3, acitems[1]);
			aclist.head = acitems[0];
			
			clist = new LList();
			LLItem[] citems = new LLItem[5];
			citems[4] = new LLItem(2, null);
			citems[3] = new LLItem(6, citems[4]);
			citems[2] = new LLItem(4, citems[3]);
			citems[1] = new LLItem(2, citems[2]);
			citems[0] = new LLItem(3, citems[1]);
			citems[4].next = citems[2];
			clist.head = citems[0];		
		}
		
		@Test
		public void testMe() {
			dump();
			
			assertTrue(isAcyclic(aclist));
			assertFalse(isAcyclic(clist));
		}
	}
	
	public static void main(String[] args) {
		JUnitCore runner = new JUnitCore();
		runner.addListener(new TextListener(new RealSystem()));
		runner.run(TestNullCycle.class);
	}

}
