package cn.donaldy.threadExample;

/**
 * Created by DonaldY on 2018/5/24.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 线程之间不可见
 */
public class Example2 extends Thread{
    boolean  stop = false;
    int value = 0;
    public void run() {
        while (!stop) {
            value ++;
        }
    }
    
    public static void main(String []args) throws InterruptedException {
        Example2 t = new Example2();
        t.start();
        Thread.sleep(2000);
        t.stop = true;
        System.out.println("value = " + t.value + " stop:" + t.stop);
        Thread.sleep(2000);
        System.out.println("value = " + t.value + " stop:" + t.stop);
        t.join();
        List list = new ArrayList();
        list.size();
    }
    
}
