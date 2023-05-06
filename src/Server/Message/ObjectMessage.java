package Server.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectMessage extends Message{
    public final String message;
    private static ObjectOutputStream out = null;

    public ObjectMessage(String message){
        this.message = message;
    }
    @Override
    public void send(Socket socket) {
        try{
            if (out == null) {
                out = new ObjectOutputStream(socket.getOutputStream());
            }
            out.writeObject(this.message+"\nEND_OF_MESSAGE");
            out.flush();
        }
        catch (IOException e){
            System.out.println("could not send message: "+e.getMessage());
        }
    }
}
