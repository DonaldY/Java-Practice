package com.donald.filesystem;

import java.util.LinkedList;

/**
 * 负责管理 edits log 的日志的核心组件
 *
 * @author donald
 * @date 2021/07/13
 */
public class FSEditLog {

    /**
     * 当前递增的 txid 的序号
     */
    private long txidSeq = 0L;
    /**
     * 内存双缓冲区
     */
    private DoubleBuffer editLogBuffer = new DoubleBuffer();
    /**
     * 是否将内存缓冲刷入磁盘中
     */
    private volatile Boolean isSyncRunning = false;
    /**
     * 是否有线程在等待刷新下一批 edit log到磁盘中
     */
    private volatile Boolean isWaitSync = false;
    /**
     * 在同步到磁盘中最大的 txid
     */
    private volatile Long syncMaxTxId = 0L;
    /**
     * 每个线程自己本地的 txid 副本
     */
    private ThreadLocal<Long> localTxId = new ThreadLocal<>();

    /**
     * 记录edit log 日志
     *
     * @param content 日志
     */
    public void logEdit(String content) {
        synchronized (this) {
            // 获取全局唯一递增的 txid，代表了 edits log 的序号
            txidSeq ++;
            long txid = txidSeq;
            localTxId.set(txid);

            EditLog log = new EditLog(txid, content);

            // 将edit log 写入内存缓冲中先
            editLogBuffer.write(log);
        }

        logSync();
    }

    /**
     * 将内存缓冲中的数据刷入磁盘文件中
     *
     * 允许某一个线程一次性将内存缓冲中的数据输入磁盘文件中
     *
     * 相当与实现一个批量将内存数据刷磁盘的过程
     */
    private void logSync() {
        // 再次尝试加锁
        synchronized (this) {
            // 如果有人正在刷内存缓冲到磁盘中去
            if (isSyncRunning) {
                /**
                 * 假如说某个线程已经把 txid = 1,2,3,4,5 的edits log都从syncBuffer 刷入磁盘了
                 * 或者说此时正在刷人磁盘中
                 * 此时 syncMaxTxid = 5, 代表的是正在输入磁盘的最大 txid
                 * 那么这个时候来一个线程，他对应的 txid = 3, 此时他是可以直接返回了
                 * 就代表说肯定是他对应的 edits log 已经被别的线程刷入磁盘了
                 * 这时候就不需要等待了
                 */
                long txid = localTxId.get();
                if (txid <= syncMaxTxId) {
                    return;
                }
                // 假如这时候来了 txid = 6
                // 那么这时候，他就需要等待，同时要释放掉锁
                if (isWaitSync) {
                    return;
                }

                isWaitSync = true;
                while (isSyncRunning) {
                    try {
                        wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            // 交换两块缓冲区
            editLogBuffer.setReadyToSync();
            // 保存一下当前要同步到磁盘中去的最大的 txid
            // 此时 editLogBuffer 中的syncBuffer这块区域，交换完以后这里可能有多条数据
            // 而且他里面的 edits log 的txid一定是从小到大的
            syncMaxTxId = editLogBuffer.getSyncMaxTxId();
            // 设置当前正在同步到磁盘的标志位
            isSyncRunning = true;
        }

        // 开始同步内存缓冲的数据到磁盘文件里去
        // 这个过程比较慢，基本上毫秒级，弄不好 几十毫秒
        editLogBuffer.flush();

        synchronized (this) {
            // 同步完了磁盘之后，就会将标志位复位，再释放锁
            isSyncRunning = false;
            // 唤醒可能等待的锁
            notifyAll();
        }
    }

    /**
     * 代表了一条 edits log
     */
    class EditLog {

        long txid;
        String content;

        public EditLog(long txid, String content) {
            this.txid = txid;
            this.content = content;
        }
    }

    /**
     * 内存双缓冲
     *
     */
    class DoubleBuffer {
        /**
         * 承载线程写入 edits log 的缓存
         */
        private LinkedList<EditLog> currentBuffer = new LinkedList<>();
        /**
         * 将数据同步到磁盘中的一块缓存
         */
        private LinkedList<EditLog> syncBuffer = new LinkedList<>();

        /**
         * 将 edits log写到内存缓冲里去
         * @param log 日志
         */
        public void write(EditLog log) {
            currentBuffer.add(log);
        }

        /**
         * 交换两块缓存区，为了同步内存数据到磁盘做准备
         */
        public void setReadyToSync() {
            LinkedList<EditLog> tmp = currentBuffer;
            currentBuffer = syncBuffer;
            syncBuffer = tmp;
        }

        /**
         * 获取 sync buffer 缓冲区里的最大一个 txid
         * @return
         */
        public Long getSyncMaxTxId() {
            return syncBuffer.getLast().txid;
        }

        /**
         * 将 syncBuffer 缓冲区的数据刷入磁盘中
         */
        public void flush() {
            for (EditLog log : syncBuffer) {
                System.out.println("将edit log 写入磁盘文件中：" + log);
                // 正常情况，用文件输出流将数据写入磁盘文件中
            }
            syncBuffer.clear();
        }
    }
}
