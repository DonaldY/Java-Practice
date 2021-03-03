
## 一、题目

1. 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
2. 两个线程交替打印 0~100 的奇偶数
3. 通过 N 个线程顺序循环打印从 0 至 100
4. 多线程按顺序调用，A->B->C，AA 打印 5 次，BB 打印10 次，CC 打印 15 次，重复 10 次
5. 用两个线程，一个输出字母，一个输出数字，交替输出 1A2B3C4D...26Z

其实这类题目考察的都是 **线程间的通信** 问题


## 二、思路


### 1. 三个线程分别打印 A，B，C


流程：
1. `Input`：打印次数 N
2. `Process`：调度
3. `Output`：输出打印的字符串


分别启动线程 A、B、C，


线程的状态：运行、等待、唤醒


可以用一个计数器来判断是否输出，例如：
1. cur % 3 == 0, 输出 A
2. cur % 3 == 1, 输出 B
3. cur % 3 == 2, 输出 C


主要步骤：当线程运行到的时候判断下，若需要执行就输出，若不是则等待，同时唤醒其他线程
> 为了顺序打印，需要一个公共变量进行控制。


举个栗子：
```
初始条件，cur = 1

1. 线程A 先运行，判断 cur % 3 ！= 0, 那么线程A 等待，同时唤醒其他线程
```


线程什么时候结束？
> 任务跑完了，就结束了。



```java
public class PrintABCWithWaitNotify {
    private int state; // 状态
    private int times; // 次数
    private static final Object LOCK = new Object(); // 锁

    private PrintABCWithWaitNotify(int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        // 打印 10 轮
        PrintABCWithWaitNotify printABC = new PrintABCWithWaitNotify(10);
        new Thread(() -> printABC.printLetter("A", 0), "A").start();
        new Thread(() -> printABC.printLetter("B", 1), "B").start();
        new Thread(() -> printABC.printLetter("C", 2), "C").start();
    }

    private void printLetter(String name, int targetState) {
        for (int i = 0; i < times; i++) {
            synchronized (LOCK) {
                while (state % 3 != targetState) {
                    try {
                        // 把当前线程等待
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                state++;
                System.out.print(name);
                // 唤醒其他线程
                LOCK.notifyAll();
            }
        }
    }
}
```







### 2. 两个线程交替打印 0~100 的奇偶数


两个线程:
1. `event` 线程: 打印偶数
2. `odd` 线程: 打印奇数



步骤:
1. 创建两个线程, 并运行
2. 创建公共锁 `LOCK`
3. 一个执行完 `wait`, 并唤醒 `notifyAll`

```
举例如下:

even线程 开始调用 ...
even线程判断合法, 执行输出
even线程 开始调用 ...
even线程判断不合法, 唤醒其他线程, 并停止调用

odd线程 开始调用 ...
odd线程判断合法, 执行输出
odd线程 开始调用 ...
odd线程判断不合法, 唤醒其他线程, 并停止调用
```

`Tips`: 先 `notifyAll`, 再 `wait`; 当被 `notify` 唤醒后, 会从原 `wait` 位置开始继续执行


```java

```

