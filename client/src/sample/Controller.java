
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private javafx.scene.control.Button Hide;
    @FXML
    private javafx.scene.control.Button Hide1;
    @FXML
    private javafx.scene.control.Label lblSystemMessage;
    @FXML private Canvas mainCanvas;
    @FXML public GraphicsContext gc;


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


    public void switchToScene2(ActionEvent event) throws IOException {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                        "\nUploaded:"
        );
        //Creates Hide button
        Hide.setText("Hide Text");
        Hide.setLayoutX(210);
        Hide.setLayoutY(350);
    }
    private void drawRec(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRoundRect(0, 0, 300, 100, 10, 10);
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
////    private void drawImg(GraphicsContext gc) {
////        Image image = new Image(getClass().getClassLoader().getResource("images/paper.png").toString());
////        gc.drawImage(image, 0, 0, 50, 50);
////    }
////
////    public void hideLable1(ActionEvent event){
////        /*
////         * Hides The Label and Hide button ones run
////         */
////
////        //hides Hide Button
////        Hide1.setVisible(false);
////        //Clears canvas drawing
////        gc.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
////    }

}