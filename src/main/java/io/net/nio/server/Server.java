package io.net.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 1.SelectionKey.OP_CONNECT：连接事件
 * 2.SelectionKey.OP_ACCEPT：接收事件
 * 3.SelectionKey.OP_READ：读事件
 * 4.SelectionKey.OP_WRITE：写事件
 *
 * FileChannel：作用于IO文件流
 * DatagramChannel：作用于UDP协议
 * SocketChannel：作用于TCP协议
 * ServerSocketChannel：作用于TCP协议
 *
 * 关于 socket
 * 服务端                         客户端
 * 1 创建socket               1 创建socket
 * 2 绑定                     2 连接
 * 3 监听
 * 4 接受
 *
 * clear  把limit设为capacity，把position设为0，一般在把数据写入Buffer前调用
 * flip   把limit设为当前position，把position设为0，一般在从Buffer读出数据前调用
 * rewind 让limit不变，把position设为0，一般在把数据重写入Buffer前调用
 *
 * 注册 channel 到 selector
 * int OP_READ      = 1 << 0;
 * int OP_WRITE     = 1 << 2;
 * int OP_CONNECT   = 1 << 3;
 * int OP_ACCEPT    = 1 << 4;
 *
 * 注册操作,每个chanel只能注册一个操作，最后注册的一个生效
 * 如果你对不止一种事件感兴趣，那么可以用“位或”操作符将常量连接起来
 * int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
 */
public class Server {
    private int port;

    // 用于接收数据的缓冲区
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    // 用于发送数据的缓冲区
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    private Selector selector;

    public Server(int port) throws IOException {
        this.port = port;
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(port));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动，端口为：" + port);
    }

    public void listen() {
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> handler(selectionKey));
                selectionKeys.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handler(SelectionKey key) {
        try {
            // 判断当前key所代表的channel是否在Acceptable状态，如果是就进行接收
            if (key.isAcceptable()) {
                System.out.println("do accept ! ");
                doAccept(key);
            } else if (key.isValid() && key.isReadable()) {
                System.out.println("do read ! ");
                doRead(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void doRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int bytesRead;
        while ((bytesRead = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip();
            String receiveText = new String(byteBuffer.array()).trim();
            System.out.println("从客户端发送过来的消息是：" + receiveText);
            dispatch(socketChannel, receiveText);
        }
        if (bytesRead == -1) {
            socketChannel.close();
        }
    }

    /**
     * 转发消息给各个客户端
     */
    private void dispatch(SocketChannel client, String info) throws IOException {
        writeBuffer.clear();
        writeBuffer.put(StandardCharsets.UTF_8.encode(info));
        writeBuffer.flip();
        client.write(writeBuffer);
    }

}
