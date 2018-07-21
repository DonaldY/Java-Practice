package com.donaldy.concurrency.Immutable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DonaldY on 2018/7/22.
 */

/**
 * 不可修改的类
 * Collections.unmodifiableXXX: Collection, List, Set, Map...
 * 
 * Guava: ImmutableXXX: Collection, List, Set, Map..
 */
public class ImmutableExample1 {
    
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = new HashMap<>();
    
    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        System.out.println(map.get(1));
    }
    
    private void test(final int a) {
        
    }
}
