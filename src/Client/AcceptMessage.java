package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class AcceptMessage implements Runnable {
    private final Socket socket;
    private final ThreadSharedData sharedData;
    public AcceptMessage(Socket socket, ThreadSharedData sharedData){
        this.socket = socket;
        this.sharedData = sharedData;
    }
    public void run(){
        try{
            System.out.println("client is ready to accept messages");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            StringBuilder message = new StringBuilder();
            String line;
            while((line = buffer.readLine()) != null){
                System.out.println("line in client: "+line);
                if (line.equals("END_OF_MESSAGE")) {
                    System.out.println(message.toString());
                    message.setLength(0);
                } else {
                    message.append(line);
                }
            }
            System.out.println("Server closed connection");
            this.sharedData.setConnectionActive(false);
            socket.close();
        }catch (IOException e){
            System.out.println("could not accept message from server: "+e.getMessage());
        }
    }
}
