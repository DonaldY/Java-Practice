package com.donaldy.lock.juc;

import java.util.HashMap;

public class HashMapSimpleResize {

    private static HashMap map = new HashMap();

    public static void main(String[] args) {

        // 扩容的阈值是 table.length * 0.75
        // 第一次扩容发生在第13个元素置入时
        for (int i = 0; i < 13; ++i) {
            map.put(new UserKey(), new Object());
        }
    }
}

class UserKey {

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        return false;
    }
}