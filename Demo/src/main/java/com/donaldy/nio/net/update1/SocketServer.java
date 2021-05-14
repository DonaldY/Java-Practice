package com.donaldy.nio.net.update1;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * @author donald
 * @date 2021/05/14
 */
public class SocketServer {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);

        while(true) {
            // 在这里会阻塞住，一直等待别人跟建立连接
            Socket socket = serverSocket.accept();

            // 创建一个线程去处理这个 socket
            new Worker(socket).start();
        }
    }

    static class Worker extends Thread {

        Socket socket;

        public Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStreamReader in = new InputStreamReader(socket.getInputStream());
                OutputStream out = socket.getOutputStream();

                char[] buf = new char[1024 * 1024];
                int len = in.read(buf);

                if(len != -1) {
                    String request = new String(buf, 0, len);
                    System.out.println("[" + Thread.currentThread().getName() + "]服务端接收到了请求：" + request);
                    out.write("收到，收到".getBytes());
                }

                out.close();
                in.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
