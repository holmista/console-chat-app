package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Map;

public class Client {
    private final String host;
    private final int port;
    private Socket socket;
    private ThreadSharedData sharedData = new ThreadSharedData();

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void launch(){
        sharedData.setConnectionActive(true);
        this.createConnection();
        SendMessage sendMessage = new SendMessage(this.socket, this.sharedData);
        Thread sendMessageThread = new Thread(sendMessage);
        sendMessageThread.start();
        AcceptMessage acceptMessage = new AcceptMessage(this.socket, this.sharedData);
        Thread acceptMessageThread = new Thread(acceptMessage);
        acceptMessageThread.start();
    }
    private void createConnection() {
        try{
            this.socket = new Socket(this.host, this.port);
            System.out.println("successfully connected to server");
        } catch (IOException e){
            System.out.println("could not connect to server: "+e.getMessage());
            System.exit(1);
        }
    }
}
