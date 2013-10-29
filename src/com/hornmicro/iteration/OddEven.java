package com.hornmicro.iteration;

import java.util.LinkedList;

public class OddEven {
	
	public static class StackEntry {
		int stage;
		//args, locals
		int n;
		boolean lvar;
		public StackEntry(int stage, int n) {
			this.stage = stage;
			this.n = n;
		}
	}
	
	static boolean isOddEvenLoop(int n, int stage) {
		boolean retVal = false;
		LinkedList<StackEntry> stack = new LinkedList<StackEntry>();
		stack.push(new StackEntry(stage, n));
		while (!stack.isEmpty()) {
			StackEntry local = stack.pop();
			switch(local.stage) {
			case 0:
				if(local.n == 0) {
					retVal = false;
				} else {
					stack.push(new StackEntry(1, local.n - 1));
				}
				break;
			case 1:
				if(local.n == 0) {
					retVal = true;
				} else {
					stack.push(new StackEntry(0, local.n - 1));
				}
				break;
			}
		}
		return retVal;
	}
	
	
	static boolean isOddLoop(int n) {
		return isOddEvenLoop(n, 0);
	}
	
	static boolean isEventLoop(int n) {
		return isOddEvenLoop(n, 1);
	}
	
	
	static boolean isOdd(int n) {
		if(n == 0) {
			return false;
		} else {
			boolean lvar = isEven( n - 1 );
			return lvar;
		}
	}
	
	static boolean isEven(int n) {
		if(n == 0) {
			return true;
		} else {
			return isOdd( n - 1);
		}
	}
	
	public static void main(String[] args) {
		int num = 6;
		
		System.out.println(isOdd(num));
		System.out.println(isOddLoop(num));
	}

}
