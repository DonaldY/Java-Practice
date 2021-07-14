

`edit log` 场景：
```bash
# 执行一个命令
# 创建一个目录
hadoop fs -mkdir /usr/warehouse
```
1. 在内存里的文件目录树中加入对应的目录节点
2. 在磁盘里写入一条 `edits log`，记录本次元数据的修改


> `hdfs client` 创建目录的话， 会给 `hdfs NameNode` 发送一个 `RPC` 接口调用的请求， 调用 `mkdir()` 接口，在这接口里就会完成上述的两件事情。


组件如下：
1. `FSNamesystem`： `NameNode` 里元数据操作的核心入口，负责管理所有的元数据的操作，在里面可能会调用其他的组件完成相关的事情
2. `FSDirectory`： 专门负责管理内存中的文件目录树
3. `FSEditLog`： 专门写文件到 `edits log`


**写 `edit log` 采用双缓冲方式：**
1. 先将缓存写入 `currentBuffer`
2. `logSync` 会将数据刷入磁盘文件