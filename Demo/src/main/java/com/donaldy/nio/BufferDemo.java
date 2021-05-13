package com.donaldy.nio;

import java.nio.ByteBuffer;

/**
 * @author donald
 * @date 2021/05/13
 */
public class BufferDemo {

    public static void main(String[] args) {

        byte[] data = new byte[]{0, 1, 2, 3};

        ByteBuffer buffer = ByteBuffer.wrap(data);

        System.out.println("capacity : " + buffer.capacity());
        System.out.println("position : " + buffer.position());
        System.out.println("limit : " + buffer.limit());
    }
}
