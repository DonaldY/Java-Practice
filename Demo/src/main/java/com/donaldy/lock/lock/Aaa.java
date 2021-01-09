package com.donaldy.lock.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author donald
 * @date 2021/01/09
 */
public class Aaa {

    boolean flag = false;

    public static void main(String[] args) {

        Aaa a = new Aaa();

        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
