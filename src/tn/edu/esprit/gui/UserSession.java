/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import tn.edu.esprit.entities.User;

/**
 *
 * @author megbl
 */
public class UserSession {
    
    private static User connectedUser;

    public static void setConnectedUser(User user) {
        connectedUser = user;
    }

    public static User getConnectedUser() {
        return connectedUser;
    }
}

    

