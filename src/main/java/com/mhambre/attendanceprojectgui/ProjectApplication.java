package com.mhambre.attendanceprojectgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 425, 463);
        stage.getIcons().add(new Image(ProjectApplication.class.getResourceAsStream("icon.png")));
        stage.setTitle("Attendance Handler");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}