package com.hornmicro.sort;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class InsertionSort {
    public int[] items;
    
    // a quick dynamic array. should really alloc in bigger chunks ;)
    public void add(int number) {
        if(items != null) {
            int[] newItems = new int[items.length + 1];
            System.arraycopy(items, 0, newItems, 0, items.length);
            newItems[items.length] = number;
            items = newItems;
        } else {
            items = new int[] { number };
        }
    }
    
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
    
    public void dump() {
        for(int i: items) {
            System.out.print(i + ",");
            
        }
        System.out.println("");
    }
    
    public static class TestInsertionSort {
        InsertionSort is;
        
        @Before
        public void setUp() {
            is = new InsertionSort();
        }
        
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
            
            //is.dump();
        }
    }
    
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(new RealSystem()));
        runner.run(TestInsertionSort.class);
    }

}
