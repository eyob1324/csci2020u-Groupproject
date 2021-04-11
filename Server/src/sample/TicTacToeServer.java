package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class TicTacToeServer {
    protected Socket clientSocket           = null;
    protected ServerSocket serverSocket     = null;
    protected ServerThread[] threads    = null;
    protected int numClients                = 0;
    protected Vector messages               = new Vector();

    public static int SERVER_PORT = 8080;
    public static int MAX_CLIENTS = 100;

    public TicTacToeServer() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("---------------------------");
            System.out.println("Server Application is running");
            System.out.println("---------------------------");
            System.out.println("Listening to port: "+SERVER_PORT);
            threads = new ServerThread[MAX_CLIENTS];
            while(true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client #"+(numClients+1)+" connected.");
                threads[numClients] = new ServerThread(clientSocket, messages);
                threads[numClients].start();
                numClients++;
            }
        } catch (IOException e) {
            System.err.println("IOException while creating server connection");
        }
    }

    public static void main(String[] args) {
        TicTacToeServer app = new TicTacToeServer();
    }
}

