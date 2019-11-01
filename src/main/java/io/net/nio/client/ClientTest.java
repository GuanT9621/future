package io.net.nio.client;

import java.io.IOException;

/**
 * 模拟聊天室 client 向 server 每两秒发送一个数字， server 向 client 回复一个数字。
 */
public class ClientTest {

    public static void main(String[] args) throws IOException {
        new Client("127.0.0.1", 3333);
    }

}
