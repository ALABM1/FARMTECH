/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jouin
 */
public class TresorerieFXMLController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button buttontransaction;
    @FXML
    private Button buttoncategorie;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void gototransaction(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("../gui/tabletransFXML.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Transaction");
            stage.show();
}
    @FXML
    private void gotocategorie(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("../gui/tablecatFXML.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
             stage.setTitle("Categorie transaction");
            stage.show();
    }

    @FXML
    private void GoToWelcome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Agriculteur1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Liste de materiels");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GetAllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }





