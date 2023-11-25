/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Ressource;
import tn.edu.esprit.entities.Terrain;
import tn.edu.esprit.services.ServiceRessource;

/**
 * FXML Controller class
 *
 * @author rihab
 */
public class AjouterRessourceFXMLController implements Initializable {

    @FXML
    private RadioButton typeRes1;
    @FXML
    private RadioButton typeRes2;
    @FXML
    private TextField txtSpecies;
    @FXML
    private TextField txtQuantite;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private Terrain selectedTerrain ;

    public void initData(Terrain terrain) {
        this.selectedTerrain = terrain;
        }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeRes1.setToggleGroup(toggleGroup);
        typeRes2.setToggleGroup(toggleGroup);
    }    
    

    @FXML
private void AjouterRessource(ActionEvent event) {
    Terrain TerrainSelectionne = SelectedTerrainManager.getSelectedTerrain();
    ServiceRessource sr = new ServiceRessource();
    
    String type = "";
    if (typeRes1.isSelected()) {
        type = "animaux";
    } else if (typeRes2.isSelected()) {
        type = "plantes";
    }
    
    int quantite = 0;
    try {
        quantite = Integer.parseInt(txtQuantite.getText());
        if (quantite <= 0) {
            afficherAlerte("Erreur de saisie", "La quantité doit être un nombre positif.");
            return;
        }
    } catch (NumberFormatException e) {
        afficherAlerte("Erreur de format", "La quantité doit être un nombre entier valide.");
        return;
    }
    
    String species = txtSpecies.getText();
    
    if (species.isEmpty()) {
        afficherAlerte("Erreur de saisie", "Le champ species ne peut pas être vide.");
        return;
    }
    
    Ressource existingRessource = sr.getOneBySpecies(species);
    if (existingRessource != null) {
        afficherAlerte("Erreur d'ajout", "L'espèce de ressource existe déjà.");
        return;
    }
    
    int idterrain = TerrainSelectionne.getIdTerrain();
    sr.ajouter(new Ressource(type, species, quantite, idterrain));
    afficherConfirmation("Succès", "La ressource a été ajoutée avec succès.");
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
    private void RetourAjoutRes(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherTerrainFxml.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Afficher Terrain"); // Titre de la nouvelle fenêtre
        stage.show();
    } catch (IOException ex) {
        System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
    }
    }
    
}
