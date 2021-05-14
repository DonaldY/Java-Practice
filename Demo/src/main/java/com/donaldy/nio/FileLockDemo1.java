package com.donaldy.nio;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;

/**
 * @author donald
 * @date 2021/05/14
 */
public class FileLockDemo1 {

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("./hello.txt");
        FileChannel channel = in.getChannel();

        // true ： 共享锁， false ： 独占锁
        channel.lock(0, Integer.MAX_VALUE, true);

        // sleep 1小时
        Thread.sleep(60 * 60 * 1000);

        channel.close();
        in.close();
    }
}
