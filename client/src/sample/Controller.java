
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button Hide;
    @FXML
    private Button Hide1;
    @FXML
    private Label lblSystemMessage;
    @FXML
    private Label outcome;
    @FXML
    private Canvas mainCanvas;
    @FXML
    private Canvas Canvas2;
    @FXML
    public GraphicsContext gc;

    @FXML
    private Button Start;




    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter networkOut = null;
    private BufferedReader networkIn = null;

    public  static String SERVER_ADDRESS = "localhost";
    public  static int    SERVER_PORT = 8080;

    public void initialize() {

    }

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(Controller.class.getResource("mystyle.css").toExternalForm());
        stage.show();
    }


    public void playgame(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(Controller.class.getResource("mystyle.css").toExternalForm());
        stage.show();
    }
    public void helpLable(ActionEvent event){
        gc = mainCanvas.getGraphicsContext2D();
        drawRec(gc);
        /*
         * Makes Hide button visible
         * And outputs help instruction
         */
        //Makes Hide button visible
        Hide.setVisible(true);
        lblSystemMessage.setText(
                "Help Menu:" +
                        "\n Simple Rock paper Scissor game"+
                        "\n you pick either rock,paper,or scissor"+
                        "\n and the computer will randomly pick one"+
                        "\n to play press start game and then start button"
        );
        //Creates Hide button
        Hide.setText("Hide Text");
        Hide.setLayoutX(210);
        Hide.setLayoutY(350);
    }
    private void drawRec(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRoundRect(0, 0, 650, 300, 20, 20);
    }

    public void hideLable(ActionEvent event){
        /*
         * Hides The Label and Hide button ones run
         */
        lblSystemMessage.setText("");
        //hides Hide Button
        Hide.setVisible(false);
        //Clears canvas drawing
        gc.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
    }
//
    public void helpLable1(ActionEvent event){
       gc = mainCanvas.getGraphicsContext2D();
       Image image = new Image(getClass().getClassLoader().getResource("ducks.png").toString());
        gc.drawImage(image, 0, 0, 50, 50);
   }





    public void rock(ActionEvent actionEvent) {


        try {
            networkOut = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
            networkOut.println("move" + " " + "1");
            networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = networkIn.readLine();
            String message2 = networkIn.readLine();
            networkOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try{
            String message3 = networkIn.readLine();
            System.out.println(message3);
            showOutcome(message3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void paper(ActionEvent actionEvent) {

            try {
                networkOut = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                networkOut.println("move" + " " + "2");
                networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = networkIn.readLine();
                String message2 = networkIn.readLine();
                networkOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
            String message3 = networkIn.readLine();
            System.out.println(message3);
            showOutcome(message3);
            } catch (Exception e) {
            e.printStackTrace();
            }

    }

    public void scissors(ActionEvent actionEvent) {

            try {
                networkOut = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                networkOut.println("move" + " " + "3");
                networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = networkIn.readLine();
                String message2 = networkIn.readLine();
                networkOut.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                String message3 = networkIn.readLine();
                System.out.println(message3);
                showOutcome(message3);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void Start(ActionEvent actionEvent) {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

        } catch (Exception e) {
            e.printStackTrace();
        }
            Start.setVisible(false);
    }


    public void showOutcome(String message){
        gc = Canvas2.getGraphicsContext2D();
        drawRec(gc);
        outcome.setText(message);
    }

}