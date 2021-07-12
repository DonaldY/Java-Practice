package com.donald.filesystem;

/**
 * NameNode 的 RPC 服务的接口
 * @author donald
 * @date 2021/07/13
 */
public class NameNodeRpcServer {

    /**
     * 负责管理元数据的核心组件
     */
    private FSNamesystem namesystem;

    public NameNodeRpcServer(FSNamesystem namesystem) {
        this.namesystem = namesystem;
    }

    /**
     * 启动 rpc server
     */
    public void start() {
        System.out.println("开始监听指定的 rpc server 的端口号，来接收这个请求");
    }

    /**
     * 创建目录
     * @param path 目录路径
     * @return 是否创建成功
     * @throws Exception 异常
     */
    public Boolean mkdir(String path) throws Exception {

        return this.namesystem.mkdir(path);
    }
}
