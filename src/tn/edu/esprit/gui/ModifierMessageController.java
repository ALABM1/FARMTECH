/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import tn.edu.esprit.entities.Message;

/**
 * FXML Controller class
 *
 * @author megbl
 */

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ModifierMessageController {

    @FXML
    private TextField fxText;

    @FXML
    private TextField fxDestinataire;

    @FXML
    private TextField fxSource;

    @FXML
    private TextField fxDate;

    @FXML
    private RadioButton fxON;

    @FXML
    private RadioButton fxOff;

    @FXML
    private void fxModifier() {
        // Handle modification logic here
        String text = fxText.getText();
        String destinataire = fxDestinataire.getText();
        String source = fxSource.getText();
        String date = fxDate.getText();
        boolean isOn = fxON.isSelected();  // Assuming fxON represents "on marche" state

        // Perform the necessary modifications based on the input values
        // You can use these values to update the message in your database or wherever it's stored

        // Optionally, you can show a success message or perform other actions upon modification
        System.out.println("Message modified successfully!");
    }

    @FXML
    private void fxgotolist() {
        // Handle going back to the list or previous screen
        // You can navigate back to the list view or perform other actions as needed
    }
}

