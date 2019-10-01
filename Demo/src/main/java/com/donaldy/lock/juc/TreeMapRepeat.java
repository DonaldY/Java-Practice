package com.donaldy.lock.juc;

import java.util.TreeMap;

public class TreeMapRepeat {

    public static void main(String[] args) {

        // 如果仅此处的 TreeMap 换成 HashMap，则 size = 1
        TreeMap map = new TreeMap();
        map.put(new Key(), "value one");
        map.put(new Key(), "value two");

        // TreeMap, size = 2, 因为 Key 去重规则是根据排序结果
        System.out.println(map.size());

        /*TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(55, "fifty-five");
        treeMap.put(56, "fifty-six");
        treeMap.put(57, "fifty-seven");
        treeMap.put(58, "fifty-eight");
        treeMap.put(83, "eighty-three");
        treeMap.remove(57);
        treeMap.put(59, "fifty-nine");*/
    }
}

class Key implements Comparable<Key> {

    // 返回负的常数，表示此对象永远小于收入的 other 对象，此处决定 TreeMap 的 size = 2
    @Override
    public int compareTo(Key other) {
        return -1;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
