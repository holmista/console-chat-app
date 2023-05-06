package Server;

import java.io.IOException;
import java.net.Socket;

public class CustomSocket extends Socket {
    private final Socket socket;
    public CustomSocket(Socket socket) {
        this.socket = socket;
    }
    public String getId(){
        return this.socket.getInetAddress().toString() + this.socket.getPort();
    }

    public Socket getSocket() {
        return socket;
    }
}
