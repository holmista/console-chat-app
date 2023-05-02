package Server.Events;

import Server.Message.Message;
import Server.Message.StringMessage;

public class BroadcastEvent {
    public Message message;
    public String excludeConnectionId;
    private final EventListener listener;
    public BroadcastEvent(Message message, String excludeConnectionId, EventListener listener){
        this.message = message;
        this.excludeConnectionId = excludeConnectionId;
        this.listener = listener;
    }
    public void emit(){
        listener.broadcast(this.message, this.excludeConnectionId);
    }
}
