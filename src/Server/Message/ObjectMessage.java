package Server.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectMessage extends Message{
    public final String message;

    public ObjectMessage(String message){
        this.message = message;
    }
    @Override
    public void send(Socket socket) {
        try{
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(this.message);
            out.flush();
        }
        catch (IOException e){
            System.out.println("could not send message: "+e.getMessage());
        }
    }
}
