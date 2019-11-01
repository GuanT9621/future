package io.net.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Set;

public class Client {

    private InetSocketAddress server;

    // 用于接收数据的缓冲区
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    // 用于发送数据的缓冲区
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    private static Selector selector;

    public Client(String host, int port) throws IOException {
        server = new InetSocketAddress(host, port);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(server);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> handle(selectionKey));
            selectionKeys.clear(); // 清除处理过的事件
        }
    }

    public void handle(SelectionKey key) {
        try {
            if (key.isConnectable()) {
                doConnect(key);
            } else if (key.isReadable()) {
                doRead(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doConnect(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
//      socketChannel.configureBlocking(false);
        if (socketChannel.isConnectionPending()) {
            socketChannel.finishConnect();
            System.out.println("连接成功！");
            // 启动线程监听客户端输入
            new Thread(() -> {
                while (true) {
                    try {
                        writeBuffer.clear();
                        Scanner scanner = new Scanner(System.in);
                        String sendText = scanner.nextLine();
                        writeBuffer.put(StandardCharsets.UTF_8.encode(sendText));
                        writeBuffer.flip();
                        socketChannel.write(writeBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        // 注册可读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void doRead(SelectionKey selectionKey) throws IOException {
        SocketChannel client = (SocketChannel) selectionKey.channel();
        readBuffer.clear();
        int count = client.read(readBuffer);
        if (count > 0) {
            String receiveText = new String(readBuffer.array(), 0, count);
            System.out.println("从服务端收到的消息 ：" + receiveText);
        }
    }

}
