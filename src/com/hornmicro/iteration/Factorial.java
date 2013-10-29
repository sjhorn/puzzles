package com.hornmicro.iteration;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class Factorial {
    
    public int factorial1(int n) {
        if(n > 1) {
            return n * factorial1(n - 1);
        } else {
            return 1;
        }
    }
    
    static class Snapshot {
        int retN;
        int n;
    }
    
    public int factorial2(int n) {
        return 0;
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
