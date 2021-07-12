package com.donald.filesystem;

/**
 * 负责管理元数据的核心组件
 *
 * @author donald
 * @date 2021/07/13
 */
public class FSNamesystem {

    /**
     * 负责管理内存文件目录树的组件
     */
    private FSDirectory directory;
    /**
     * 负责管理 edits log 写入磁盘的组件
     */
    private FSEditLog editLog;

    public FSNamesystem() {
        this.directory = new FSDirectory();
        this.editLog = new FSEditLog();
    }

    /**
     * 创建目录
     *
     * 1. 创建目录
     * 2. 写入 edits log 日志
     * @param path 目录路径
     * @return 是否成功
     */
    public Boolean mkdir(String path) {
        this.directory.mkdir(path);
        this.editLog.logEdit("创建了一个目录：" + path);
        return true;
    }
}
