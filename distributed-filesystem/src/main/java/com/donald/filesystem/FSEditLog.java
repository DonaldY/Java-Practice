package com.donald.filesystem;

import java.util.LinkedList;
import java.util.List;

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
     * 记录edit log 日志
     *
     * @param content 日志
     */
    public void logEdit(String content) {
        synchronized (this) {
            // 获取全局唯一递增的 txid，代表了 edits log 的序号
            txidSeq ++;
            long txid = txidSeq;

            EditLog log = new EditLog(txid, content);

            // 将edit log 写入内存缓冲中先
            editLogBuffer.write(log);
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
        private List<EditLog> currentBuffer = new LinkedList<>();
        /**
         * 将数据同步到磁盘中的一块缓存
         */
        private List<EditLog> syncBuffer = new LinkedList<>();

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
            List<EditLog> tmp = currentBuffer;
            currentBuffer = syncBuffer;
            syncBuffer = tmp;
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
