package com.donaldy.nio.net.update2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author donald
 * @date 2021/05/14
 */
public class NIOServer {

    private static Selector selector;
    private static LinkedBlockingQueue<SelectionKey> requestQueue;
    private static ExecutorService threadPool;

    public static void main(String[] args) {
        init();
        listen();
    }

    private static void init(){
        ServerSocketChannel serverSocketChannel = null;

        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);  // NIO就是支持非阻塞的，项目实战的时候给大家来讲
            serverSocketChannel.socket().bind(new InetSocketAddress(9000), 100);
            // ServerSocket，就是负责去跟各个客户端连接连接请求的
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 就是仅仅关注这个ServerSocketChannel接收到的TCP连接的请求
        } catch (IOException e) {
            e.printStackTrace();
        }

        requestQueue = new LinkedBlockingQueue<SelectionKey>(500);

        threadPool = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10; i++) {
            threadPool.submit(new Worker());
        }
    }

    private static void listen() {
        while(true){
            try{
                selector.select();
                Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();

                while(keysIterator.hasNext()){
                    SelectionKey key = (SelectionKey) keysIterator.next();
                    // 可以认为一个SelectionKey是代表了一个请求
                    keysIterator.remove();
//                    requestQueue.offer(key);
                    handleRequest(key);
                }
            }
            catch(Throwable t){
                t.printStackTrace();
            }
        }
    }

    private static void handleRequest(SelectionKey key)
            throws IOException, ClosedChannelException {
        // 假设想象一下，后台有个线程池获取到了请求
        // 下面的代码，都是在后台线程池的工作线程里在处理和执行
        SocketChannel channel = null;

        try{
            if(key.isAcceptable()){  // 如果说这个key是个acceptable，是个连接请求的话
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                channel = serverSocketChannel.accept(); // 调用这个accept方法，就可以跟客户端进行TCP三次握手
                // 如果三次握手成功了之后，就可以获取到一个建立好TCP连接的SocketChannel
                // 这个SocketChannel大概可以理解为，底层有一个Socket，是跟客户端进行连接的
                // 你的SocketChannel就是联通到那个Socket上去，负责进行网络数据的读写的

                if (!Objects.isNull(channel)) {

                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);  // 仅仅关注这个READ请求，就是人家发送数据过来的请求
                }
            }  // 如果说这个key是readable，是个发送了数据过来的话，此时需要读取客户端发送过来的数据
            else if(key.isReadable()){
                channel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int count = channel.read(buffer);  // 通过底层的socket读取数据，写入buffer中，position可能就会变成21之类的
                // 你读取到了多少个字节，此时buffer的position就会变成多少

                if(count > 0){
                    buffer.flip();   // position = 0，limit = 21，仅仅读取buffer中，0~21这段刚刚写入进去的数据
                    System.out.println("服务端接收请求：" + new String(buffer.array(), 0, count));

                    /**
                     * 你接收到别人发送过来的数据之后，或者是请求之后，应该如何处理？
                     *
                     * 协议： TCP 是传输层的协议，IP是网络层的协议，以太网是链路层的协议。
                     * 实际上应该有一个 HTTP 协议
                     *
                     *
                     * 别人在这里发送给你的数据看起来，像下面这样：
                     * GET HTTP/1.1 /home/do.jsp
                     * Accept: xxx
                     * Last-Modified: xxx
                     * {id:1}
                     *
                     *
                     * 此时就需要按照应用层的协议，无论是 HTTP 协议，还是自定义的协议
                     * 可以按照固定的格式来解析消息，进行处理
                     *
                     * 处理完了请求之后，需要发送响应回去，此时可以重新注册一下 channel
                     * 把你对这个 channel 感兴趣的操作变成： OP_WRITE
                     */
                    channel.register(selector, SelectionKey.OP_WRITE);
                }
            } else if(key.isWritable()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put("收到".getBytes());
                buffer.flip();

                channel = (SocketChannel) key.channel();
                channel.write(buffer);

                /**
                 * 此时按照设计好的应用层的协议，比如 HTTP 协议。
                 *
                 * 现在已经发送响应回去给客户端了，接下来再次重新注册这个 channel
                 * 即，我改变注意了，现在又要对客户端的 `READ` 事件比较感兴趣。
                 *
                 * 如果说那个 channel 接收到客户端的数据，可以读数据了，就通知我一下。
                 */
                channel.register(selector, SelectionKey.OP_READ);
            }
        }
        catch(Throwable t){
            t.printStackTrace();
            if(channel != null){
                channel.close();
            }
        }
    }

    static class Worker implements Runnable {

        @Override
        public void run() {
            while(true) {
                try {
                    SelectionKey key = requestQueue.take();
                    handleRequest(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleRequest(SelectionKey key) throws IOException {
            // 假设想象一下，后台有个线程池获取到了请求
            // 下面的代码，都是在后台线程池的工作线程里在处理和执行
            SocketChannel channel = null;

            try{
                if(key.isAcceptable()){  // 如果说这个key是个acceptable，是个连接请求的话
                    System.out.println("[" + Thread.currentThread().getName() + "]接收到连接请求");
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    channel = serverSocketChannel.accept(); // 调用这个accept方法，就可以跟客户端进行TCP三次握手
                    System.out.println("[" + Thread.currentThread().getName() + "]建立连接时获取到的channel=" + channel);
                    // 如果三次握手成功了之后，就可以获取到一个建立好TCP连接的SocketChannel
                    // 这个SocketChannel大概可以理解为，底层有一个Socket，是跟客户端进行连接的
                    // 你的SocketChannel就是联通到那个Socket上去，负责进行网络数据的读写的
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);  // 仅仅关注这个READ请求，就是人家发送数据过来的请求
                }  // 如果说这个key是readable，是个发送了数据过来的话，此时需要读取客户端发送过来的数据
                else if(key.isReadable()){
                    channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = channel.read(buffer);  // 通过底层的socket读取数据，写入buffer中，position可能就会变成21之类的
                    // 你读取到了多少个字节，此时buffer的position就会变成多少

                    System.out.println("[" + Thread.currentThread().getName() + "]接收到请求");

                    if(count > 0){
                        buffer.flip();   // position = 0，limit = 21，仅仅读取buffer中，0~21这段刚刚写入进去的数据
                        System.out.println("服务端接收请求：" + new String(buffer.array(), 0, count));
                        channel.register(selector, SelectionKey.OP_WRITE);
                    }
                } else if(key.isWritable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    buffer.put("收到".getBytes());
                    buffer.flip();

                    channel = (SocketChannel) key.channel();
                    channel.write(buffer);
                    channel.register(selector, SelectionKey.OP_READ);
                }
            }
            catch(Throwable t){
                t.printStackTrace();
                if(channel != null){
                    channel.close();
                }
            }
        }
    }
}
