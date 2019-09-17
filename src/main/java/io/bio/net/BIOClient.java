package io.bio.net;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class BIOClient {
    public static void main(String[] args) {

        // 创建多个线程，模拟多个客户端连接服务端
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Socket socket = new Socket("127.0.0.1", 3333);
                    socket.getOutputStream().write((new Date() + ": hello world ").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
