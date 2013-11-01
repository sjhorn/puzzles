package com.hornmicro.sort;


public abstract class AbstractSort {

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
    
    abstract void sort();
    
    public void dump() {
        for(int i: items) {
            System.out.print(i + ",");
            
        }
        System.out.println("");
    }
    
    
}
