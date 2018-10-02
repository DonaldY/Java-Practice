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
