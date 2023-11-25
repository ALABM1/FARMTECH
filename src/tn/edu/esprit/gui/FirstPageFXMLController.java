/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.edu.esprit.services.ServiceActivite;
import tn.edu.esprit.services.ServiceRessource;
import tn.edu.esprit.services.ServiceTerrain;

/**
 * FXML Controller class
 *
 * @author rihab
 */
public class FirstPageFXMLController implements Initializable {

    @FXML
    private Button btnGestionTerrain;
    @FXML
    private Button btnGestionActivité;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gestionTerrain(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherTerrainFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle à partir du bouton
            Scene currentScene = btnGestionTerrain.getScene();

            // Créez une transition de translation
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), root);
            translateTransition.setFromX(currentScene.getWidth());
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

     @FXML
    private void gestionActivité(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherActiviteFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle à partir du bouton
            Scene currentScene = btnGestionActivité.getScene();

            // Créez une transition de translation
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), root);
            translateTransition.setFromX(currentScene.getWidth());
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
            stage.setTitle("Afficher Activité");
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
        }
    }

   @FXML
private void afficherStat(ActionEvent event) {
    ServiceTerrain st = new ServiceTerrain();
    ServiceRessource sr = new ServiceRessource();
    ServiceActivite sa = new ServiceActivite();
    // Logique de calcul des statistiques
    int nombreTerrains = st.getNombreTotalTerrains();// Calcul du nombre de terrains depuis votre service
    double superficieTotale = st.getSuperficieTotale();// Calcul de la superficie totale depuis votre service
    int sommeActiviteEnAttente = sa.sommeActivitesEnAttente(); // Obtenez la somme des activités en attente
    int sommeActiviteTerminees = sa.sommeActivitesTerminees();
    
    // Obtenez une référence à la scène actuelle à partir du bouton
    Scene currentScene = btnGestionTerrain.getScene();

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Statistique.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Obtenez le contrôleur de la page Statistique
        StatistiqueController statistiqueController = loader.getController();

        // Appelez les méthodes pour définir les statistiques
        statistiqueController.setNombreTerrain(Integer.toString(nombreTerrains));
        statistiqueController.setTotalSuperficie(Double.toString(superficieTotale));
        
        statistiqueController.setSommeActivitesEnAttente(Integer.toString(sommeActiviteEnAttente));
        statistiqueController.setSommeActivitesTerminees(Integer.toString(sommeActiviteTerminees));

        // Obtenez le nombre total de ressources pour chaque terrain
       Map<String, Integer> statistiquesTerrains = sr.getNombreTotalRessourcesPourTousTerrains();
        // Appelez la méthode pour définir les statistiques des ressources par terrain
        statistiqueController.setStatistiquesTerrains(statistiquesTerrains);

        // Créez des transitions comme vous l'avez fait pour d'autres boutons

        // Changez de scène après la fin de la transition
        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(scene);
        stage.setTitle("Statistique");
    } catch (IOException ex) {
        System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
    }
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
