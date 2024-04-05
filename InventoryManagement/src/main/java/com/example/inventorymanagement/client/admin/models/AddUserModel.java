package com.example.inventorymanagement.client.admin.models;

import com.example.inventorymanagement.client.model.ClientCallbackImpl;
import com.example.inventorymanagement.util.ClientCallback;
import com.example.inventorymanagement.util.exceptions.NotLoggedInException;
import com.example.inventorymanagement.util.exceptions.OutOfRoleException;
import com.example.inventorymanagement.util.exceptions.UserExistenceException;
import com.example.inventorymanagement.util.objects.User;
import com.example.inventorymanagement.util.requests.UserRequestInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AddUserModel {

    public void process (User requestBy,User newUser){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            UserRequestInterface userRequest = (UserRequestInterface) registry.lookup("UserRequestService");

            ClientCallback cB = new ClientCallbackImpl(requestBy);

            userRequest.addUser(cB, requestBy, newUser);

        } catch (RemoteException | NotBoundException | NotLoggedInException | OutOfRoleException |
                 UserExistenceException e) {
            throw new RuntimeException(e);
        }
    }
}
