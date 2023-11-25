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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Parc;
import tn.edu.esprit.services.ServiceParc;

/**
 * FXML Controller class
 *
 * @author megbl
 */
public class AjouterParcFXMLController implements Initializable {

    @FXML
    private TextField fxNomParc;
    @FXML
    private TextField fxAdresseParc;
    @FXML
    private TextField fxSuperficieParc;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

   @FXML
private void fxAjouterParc(ActionEvent event) {  
    try {
        String nomParc = fxNomParc.getText();
        String adresseParc = fxAdresseParc.getText();
        float superficie = Float.parseFloat(fxSuperficieParc.getText());

        // Vérification du nom du parc
        if (nomParc.isEmpty()) {
            afficherAlerte("Erreur de saisie", "Le nom du parc ne peut pas être vide.");
            return; // Sortir de la méthode si la saisie est invalide
        }

        // Vérification de l'adresse du parc
        if (adresseParc.isEmpty()) {
            afficherAlerte("Erreur de saisie", "L'adresse du parc ne peut pas être vide.");
            return; // Sortir de la méthode si la saisie est invalide
        }

        // Vérification de la superficie du parc
        if (superficie <= 0) {
            afficherAlerte("Erreur de saisie", "La superficie doit être un nombre positif.");
            return; // Sortir de la méthode si la saisie est invalide
        }

        ServiceParc sp = new ServiceParc();
        Parc existant = sp.getOne(nomParc);

        if (existant != null) {
            afficherAlerte("Erreur d'ajout", "Le nom de parc existe déjà.");
            return; // Sortir de la méthode si le parc existe déjà
        }

        sp.ajouter(new Parc(nomParc, adresseParc, superficie));
        afficherConfirmation("Succès", "Le parc a été ajouté avec succès.");
    } catch (NumberFormatException e) {
        afficherAlerte("Erreur de format", "La superficie doit être un nombre valide.");
    }
}
private void afficherAlerte(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}
private void afficherConfirmation(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}

    @FXML
    private void fxGoToALLParcs(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/GetAllFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Liste de materiels");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterParcFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

