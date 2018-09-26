package com.donaldy.concurrency.Immutable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DonaldY on 2018/7/22.
 */

/**
 * 不可变对象需要满足的条件：
 * 1. 对象创建以后其状态就不能修改
 * 2. 对象所有域都是final类型
 * 3. 对象是正确创建的（在对象创建期间，this引用没有逸出）
 */

/**
 * final关键字：类、方法、变量
 * 修饰类：不能被继承
 * 修改方法：1. 锁定方法不被继承类修改； 2.效率
 * 修饰变量：基本数据类型变量、引用类型变量
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
