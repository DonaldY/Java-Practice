package cn.donaldy.threadExample;

/**
 * Created by DonaldY on 2018/5/18.
 */

/**
 * 多线程编程的运行结果可能依赖于时序，多次运行的结果并不稳定
 *
 * Thread.yield() , 通知调度器：当前线程想要让出对处理器的占用
 *
 * Thread.join(), 在某些情况下，主线程创建并启动了子线程，
 * 如果子线程中需要进行大量的耗时运算，主线程往往将早于子线程结束之前结束，
 * 如果主线程想等待子线程执行完毕后，获得子线程中的处理完的某个数据，就要用到join方法了，方法join（）的作用是等待线程对象呗销毁。
 */
public class Example1 {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread() {
            public void run() {
                System.out.println("Hello from new thread");
            }
        };
        myThread.start();
        Thread.yield();
        System.out.println("Hello from main thread");
        myThread.join();
    }
}