package Server.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class StringMessage extends Message {
    private final String message;
    private String createdAt;
    public StringMessage(String message){
        this.message = message;
        this.createdAt = this.getCurrentDate();
    }
    public String getMessage(){
        return this.message;
    }
    public void send(Socket socket) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(this.message+"\nEND_OF_MESSAGE");
        } catch (IOException e) {
            System.out.println("Failed to send message: " + e.getMessage());
        }
    }
    public String getCreatedAt(){
        return this.createdAt;
    }
}
