package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SendMessage implements Runnable{
    private final Socket socket;
    private final ThreadSharedData sharedData;
    public SendMessage(Socket socket, ThreadSharedData sharedData){
        this.socket = socket;
        this.sharedData = sharedData;
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            if (!this.sharedData.getConnectionActive())
                break;
            System.out.println("Enter your message: ");
            String message = scanner.nextLine();
            try{
                PrintWriter writer = new PrintWriter(this.socket.getOutputStream(), true);
                writer.println(message+"\nEND_OF_MESSAGE");
            }catch (IOException e){
                System.out.println("could not send message: "+e.getMessage());
            }
        }
    }
}
