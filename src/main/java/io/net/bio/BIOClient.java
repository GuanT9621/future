package io.net.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class BIOClient {

    private Socket socket;

    public BIOClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }

    public void send(byte[] bytes) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(bytes);
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            BIOClient bioClient = new BIOClient("127.0.0.1", 3333);
            byte[] words = (" hello world " + i).getBytes();
            bioClient.send(words);
        }
    }
}
