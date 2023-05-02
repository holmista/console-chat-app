package Server;

import Server.Events.BroadcastEvent;
import Server.Events.EventListener;
import Server.Events.RemoveConnectionEvent;
import Server.Message.Message;
import Server.Message.StringMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler extends Thread{
    private final Socket clientSocket;
    private final String clientSocketId;
    private final EventListener listener;
    public ClientHandler(Socket clientSocket, String clientSocketId, EventListener listener){
        this.clientSocket = clientSocket;
        this.clientSocketId = clientSocketId;
        this.listener = listener;
    }
    @Override
    public void run(){
        try{
            System.out.println("Client handler started");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            StringBuilder message = new StringBuilder();
            String line;
            while ((line = buffer.readLine()) != null) {
                if(line.equals("END_OF_MESSAGE")){
                    System.out.println("Finished reading input");
                    Message m = new StringMessage(message.toString());
                    BroadcastEvent event = new BroadcastEvent(m, this.clientSocketId, this.listener);
                    event.emit();
                    message.setLength(0);
                }else{
                    System.out.println("Received line: " + line);
                    message.append(line);
                }
            }
            System.out.println("client disconnected");
            System.out.println("client socket id: "+ this.clientSocketId);
            RemoveConnectionEvent event = new RemoveConnectionEvent(this.clientSocketId, this.listener);
            event.emit();
            this.clientSocket.close();
        }
        catch (IOException e){
            System.out.println("error while reading user input: " + e.getMessage());
        }
    }
}
