package com.donaldy.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author donald
 * @date 2021/05/14
 */
public class FileChannelDemo3 {

    public static void main(String[] args) throws Exception {
        // hello world
        FileInputStream in = new FileInputStream("./hello.txt");
        FileChannel channel = in.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(11);
        // 读数据写入buffer，所以写完以后，buffer的position = 11
        channel.read(buffer);

        // position = 0，limit = 11
        buffer.flip();
        byte[] data = new byte[11];
        buffer.get(data);

        System.out.println(new String(data));

        channel.close();
        in.close();
    }
}
