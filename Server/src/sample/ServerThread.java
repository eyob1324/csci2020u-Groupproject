package sample;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread{
    protected Socket socket       = null;
    protected PrintWriter out     = null;
    protected BufferedReader in   = null;


    protected Vector messages     = null;

    public ServerThread(Socket socket, Vector messages) {
        super();
        this.socket = socket;
        this.messages = messages;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOEXception while opening a read/write connection");
        }
    }

    public void run() {
        // initialize interaction
        out.println("Connected to Chat Server");
        out.println("200 Ready For Chat");

        boolean endOfSession = false;
        while(!endOfSession) {
            endOfSession = processCommand();
        }
        try {
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean processCommand() {
        String message = null;
        try {
            message = in.readLine();
        } catch (IOException e) {
            System.err.println("Error reading command from socket.");
            return true;
        }
        if (message == null) {
            return true;
        }
        StringTokenizer st = new StringTokenizer(message);
        String command = st.nextToken();
        String args = null;
        if (st.hasMoreTokens()) {
            args = message.substring(command.length()+1, message.length());
        }
        return processCommand(command, args);
    }
    /*
    When the client sends a command it comes in the format "move" + " "+ "1 <= number <=3" the number tells it
    what move to use 1 being rock , 2 being paper , 3 being scissors.
    Once the function has the players move it will randomly generate a number between 1 and 3 for its move
    it then compares its number using the rock paper scissors rules to see if the client won,lost,or Drawed
    rules
    paper beats rock
    rock beats scissors
    scissors beat paper
     */
    protected boolean processCommand(String command, String arguments) {
        if(command.equalsIgnoreCase("move")) {

            System.out.println(arguments);
            int random_int = (int)Math.floor(Math.random()*(3-1+1)+1);
            System.out.println(random_int);
            if (random_int == 1){
                System.out.println("computer move Rock");
                if(Integer.parseInt(arguments) == 1){
                    System.out.println("Draw");
                    out.println("Draw You picked Rock Computer Picked Rock");
                }else if(Integer.parseInt(arguments) == 2){
                    System.out.println("Win");
                    out.println("Win You picked Paper Computer picked Rock");
                }else if(Integer.parseInt(arguments) == 3){
                    System.out.println("loss");
                    out.println("loss you picked scissors Computer picked Rock");
                }
            }
            else if(random_int == 2){
                System.out.println("computer move paper");
                if(Integer.parseInt(arguments) == 1){
                    System.out.println("loss");
                    out.println("loss you picked Rock computer picked paper");
                }else if(Integer.parseInt(arguments) == 2){
                    System.out.println("Draw");
                    out.print("Draw you picked paper computer picked paper");
                }else if(Integer.parseInt(arguments) == 3){
                    System.out.println("Win");
                    out.println("Win you picked scissors computer picked paper");
                }
            }else if(random_int == 3){
                System.out.println("computer move scissors");
                if(Integer.parseInt(arguments) == 1){
                    System.out.println("win");
                    out.println("Win you picked rock computer picked scissors");
                }else if(Integer.parseInt(arguments) == 2){
                    System.out.println("loss");
                    out.println("loss you picked paper computer picked scissors");
                }else if(Integer.parseInt(arguments) == 3){
                    System.out.println("Draw");
                    out.print("Draw you picked scissors computer picked scissors");

                }
            }
        }

        return false;
    }








}
