package Server.Events;

import Server.CustomSocket;
import Server.Message.Message;
import Server.Message.StringMessage;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class EventListener {
    private final Map<String, Socket> connections = new HashMap<>();
    public void addConnection(CustomSocket val){
        this.connections.put(val.getId(), val.getSocket());
    }
    public void removeConnection(String key){
        this.connections.remove(key);
    }
    public void broadcast(Message message, String excludeConnectionId){
        for (String socketId:this.connections.keySet()) {
            if(socketId.equals(excludeConnectionId))
                continue;
            message.send(this.connections.get(socketId));
        }
    }
}
