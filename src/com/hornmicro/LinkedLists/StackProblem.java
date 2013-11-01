package com.hornmicro.linkedlists;



import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class StackProblem {
	static class Stack1 extends LinkedList implements Stack {
		
		@Override
		public Object pop() throws IOException {
			if(head == null) {
				throw new IOException("Stack is empty");
			}
			LinkedListItem tmp = head;
			head = head.next;
			return tmp.data;
		}
		
		@Override
		public void push(Object o) {
			LinkedListItem item = new LinkedListItem();
			item.data = o;
			item.next = head;
			head = item;
		}
	}
	
	static class Stack2 implements Stack {
		ArrayList<Object> stack = new ArrayList<Object>();
		
		@Override
		public Object pop() throws IOException {
			if(stack.size() == 0) {
				throw new IOException("Stack is empty");
			}
			return stack.remove(0);
		}
		@Override
		public void push(Object o) {
			stack.add(0, o);
		}
	}

	static class Stack3 implements Stack {
		static final int BUFFER_INCREMENT = 1;
		Object[] stack = null;
		int top = 0;

		@Override
		public Object pop() throws IOException {
			if(top == 0) {
				throw new IOException("Stack is empty");
			}
			
			return stack[--top];
		}

		@Override
		public void push(Object o) {
			if(stack == null) {
				stack = new Object[BUFFER_INCREMENT];
			}
			if(top == stack.length) {
				Object[] newstack = new Object[stack.length+BUFFER_INCREMENT];
				System.arraycopy(stack, 0, newstack, 0, stack.length);
				stack = newstack;
			}
			stack[top++] = o;
		}	
	}
	
	
	public static class TestStacks {
		
		@Test
		public void testStack1() throws Exception {
			Stack s = new Stack3();
			testStack(s);
		}

		@Test
		public void testStack2() throws Exception {
			Stack s = new Stack3();
			testStack(s);
		}

		@Test
		public void testStack3() throws Exception {
			Stack s = new Stack3();
			testStack(s);
		}

		private void testStack(Stack s1) throws IOException {
			try {
				s1.pop();
				Assert.fail("Expected IOException on empty stack pop.");
			} catch(IOException ioe) { }
			s1.push("one");
			s1.push("two");
			Assert.assertEquals("two", s1.pop());
			s1.push("three");
			Assert.assertEquals("three", s1.pop());
			Assert.assertEquals("one", s1.pop());
			try {
				s1.pop();
				Assert.fail("Expected IOException on empty stack pop.");
			} catch(IOException ioe) { }
		}
	}
	
	public static void main(String[] args) throws Exception {
		JUnitCore runner = new JUnitCore();
//		runner.addListener(new RunListener() {
//			@Override
//			public void testStarted(Description description) throws Exception {
//				System.out.print("Test started:"+ description.getClassName()+"->"+description.getMethodName());
//			}
//			
//			@Override
//			public void testFinished(Description description) throws Exception {
//				System.out.println("..done");
//			}
//			
//			@Override
//			public void testFailure(Failure failure) throws Exception {
//				System.err.print("..failed:"+failure.toString());
//			}
//			
//		});
		
		runner.addListener(new TextListener(new RealSystem()));
		runner.run(TestStacks.class);
	}

}
