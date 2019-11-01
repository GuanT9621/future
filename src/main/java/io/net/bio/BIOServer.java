package io.net.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

    private ServerSocket serverSocket;

    public BIOServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void launch() throws IOException {
        while (true) {
            // 阻塞方法 获取新的连接
            Socket socket = serverSocket.accept();
            // 每一个新的连接都创建一个线程，负责读取数据
            new Thread(() -> {
                try {
                    int len;
                    byte[] data = new byte[1024];
                    InputStream inputStream = socket.getInputStream();
                    // 按字节流方式读取数据
                    while ((len = inputStream.read(data)) != -1) {
                        System.out.println(new String(data, 0, len));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) throws IOException {
        BIOServer bioServer = new BIOServer(3333);
        bioServer.launch();
    }
}
