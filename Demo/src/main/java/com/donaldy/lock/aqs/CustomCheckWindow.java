package com.donaldy.lock.aqs;

import java.util.concurrent.Semaphore;

public class CustomCheckWindow {

    public static void main(String[] args) {

        // 设定 3个信号量, 即 3个 服务窗口
        Semaphore semaphore = new Semaphore(3);

        // 这个队伍排了 5个人
        for (int i = 1; i <= 5; ++i) {

            new SecurityCheckThread(i, semaphore).start();
        }
    }

    private static class SecurityCheckThread extends Thread {

        private int seq;
        private Semaphore semaphore;

        SecurityCheckThread(int seq, Semaphore semaphore) {
            this.seq = seq;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("NO." + seq + " 乘客, 正在查验中");

                // 假设号码是整除2的人是身份可疑人员, 需要花更长时间来安检
                if (seq % 2 == 0) {

                    Thread.sleep(2000);
                    System.out.println("No." + seq + "乘客, 身份可疑, 不能出国!");
                }
            } catch (InterruptedException e) {

                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println("NO." + seq + " 乘客已完成服务.");
            }
        }
    }
}


