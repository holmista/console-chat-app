package Server;

import Server.Events.EventListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptConnections implements Runnable {
    private final ServerSocket serverSocket;
    private final EventListener listener;
    public AcceptConnections(ServerSocket serverSocket, EventListener listener){
        this.serverSocket = serverSocket;
        this.listener = listener;
    }

    public void run(){
        while(true){
            try{
                Socket socket = this.serverSocket.accept();
                CustomSocket customSocket = new CustomSocket(socket);
                System.out.println("New client connected: " + socket.getInetAddress());
                this.listener.addConnection(customSocket);
                ClientHandler clientHandler = new ClientHandler(customSocket, listener);
                Thread clientHandlerThread = new Thread(clientHandler);
                clientHandlerThread.start();
            }catch (IOException e){
                System.out.println("Could not accept new connection: "+e.getMessage());
            }
        }
    }
}
