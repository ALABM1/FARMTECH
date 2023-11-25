package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import tn.edu.esprit.entities.Ressource;
import tn.edu.esprit.entities.Terrain;
import tn.edu.esprit.services.ServiceRessource;

public class AfficherRessourceFXMLController implements Initializable {

    @FXML
    private TableView<Ressource> viewRessource;
    private List<Ressource> data;
    @FXML
private TableColumn<Ressource, String> typeRes;
@FXML
private TableColumn<Ressource, String> speciesRes;
@FXML
private TableColumn<Ressource, Integer> quantiteRes;
    @FXML
    private TextField txtRechercherRes;
    @FXML
    private Button suppRES;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceRessource sr = new ServiceRessource();
         editData();
         
         viewRessource.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Un élément a été sélectionné
            suppRES.setVisible(true);
        } else {
            suppRES.setVisible(false);
        }
    });
    }

    
    
    void initData(Terrain selectedTerrain) {
         
    ServiceRessource sr = new ServiceRessource();
    int idTerrain = selectedTerrain.getIdTerrain();
    List<Ressource> ressource = sr.getAllres(idTerrain);
    
    // Mettez à jour la TableView
    viewRessource.setItems(FXCollections.observableArrayList(ressource));
   
    // Set cell value factories
    typeRes.setCellValueFactory(new PropertyValueFactory<>("typeRes"));
    speciesRes.setCellValueFactory(new PropertyValueFactory<>("speciesRes"));
    quantiteRes.setCellValueFactory(new PropertyValueFactory<>("quantiteRes"));
    }
    
    

    @FXML
private void SupprimerRessource(ActionEvent event) {
    Ressource ressourceSelectionnee = viewRessource.getSelectionModel().getSelectedItem();
    ServiceRessource sr = new ServiceRessource();
    if (ressourceSelectionnee != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette Ressource ?");

        ButtonType boutonOui = new ButtonType("Oui");
        ButtonType boutonNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(boutonOui, boutonNon);
        
        alert.showAndWait().ifPresent(reponse -> {
        if (reponse == boutonOui) {
        
        sr.supprimer(ressourceSelectionnee.getIdRes()); // Supprimez l'élément de la base de données

        // Mettez à jour la TableView
        viewRessource.getItems().remove(ressourceSelectionnee); // Supprimez l'élément de la TableView
        }});
    } else {
        System.out.println("Vous devez sélectionner une ressource avant de la supprimer.");
    }
}

    

    private void editData() {
    typeRes.setCellFactory(TextFieldTableCell.<Ressource>forTableColumn());
    typeRes.setOnEditCommit(event -> {
        Ressource ressource = event.getTableView().getItems().get(event.getTablePosition().getRow());
        ressource.setTypeRes(event.getNewValue());
        System.out.println("Le type de ressource a été mis à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        ServiceRessource sr = new ServiceRessource();
        sr.modifier(ressource);
    });

    speciesRes.setCellFactory(TextFieldTableCell.<Ressource>forTableColumn());
    speciesRes.setOnEditCommit(event -> {
        Ressource ressource = event.getTableView().getItems().get(event.getTablePosition().getRow());
        ressource.setSpeciesRes(event.getNewValue());
        System.out.println("le species de ressource a été mise à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        ServiceRessource sr = new ServiceRessource();
        sr.modifier(ressource);
    });

    quantiteRes.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    quantiteRes.setOnEditCommit(event -> {
        Ressource ressource = event.getTableView().getItems().get(event.getTablePosition().getRow());
        ressource.setQuantiteRes(event.getNewValue());
        System.out.println("La quantité de ressource a été mise à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        ServiceRessource sr = new ServiceRessource();
        sr.modifier(ressource);
    });
}
     
     

    @FXML
    private void retourAfficheRessource(ActionEvent event) {
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
    private void chercherRessource(ActionEvent event) {
            String species = txtRechercherRes.getText();

    // Appelez la méthode getOneBySpecies pour obtenir la ressource correspondante
    ServiceRessource sr = new ServiceRessource();
    Ressource r = sr.getOneBySpecies(species);

    // Créez une liste contenant cette ressource (ou vide si aucune correspondance n'est trouvée)
    List<Ressource> data = new ArrayList<>();
    if (r != null) {
        data.add(r);
    }

    // Mettez à jour votre TableView avec cette liste
    viewRessource.setItems(FXCollections.observableArrayList(data));

    // Configurez les cell value factories pour afficher les propriétés de la ressource
    typeRes.setCellValueFactory(new PropertyValueFactory<Ressource, String>("typeRes"));
    speciesRes.setCellValueFactory(new PropertyValueFactory<Ressource, String>("speciesRes"));
    quantiteRes.setCellValueFactory(new PropertyValueFactory<Ressource, Integer>("quantiteRes"));
    }

 
}
