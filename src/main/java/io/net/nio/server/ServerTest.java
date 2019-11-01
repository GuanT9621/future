package io.net.nio.server;

import java.io.IOException;

public class ServerTest {

    public static void main(String[] args) throws IOException {
        Server server = new Server(3333);
        server.listen();
    }

}
