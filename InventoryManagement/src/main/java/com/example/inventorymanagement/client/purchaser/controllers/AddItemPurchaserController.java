package com.example.inventorymanagement.client.purchaser.controllers;

import com.example.inventorymanagement.client.purchaser.views.AddItemPurchaserPanel;
import com.example.inventorymanagement.util.ControllerInterface;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AddItemPurchaserController extends Application implements Initializable, ControllerInterface {
    @FXML private TextField itemNameField;
    @FXML private TextField itemPriceField;
    @FXML private TextField quantityField;
    public TextField getItemNameField() {
        return itemNameField;
    }

    public TextField getItemPriceField() {
        return itemPriceField;
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    private AddItemPurchaserPanel addItemPurchaserPanel = new AddItemPurchaserPanel();
    //    private AddItemAdminModel addItemAdminModel = new AddItemAdminModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void fetchAndUpdate() throws RemoteException {

    }

    @Override
    public String getObjectsUsed() throws RemoteException {
        return null;
    }
    @Override
    public void start(Stage stage) throws Exception {
        addItemPurchaserPanel.start(stage);
    }
}
