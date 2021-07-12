package com.donald.filesystem;

/**
 * NameNode 核心启动类
 * @author donald
 * @date 2021/07/13
 */
public class NameNode {

    /**
     * NameNode 是否在运行
     */
    private volatile Boolean shouldRun;
    /**
     * 负责管理元数据的核心组件
     */
    private FSNamesystem namesystem;
    /**
     * NameNode 对外提供 RPC 接口的 server，可以响应请求
     */
    private NameNodeRpcServer rpcServer;

    public NameNode() {
        this.shouldRun = true;
    }

    /**
     * 初始化 NameNode
     */
    private void initialize() {
        this.namesystem = new FSNamesystem();
        this.rpcServer = new NameNodeRpcServer(namesystem);
        this.rpcServer.start();
    }

    /**
     * 运行 NameNode
     */
    private void run() {
        while (shouldRun) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        NameNode nameNode = new NameNode();
        nameNode.initialize();
        nameNode.run();
    }
}
