package com.donaldy.concurrency.Immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DonaldY on 2018/7/22.
 */
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2);

    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4)
            .put(5, 6).build();
    
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map3 = new HashMap<>();
    
    static {

    }

    public static void main(String[] args) {
        map.put(1, 3);
        System.out.println(map.get(1));
    }
    
    private void test(final int a) {
        
    }
}
