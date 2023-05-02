package Server.Events;

import Server.Message.Message;
import Server.Message.StringMessage;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class EventListener {
    private final Map<String, Socket> connections = new HashMap<>();
    public void addConnection(String key, Socket val){
        this.connections.put(key, val);
    }
    public void removeConnection(String key){
        this.connections.remove(key);
    }
    public void broadcast(Message message, String excludeConnectionId){
        for (Socket socket:this.connections.values()) {
            if((socket.getInetAddress().toString() + socket.getPort()).equals(excludeConnectionId))
                continue;
            message.send(socket);
        }
    }
}
