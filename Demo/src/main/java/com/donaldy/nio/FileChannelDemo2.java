package com.donaldy.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author donald
 * @date 2021/05/14
 */
public class FileChannelDemo2 {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        // 构造一个传统的文件输出流
        FileOutputStream out = new FileOutputStream("./hello.txt");

        // 通过文件输出流获取到对应的FileChannel，以NIO的方式来写文件
        FileChannel channel = out.getChannel();

        for(int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());
                    channel.write(buffer);
                    channel.force(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
