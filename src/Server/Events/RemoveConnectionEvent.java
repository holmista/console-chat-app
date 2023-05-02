package Server.Events;

public class RemoveConnectionEvent {
    private final EventListener connectionListener;
    private String connectionId;
    public RemoveConnectionEvent(String connectionId, EventListener connectionListener){
        this.connectionId = connectionId;
        this.connectionListener = connectionListener;
    }
    public void emit(){
        this.connectionListener.removeConnection(this.connectionId);
    }
}
