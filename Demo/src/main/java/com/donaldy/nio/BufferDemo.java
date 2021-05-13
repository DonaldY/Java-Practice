package com.donaldy.nio;

import java.nio.ByteBuffer;

/**
 * @author donald
 * @date 2021/05/13
 */
public class BufferDemo {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(100);

        System.out.println("capacity : " + buffer.capacity());
        System.out.println("position : " + buffer.position());
        System.out.println("limit : " + buffer.limit());

        byte[] src = new byte[] {1, 2, 3, 4, 5};

        // 放置数据，移动 position
        buffer.put(src);

        // position = 0~4，都填充了数据
        System.out.println("position : " + buffer.position());

        // 此时 position = 5，此时如果你直接读数据，是读不到的，是空的没有数据。
        // 手动操作一下 position，调整到 position = 0的地方，开始读数据
        buffer.position(0);

        byte[] dst = new byte[5];

        // 将数据写到目标数组中
        buffer.get(dst);
        System.out.println("position : " + buffer.position());

        System.out.print("dst=[");
        for(int i = 0; i < dst.length; i++) {
            System.out.print(i);
            if(i < dst.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }
}
