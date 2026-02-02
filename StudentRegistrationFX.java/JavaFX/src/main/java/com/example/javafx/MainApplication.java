// NUMBER 2 STUDENT REGISTRATION SYSTEM
// GROUP I - VICTORIA UNIVERSITY: OOP (1301 ST)
// OMONA EMMANUEL - VU-BSF-2503-1833-DAY
// VANESSAH NTABADDE - VU-BBC-2511-1563-DAY
// MPAKA JONATHAN - VU-BSF-2503-2210-DAY
// LWANYAGA IBRAHIM - VU-BCS-2503-1062-DAY
// ELVIS TREVOR AMUNYO - VU-BCS-2503-0114-DAY

package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 600);
        scene.getRoot().setStyle("-fx-background-color: #E5E5E5;");
        stage.setResizable(false);
        stage.setTitle("Student Registration Form");
        stage.setScene(scene);
        stage.show();
    }
}
