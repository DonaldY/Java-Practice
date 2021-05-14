package com.donaldy.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author donald
 * @date 2021/05/14
 */
public class FileChannelDemo1 {

    public static void main(String[] args) throws IOException {
        // 构造一个传统的文件输出流
        FileOutputStream out = new FileOutputStream("./hello.txt");

        // 通过文件输出流获取到对应的FileChannel，以NIO的方式来写文件
        FileChannel channel = out.getChannel();

        // 把字符转为字节，并写入 buffer
        ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());
        channel.write(buffer);

        channel.close();
        out.close();
    }
}
