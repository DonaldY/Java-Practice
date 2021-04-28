package com.donaldy.jvm.fullgc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author donald
 * @date 2021/04/28
 */
public class Demo2 {
    public static long cnt = 0;
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
            System.out.println("添加第 " + (++cnt) + " 个对象");
        }
    }
}
