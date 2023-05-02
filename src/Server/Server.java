package Server;

import Server.Events.EventListener;

import java.io.*;
import java.net.*;

public class Server {
    private int port;
    private ServerSocket serverSocket;
    private final EventListener listener;

    public Server(int port){
        this.listener = new EventListener();
        this.port = port;
        this.createServer();
        this.acceptConnections();
    }
    public void acceptConnections() {
        System.out.println("Server ready to accept new connections");
        while(true){
            try{
                Socket socket = this.serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress());
                String socketId = socket.getInetAddress().toString() + socket.getPort();
                this.listener.addConnection(socketId, socket);
                ClientHandler clientHandler = new ClientHandler(socket, socketId, this.listener);
                clientHandler.start();
            }catch (IOException e){
                System.out.println("Could not accept new connection");
            }
        }
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
