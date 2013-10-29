package com.hornmicro.iteration;

import java.util.LinkedList;

public class FibNum {
	static class StackEntry {
		int stage;
		int n;
		int lvar;
		
		// args
		// localvars
		public StackEntry(int stage, int n) {
			this.stage = stage;
			this.n = n;
		}
	}
	
	public static int fibNum1(int n) {
		if(n == 1 || n == 2) {
			return 1;
		} else if (n < 1) {
			return -1;
		} else {
			int lvar = fibNum1(n - 1);
			lvar += fibNum1(n - 2);
			return lvar;
		}
	}
	
	
	public static int fibNum2(int n) {
		LinkedList<StackEntry> stack = new LinkedList<StackEntry>();
		int retVal = 0;
		stack.push(new StackEntry(0, n));
		
		while (!stack.isEmpty()) {
			StackEntry local = stack.pop();
			switch(local.stage) {
			case 0:
				if(local.n == 1 || local.n == 2) {
					retVal = 1;
				} else if(local.n < 1) {
					retVal = -1;
				} else {
					local.stage = 1;
					stack.push(local);
					stack.push(new StackEntry(0, local.n - 1));
				}
				break;
			case 1:
				local.lvar = retVal;
				local.stage = 2;
				stack.push(local);
				stack.push(new StackEntry(0, local.n - 2));
				break;
			case 2:
				local.lvar += retVal;
				retVal = local.lvar;
				break;
			}
		}
		return retVal;
	}
	
	
	
	public static void main(String[] args) {
		int num = 7;
		System.out.println( fibNum1(num) );
		System.out.println( fibNum2(num) );
	}

}
