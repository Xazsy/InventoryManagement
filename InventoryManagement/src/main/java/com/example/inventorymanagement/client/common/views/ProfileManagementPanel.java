package com.example.inventorymanagement.client.common.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class ProfileManagementPanel extends Application {
    public void start(Stage stage) throws IOException {

        Font.loadFont(getClass().getResourceAsStream("/fonts/ShareTechMono-Regular.ttf"),20);

        FXMLLoader fxmlLoader = new FXMLLoader(LoginPanel.class.getResource("/com/example/inventorymanagement/client/view/profileManagement/profileManagement-view.fxml"));
        InputStream inputStream = getClass().getResourceAsStream("/icons/logo.png");

        if (inputStream != null) {
            Image image = new Image(inputStream);
            stage.getIcons().add(image);
        } else {
            System.err.println("Failed to load image: logo.png");
        }

        Scene sceneprofileManagement = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Stock Pilot");
        stage.setScene(sceneprofileManagement);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}

