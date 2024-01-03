package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardformController {
    public AnchorPane pane;
    public Label lblTime;
    private Stage primaryStage;

    public void initialize(){
        calculateTime();
    }
    private void calculateTime() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> {
                    lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                }
        ), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void customerButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("View/Customerform.fxml"))));
        stage.setTitle("CUSTOMER FORM");
        Image image = new Image("Img/img1.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();

    }

    public void itemButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblTime.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("View/Itemform.fxml"))));
        stage.setTitle("ITEM FORM");
        Image image = new Image("Img/img1.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
}
