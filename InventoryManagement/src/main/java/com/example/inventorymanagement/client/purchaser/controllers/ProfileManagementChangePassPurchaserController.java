package com.example.inventorymanagement.client.purchaser.controllers;

import com.example.inventorymanagement.client.admin.models.ProfileManagementAdminModel;
import com.example.inventorymanagement.client.admin.models.ProfileManagementChangePassAdminModel;
import com.example.inventorymanagement.client.admin.views.ProfileManagementChangePassAdminPanel;
import com.example.inventorymanagement.client.common.controllers.MainController;
import com.example.inventorymanagement.client.model.ClientCallbackImpl;
import com.example.inventorymanagement.client.purchaser.models.ProfileManagementChangePassPurchaserModel;
import com.example.inventorymanagement.util.ClientCallback;
import com.example.inventorymanagement.util.ControllerInterface;
import com.example.inventorymanagement.util.exceptions.NotLoggedInException;
import com.example.inventorymanagement.util.exceptions.OutOfRoleException;
import com.example.inventorymanagement.util.exceptions.UserExistenceException;
import com.example.inventorymanagement.util.objects.User;
import com.example.inventorymanagement.util.requests.ItemOrderRequestInterface;
import com.example.inventorymanagement.util.requests.ItemRequestInterface;
import com.example.inventorymanagement.util.requests.UserRequestInterface;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.ResourceBundle;

import static com.example.inventorymanagement.client.common.controllers.MainController.clientCallback;
import static com.example.inventorymanagement.client.common.controllers.MainController.registry;

public class ProfileManagementChangePassPurchaserController  implements ControllerInterface {
    @FXML
    private BorderPane borderPaneProfileManagementChangePass;
    @FXML
    private ImageView personIconImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label changePasswordLabel;
    @FXML
    private TextField oldPasswordTextField;
    @FXML
    private TextField newPasswordTextField;
    @FXML
    private Button saveButton;
    private ListView<User> userListView;

    // Getter methods of FXMl components

    public BorderPane getBorderPaneProfileManagementChangePass() {
        return borderPaneProfileManagementChangePass;
    }

    public ImageView getPersonIconImage() {
        return personIconImage;
    }

    public Label getChangePasswordLabel() {
        return changePasswordLabel;
    }

    public TextField getOldPasswordTextField() {
        return oldPasswordTextField;
    }

    public TextField getNewPasswordTextField() {
        return newPasswordTextField;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Button getSaveButton() {
        return saveButton;
    }
    private ProfileManagementChangePassPurchaserModel profileManagementChangePassPurchaserModel;
    private MainController mainController;

    public ProfileManagementChangePassPurchaserController(){
        // Default Constructor
    }

    public ProfileManagementChangePassPurchaserController(ClientCallback clientCallback, UserRequestInterface userService, ItemOrderRequestInterface iOService, ItemRequestInterface itemService, Registry registry, MainController mainController) {
        this.profileManagementChangePassPurchaserModel = new ProfileManagementChangePassPurchaserModel(registry, clientCallback);
    }

    boolean initialized = false;
    public void fetchAndUpdate() throws RemoteException {
        try {
            // Fetch user data from the model
            updateUsernameLabel();
            LinkedList<User> userList = profileManagementChangePassPurchaserModel.fetchListOfUsers(); // Fetch list of users from the model
            if (userList != null && !userList.isEmpty()) {
                // Assuming you want to display the first user in the list
                User user = userList.getFirst();

                // Update UI components with user data
                if (user != null) {
                    usernameLabel.setText(user.getUsername());
                    // You can update other UI components as needed
                } else {
                    System.out.println("User data not available.");
                }
            } else {
                // Handle the case where user list is empty or null
                System.out.println("User list is empty or null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public String getObjectsUsed() throws RemoteException {
        return "User";
    }

    private void addHoverEffect (Button button){
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: derive(#EAD7D7, -10%);"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #EAD7D7;"));
    }
    @FXML
    private void handleSave() {
        String newPassword = newPasswordTextField.getText();

        try {
            // Fetch the list of users
            LinkedList<User> userList = profileManagementChangePassPurchaserModel.fetchListOfUsers();

            // Check if the user list is not null and not empty
            if (userList != null && !userList.isEmpty()) {
                // Get the first user from the list
                User user = userList.getFirst();

                // Call the changePassword method from the model
                try {
                    boolean success = profileManagementChangePassPurchaserModel.changePassword(user, newPassword);
                    if (success) {
                        // Password change was successful
                        showInformationDialog("Success", "Password changed successfully.");
                    } else {
                        // Password change failed
                        showErrorDialog("Error", "Failed to change password.");
                    }
                } catch (UserExistenceException | OutOfRoleException | NotLoggedInException e) {
                    // Handle specific exceptions
                    e.printStackTrace();
                    showErrorDialog("Error", e.getMessage());
                }
            } else {
                // Handle the case where user list is empty or null
                showErrorDialog("Error", "No users found.");
            }
        } catch (NotLoggedInException | OutOfRoleException e) {
            // Handle exceptions related to user authentication and authorization
            e.printStackTrace();
            showErrorDialog("Error", e.getMessage());
        }
    }


    // Helper method to show an information dialog
    private void showInformationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Helper method to show an error dialog
    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void updateUsernameLabel() {
        try {
            usernameLabel.setText(clientCallback.getUser().getUsername());
        } catch (RemoteException e) {
            //Prompt user unable to fetch User object
        }
    }

    public void initialize() {
        // sout initialize
        System.out.println("initialize");
        addHoverEffect(saveButton);

        //add action handlers
        saveButton.setOnAction(event -> handleSave());
        profileManagementChangePassPurchaserModel = new ProfileManagementChangePassPurchaserModel(registry, clientCallback);
        if (!initialized) {
            initialized = true;
            try {
                if (profileManagementChangePassPurchaserModel != null) {
                    fetchAndUpdate();
                }
                else {
                    System.out.println("Profile Management Change Pass Admin Model is null");
                }
            } catch (RemoteException e) {
                System.out.println("User is not logged in");
            }
        }else {
            System.out.println("Error: Save button is null. Cannot Initialize");
        }
    }
}




