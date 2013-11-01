package com.hornmicro.sort;

import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;


public class MergeSort extends AbstractSort {
    
    public void sort() {
        merge(0, items.length / 2, items.length);
    }
    
    private void mergeSort(int left, int right) {
        
    }
    private void merge(int left, int center, int right) {
        int[] leftArray = new int[center - left + 2];
        int[] rightArray = new int[right - center + 1];
        for(int i = 0; i < leftArray.length; i++) {
            leftArray[i] = items[left + i];
        }
        leftArray[leftArray.length] = Integer.MAX_VALUE;
        for(int i = 0; i < rightArray.length; i++) {
            rightArray[i] = items[right + i];
        }
        rightArray[leftArray.length] = Integer.MAX_VALUE;
        int i = 0; 
        int j = 0;
        for(int k = left; k < right; k++) {
            if(leftArray[i] > rightArray[j]) {
                items[k] = leftArray[i];
                i++;
            } else {
                items[k] = rightArray[j];
                j++;
            }
        }
    }
    
    public static class TestSort {
        MergeSort is = new MergeSort();
        
        @Test
        public void testSort() {
            is.add(2);
            is.add(7);
            is.add(8);
            is.add(3);
            is.add(10);
            is.add(8);
            is.sort();
            is.dump();
            //assertArrayEquals(new int[] {2,3,7,8,8,10}, is.items);
        }
    }
    
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(new RealSystem()));
        runner.run(TestSort.class);
    }

}
