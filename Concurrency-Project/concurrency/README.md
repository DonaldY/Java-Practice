# Java 并发练习


## 安全共享对象策略
1. 线程限制
一个被线程限制的对象，由线程独占，并且只能被占有它的线程修改

2. 共享只读：
一个共享只读的对象，在没有额外同步的情况下，可以被多个线程并发访问，但是任何线程都不能修改它。

3. 线程安全对象：
一个线程安全的对象或者容器，在内部通过同步机制来保证线程安全，所以其他线程无需额外的同步就可以通过公共接口随意访问它

4. 被守护对象：
被守护对象只能通过获取特定的锁来访问


## `AbstractQueuedSynchronized` - AQS
1. 使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架
2. 利用了一个int类型表示状态
3. 使用方法是继承
4. 子类通过继承并通过实现它的方法管理其状态 {acquire 和 release} 的方法操纵状态
5. 可以同时实现排它锁和共享锁（独占、共享）

### AQS 同步组件
1. `CountDownLatch`
2. `Semaphore`
3. `CyclicBarrier`
4. `ReentrantLock`
5. `Condition`
6. `FutureTask`


## FutureTask
1. Callable 与 Runnable 接口对接
2. Future接口
3. FutureTask类


## BlockingQueue
|  | Throws Exception | Special Value | Blocks | Times Out |
|--|--|--|--|--|
| Insert | add(o) | offer(0) | put(o) | offer(o, timeout, timeunit) |
| Remove | remove(o) | poll() | take() | poll(timeout, timeunit) |
| Examine | element() | peek() |  |  |

1. ArrayBlockingQueue
2. DelayQueue
3. LinkedBlockingQueue
4. PriorityBlockingQueue
5. SynchronousQueue


## 线程池

### `new Thread` 弊端
1. 每次`new Thread` 新建对象，性能差
2. 线程缺乏统一管理，可能无限制的新建线程，相互竞争，有可能占用过多系统资源导致死机或OOM
3. 缺少更多功能，如更多执行、定期执行、线程中断

### 线程池的好处
1. 重用存在的线程，减少对象创建、消亡的开销，性能差
2. 可有效控制最大并发线程数，提高系统资源利用率，同时可以避免过多资源竞争，避免阻塞
3. 提供定时执行，定期执行，单线程，并发数控制等功能

### 线程池

#### `ThreadPoolExecutor`
1. `corePoolSize` : 核心线程数量
2. `maximumPoolSize` : 线程最大线程数
3. `workQueue` : 阻塞队列，存储等待执行的任务，很重要，会对线程池运行过程产生重大影响
4. `keepAliveTime` : 线程没有任务执行时最多保持多久时间终止
5. `unit`: keepAliveTime的时间单位
6. `threadFactory` : 线程工厂，用来创建线程
7. `rejectHandler` : 当拒绝处理任务时的策略

#### `ThreadPoolExecutor` 方法
1. `execute()` : 提交任务，交给线程池执行
2. `submit()` : 提交任务，能够返回执行结果， execute + Future
3. `shutdown()` : 关闭线程池，等待任务都执行完
4. `shutdownNow()` : 关闭线程池，不等待任务执行完
5. `getTaskCount()` : 线程池已执行和未执行的任务总数
6. `getCompletedTaskCount()` : 已完成的任务数量
7. `getPoolSize()` : 线程池当前的线程数量
8. `getActiveCount()` : 当前线程池中正在执行任务的线程数量 

#### `Executor` 框架接口
1. Executors.newCachedThreadPool
2. Executors.newFixedThreadPool
3. Executors.newScheduledThreadPool
4. Executors.newThreadExecutor

### 线程池 - 合理配置
1. CPU密集型任务：需要尽量压榨CPU，参考值可以设为 NCPU + 1
2. IO密集型任务，参考值可以设置为 2 * NCPU