package com.michaelpollmeier.selection;

import java.util.Comparator;

public class MyComparator implements Comparator<Integer>{
    public int compare(Integer o1, Integer o2) {
       return o2.compareTo(o1);
    }
}
