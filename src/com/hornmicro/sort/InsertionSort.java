package com.hornmicro.sort;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class InsertionSort extends AbstractSort {
    
    public void sort() {
        for (int i = 1; i < items.length; i++) {
            int key = items[i];
            int j = i - 1;
            for(; j >= 0 && items[j] > key; j--) {
                items[j+1] = items[j];
            }
            items[j+1] = key;
        }
     }
    
    public static class TestSort {
        InsertionSort is = new InsertionSort();
        
        @Test
        public void testSort() {
            is.add(2);
            is.add(7);
            is.add(8);
            is.add(3);
            is.add(10);
            is.add(8);
            is.sort();
            assertArrayEquals(new int[] {2,3,7,8,8,10}, is.items);
        }
    }
    
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(new RealSystem()));
        runner.run(TestSort.class);
    }

}
