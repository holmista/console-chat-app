package Server;
import Server.Events.EventListener;
import java.io.*;
import java.net.*;

public class Server {
    private final int port;
    private ServerSocket serverSocket;
    private final EventListener listener;
    public Server(int port){
        this.listener = new EventListener();
        this.port = port;
    }
    public void launch(){
        this.createServer();
        AcceptConnections acceptConnections = new AcceptConnections(this.serverSocket, this.listener);
        Thread acceptConnectionsThread = new Thread(acceptConnections);
        acceptConnectionsThread.start();
    }
    public void createServer() {
        try{
            this.serverSocket = new ServerSocket(this.port);
            System.out.println("Server listening on port " + this.port);
        }catch (IOException e){
            System.out.println("Could not start listening on port " + this.port);
        }
    }
}
