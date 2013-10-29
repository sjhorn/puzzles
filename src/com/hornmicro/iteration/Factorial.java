package com.hornmicro.iteration;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class Factorial {
    
    public int factorial1(int n) {
        if(n > 1) {
        	int fval = factorial1(n - 1);
        	fval = fval * n;
            return fval;
        } else {
            return 1;
        }
    }
    
    static class Snapshot {
        int n;
        int stage;
        int fval;
        
        public Snapshot(int stage, int n, int fval) {
        	this.fval = fval;
        	this.n = n;
        	this.stage = stage;
        }
    }
    
    public int factorial3(int n) {
        LinkedList<Snapshot> stack = new LinkedList<Snapshot>();
        int retVal = 0;
        
        stack.push(new Snapshot(0, n, 0));
        while (!stack.isEmpty()) {
        	Snapshot snap = stack.pop();
        	switch(snap.stage) {
        		case 0:
        			if(snap.n > 1) {
        				
        				// recurse
        				snap.stage = 1;
        				stack.push(snap);
        				stack.push(new Snapshot(0, snap.n - 1, 0));
        				continue;
        			} else {
        				retVal = 1;
        			}
        			break;
        		case 1:
        			snap.fval = retVal;
        			snap.fval *= snap.n;
        			retVal = snap.fval;
        			break;
        	}
        }
        return retVal;
    }

    
    public int factorial2(int n) {
    	int retVal = 0;
    	LinkedList<Snapshot> stack = new LinkedList<Snapshot>();
    	stack.push(new Snapshot(0, n, 0));
    	while (!stack.isEmpty()) {
    		Snapshot snap = stack.pop();
    		switch (snap.stage) {
    		case 0:
    			if(snap.n > 1) {
	    			snap.stage = 1;
	    			stack.push(snap);
	    			stack.push(new Snapshot(0, n - 1, 0));
	    			continue;
    			} else {
    				retVal = 1;
    			}
    			break;
    		case 1:
    			snap.fval = retVal;
    			snap.fval *= snap.n;
    			retVal = snap.fval;
    			break;
    		}
    	}
    	return retVal;
    }
    
    
    public static class TestFactorial {
        Factorial f;
        
        @Before
        public void setUp() {
            f = new Factorial();
        }
        
        @Test
        public void testF1() {
            assertTrue(f.factorial1(4) == 24);
        }
        
        @Test
        public void testF2() {
            assertTrue(f.factorial2(4) == 24);
        }
    }
    
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(new RealSystem()));
        runner.run(TestFactorial.class);
    }

}
