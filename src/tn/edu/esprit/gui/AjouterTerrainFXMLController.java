/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.edu.esprit.entities.Terrain;
import tn.edu.esprit.services.ServiceTerrain;

/**
 * FXML Controller class
 *
 * @author rihab
 */
public class AjouterTerrainFXMLController implements Initializable {

    @FXML
    private TextField txtNomTerrain;
    @FXML
    private TextField txtSuperficieTerrain;
    @FXML
    private TextField txtLocalisation;
    @FXML
    private Button btnRetourAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
private void AjouterTerrain(ActionEvent event) {
    try {
        String nomTerrain = txtNomTerrain.getText();
        String localisation = txtLocalisation.getText();
        float superficie = Float.parseFloat(txtSuperficieTerrain.getText());

        // Vérification du nom du terrain
        if (nomTerrain.isEmpty()) {
            afficherAlerte("Erreur de saisie", "Le nom du terrain ne peut pas être vide.");
            return; // Sortir de la méthode si la saisie est invalide
        }

        // Vérification de la localisation du terrain
        if (localisation.isEmpty()) {
            afficherAlerte("Erreur de saisie", "La localisation du terrain ne peut pas être vide.");
            return; // Sortir de la méthode si la saisie est invalide
        }

        // Vérification de la superficie du terrain
        if (superficie <= 0) {
            afficherAlerte("Erreur de saisie", "La superficie doit être non vide ou un nombre positif.");
            return; // Sortir de la méthode si la saisie est invalide
        }

        ServiceTerrain st = new ServiceTerrain();
        Terrain existant = st.getOneByNom(nomTerrain);

        if (existant != null) {
            afficherAlerte("Erreur d'ajout", "Le nom du terrain existe déjà.");
            return; // Sortir de la méthode si le terrain existe déjà
        }

        st.ajouter(new Terrain(nomTerrain, localisation, String.valueOf(superficie)));
        afficherConfirmation("Succès", "Le terrain a été ajouté avec succès.");
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
    private void RetourAjout(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherTerrainFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle à partir du bouton
            Scene currentScene = btnRetourAjout.getScene();

            // Créez une transition de translation
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), root);
            translateTransition.setFromX(-currentScene.getWidth());
            translateTransition.setToX(0);

            // Créez une transition de fondu
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), root);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);

            // Exécutez les deux transitions en parallèle
            translateTransition.play();
            fadeTransition.play();

            // Changez de scène après la fin de la transition
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(scene);
            stage.setTitle("Afficher Terrain");
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
        }
    }
    
}
