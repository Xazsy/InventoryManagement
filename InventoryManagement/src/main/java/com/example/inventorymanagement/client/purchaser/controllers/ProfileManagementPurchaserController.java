package com.example.inventorymanagement.client.purchaser.controllers;

import com.example.inventorymanagement.client.admin.controllers.NavigationBarAdminController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileManagementPurchaserController extends Application {
    public void start(Stage stage) throws IOException {

        Font.loadFont(getClass().getResourceAsStream("/fonts/ShareTechMono-Regular.ttf"), 20);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/inventorymanagement/client/view/navigationBar/navigationBarAdmin-view.fxml"));
        BorderPane navigationBar = loader.load();

        // Get the controller
        NavigationBarAdminController navBarAdminController = loader.getController();

        // Create the profile management panel
        BorderPane profileManagementAdminPanel = FXMLLoader.load(getClass().getResource("/com/example/inventorymanagement/client/view/profileManagement/profileManagement-view.fxml"));

        BorderPane root = new BorderPane();
        root.setLeft(navigationBar);
        root.setRight(profileManagementAdminPanel);

        Scene scene = new Scene(root, 1080, 650);
        stage.setScene(scene);
        stage.setTitle("Stock Pilot");
        stage.show();

        // Set the main BorderPane reference in the navigation bar controller
        navBarAdminController.setMainBorderPane(root);
    }

    public static void main(String[] args) {
        launch();
    }
}
