package com.donaldy.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Field f = Unsafe.class.getDeclaredField("theUnsafe");

        f.setAccessible(true);

        Unsafe unsafe = (Unsafe) f.get(null);

    }
}
