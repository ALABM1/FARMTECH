/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.edu.esprit.entities.Terrain;
import tn.edu.esprit.services.ServiceTerrain;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author rihab
 */
public class AfficherTerrainFXMLController implements Initializable {

  
    @FXML
    private TableView<Terrain> viewTerrain;
    @FXML
    private TableColumn<Terrain, String> nomTerrain;
    @FXML
    private TableColumn<Terrain, String> localisation;
    @FXML
    private TableColumn<Terrain, String> superficie;
    private List<Terrain> data;
    @FXML
    private TextField txtchercherTerrain;
    @FXML
    private Button suppTerrain;
    @FXML
    private Button addRES;
    @FXML
    private Button showRES;
    @FXML
    private Button btnAjouterTerrain;
    @FXML
    private Button btnRetourAfficherTerrain;
    @FXML
    private Label hectareLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editTerrain(); 
        afficheTerrain(null);
         viewTerrain.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Un élément a été sélectionné
            suppTerrain.setVisible(true);
        } else {
            suppTerrain.setVisible(false);
        }
    });
         
        viewTerrain.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Un élément a été sélectionné
            addRES.setVisible(true);
        } else {
            addRES.setVisible(false);
        }
    });
         
       viewTerrain.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Un élément a été sélectionné
            showRES.setVisible(true);
        } else {
            showRES.setVisible(false);
        }
    });  
    }

   
    

    @FXML
    private void SupprimerTerrain(ActionEvent event) {
    ServiceTerrain st = new ServiceTerrain();
    Terrain terrainSelectionne = viewTerrain.getSelectionModel().getSelectedItem();

    if (terrainSelectionne != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette Terrain ?");

        ButtonType boutonOui = new ButtonType("Oui");
        ButtonType boutonNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(boutonOui, boutonNon);
        
        alert.showAndWait().ifPresent(reponse -> {
        if (reponse == boutonOui) {
        st.supprimer(terrainSelectionne.getIdTerrain()); // Supprimez l'élément de la base de données

        // Mettez à jour la TableView
        data.remove(terrainSelectionne);
        viewTerrain.getItems().setAll(data);
        }});
    } else {
        System.out.println("Vous devez sélectionner un élément avant de le supprimer.");
    }
}
    
    private void editTerrain() {
    nomTerrain.setCellFactory(TextFieldTableCell.forTableColumn());
    nomTerrain.setOnEditCommit(event -> {
        Terrain terrain = event.getTableView().getItems().get(event.getTablePosition().getRow());
        terrain.setNomTerrain(event.getNewValue());
        System.out.println("Le nom du terrain " + terrain.getNomTerrain() + " a été mis à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        ServiceTerrain st = new ServiceTerrain();
        st.modifier(terrain);
    });

    localisation.setCellFactory(TextFieldTableCell.forTableColumn());
    localisation.setOnEditCommit(event -> {
        Terrain terrain = event.getTableView().getItems().get(event.getTablePosition().getRow());
        terrain.setLocalisation(event.getNewValue());
        System.out.println("La localisation du terrain " + terrain.getLocalisation() + " a été mise à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        ServiceTerrain st = new ServiceTerrain();
        st.modifier(terrain);
    });

    superficie.setCellFactory(TextFieldTableCell.forTableColumn());
    superficie.setOnEditCommit(event -> {
        Terrain terrain = event.getTableView().getItems().get(event.getTablePosition().getRow());
        terrain.setSuperficie(event.getNewValue());
        System.out.println("La superficie du terrain " + terrain.getSuperficie() + " a été mise à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        ServiceTerrain st = new ServiceTerrain();
        st.modifier(terrain);
    });
} 

    
      

    @FXML
    private void AjouterRessource(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterRessourceFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Terrain selectedTerrain = viewTerrain.getSelectionModel().getSelectedItem();

        if (selectedTerrain != null) {
            SelectedTerrainManager.setSelectedTerrain(selectedTerrain);

            AjouterRessourceFXMLController controller = loader.getController();
            controller.initData(selectedTerrain);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Ajouter Ressource ");
            stage.show();
        } else {
            // Aucun parc sélectionné, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun parc sélectionné.\nVeuillez séelectionner une parc de la liste.");
            alert.showAndWait();
        }
    } catch (IOException ex) {
        System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
    }
    }

    @FXML
private void AjouterTerrain(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterTerrainFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle à partir du bouton
            Scene currentScene = btnAjouterTerrain.getScene();

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
            stage.setTitle("Ajouter Terrain");
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
        }
    }


    @FXML
    private void retourAfficherTerrain(ActionEvent event) {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/firstPageFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle à partir du bouton
            Scene currentScene = btnRetourAfficherTerrain.getScene();

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
            stage.setTitle("First Page");
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
        }
    
}

    @FXML
    private void afficherRessourceTerrain(ActionEvent event) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherRessourceFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Terrain selectedTerrain = viewTerrain.getSelectionModel().getSelectedItem();
        if (selectedTerrain!= null) {
        // Set the selected parc's name to the test TextField
        AfficherRessourceFXMLController t = loader.getController();
        t.initData(selectedTerrain);
        SelectedTerrainManager.setSelectedTerrain(selectedTerrain);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Liste des Ressources");
        stage.show();
        } else {
            // Aucun parc sélectionné, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Terrain sélectionné.\nVeuillez séelectionner un terrain de la liste.");
            alert.showAndWait();
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    private void afficheTerrain(ActionEvent event) {
    ServiceTerrain sp = new ServiceTerrain();
        Terrain t = new Terrain();
        data = sp.getAll(t);

        viewTerrain.setItems(FXCollections.observableArrayList(data));
        nomTerrain.setCellValueFactory(new PropertyValueFactory<Terrain , String>("nomTerrain"));
        localisation.setCellValueFactory(new PropertyValueFactory<Terrain , String>("localisation"));
        superficie.setCellValueFactory(new PropertyValueFactory<Terrain , String>("Superficie"));
    }

    @FXML
    private void chercherTerrain(ActionEvent event) {
    String nom = txtchercherTerrain.getText();

    // Appelez la méthode getOneBySpecies pour obtenir la ressource correspondante
    ServiceTerrain st = new ServiceTerrain();
    Terrain t = st.getOneByNom(nom);

    // Créez une liste contenant cette ressource (ou vide si aucune correspondance n'est trouvée)
    List<Terrain> data = new ArrayList<>();
    if (t != null) {
        data.add(t);
    }
        
        
        viewTerrain.setItems(FXCollections.observableArrayList(data));
        nomTerrain.setCellValueFactory(new PropertyValueFactory<Terrain , String>("nomTerrain"));
        localisation.setCellValueFactory(new PropertyValueFactory<Terrain , String>("localisation"));
        superficie.setCellValueFactory(new PropertyValueFactory<Terrain , String>("Superficie"));
    
    }

    @FXML
    private void refresh(ActionEvent event) {
    try {
        // Chargez le fichier FXML de la vue AfficherTerrain
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherTerrainFXML.fxml"));
        Parent root = loader.load();

        // Créez une nouvelle scène avec la vue AfficherTerrain
        Scene scene = new Scene(root);

        // Obtenez la fenêtre actuelle à partir de l'événement
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Affichez la nouvelle scène dans la fenêtre
        currentStage.setScene(scene);
        currentStage.setTitle("Afficher Terrain"); // Mettez à jour le titre de la fenêtre si nécessaire
        currentStage.show();
    } catch (IOException ex) {
        System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
    }
}

    @FXML
private void afficherSurCarte(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MapFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Terrain selectedTerrain = viewTerrain.getSelectionModel().getSelectedItem();

        if (selectedTerrain != null) {
            // Obtenez le moteur WebEngine du WebView dans le contrôleur MapFXML
            MapFXMLController mapController = loader.getController();
            WebEngine webEngine = mapController.mapView.getEngine();

            // Créez l'URL Google Maps avec la localisation
            String googleMapsUrl = "https://www.google.com/maps/place/" + selectedTerrain.getLocalisation();

            // Chargez la page Google Maps dans le WebView
            webEngine.load(googleMapsUrl);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Carte Google Maps");
            stage.show();
        } else {
            // Gérez le cas où aucun terrain n'est sélectionné.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun terrain sélectionné.\nVeuillez sélectionner un terrain dans la liste.");
            alert.showAndWait();
        }
    } catch (IOException ex) {
        System.out.println("Erreur lors du chargement de la page de la carte : " + ex.getMessage());
    }
}

   @FXML
private void convertToHectares(ActionEvent event) {
    Terrain terrainSelectionne = viewTerrain.getSelectionModel().getSelectedItem();

    if (terrainSelectionne != null) {
        try {
            double superficieM2 = Double.parseDouble(terrainSelectionne.getSuperficie());
            double superficieHa = superficieM2 / 10000.0; // 1 hectare = 10 000 m²
            hectareLabel.setText("Superficie en hectares : " + superficieHa + " ha");
        } catch (NumberFormatException ex) {
            hectareLabel.setText("Erreur de conversion");
        }
    } else {
        // Gérez le cas où aucune ligne n'est sélectionnée.
        hectareLabel.setText("Sélectionnez une ligne pour convertir.");
    }
}



    


}
    
