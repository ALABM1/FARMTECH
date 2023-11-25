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
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.edu.esprit.entities.Activite;
import tn.edu.esprit.services.ServiceActivite;

/**
 * FXML Controller class
 *
 * @author rihab
 */
public class AfficherActiviteFXMLController implements Initializable {

   @FXML
    private TableView<Activite> viewActivite;
    private List<Activite> data;
    @FXML
    private TableColumn<Activite, String> objetA;
    @FXML
    private TableColumn<Activite, String> descriptionA;
    @FXML
    private TableColumn<Activite, String> distinataireA;
    @FXML
    private TableColumn<Activite, String> EmailDisA;
    @FXML
    private TableColumn<Activite, String> speciesAct;
    @FXML
    private TextField chercherActivite;
    @FXML
    private Button supACT;
    @FXML
    private Button btnRetourAfficherActivite;
    @FXML
    private Button btnAjouterNouveauActivite;
    @FXML
    private TableColumn<Activite, String> etatAct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editData();
        AfficheActivite();
        viewActivite.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Un élément a été sélectionné
            supACT.setVisible(true);
        } else {
            supACT.setVisible(false);
        }
    });
    }    

    private void AfficheActivite() {
        ServiceActivite sa = new ServiceActivite();
        Activite a = new Activite();
        data = sa.getAll(a);
        objetA.setCellValueFactory(new PropertyValueFactory<Activite, String>("objetAct"));
        descriptionA.setCellValueFactory(new PropertyValueFactory<Activite, String>("descriptionAct"));
        distinataireA.setCellValueFactory(new PropertyValueFactory<Activite, String>("distAct"));
        EmailDisA.setCellValueFactory(new PropertyValueFactory<Activite, String>("emailDist"));
        speciesAct.setCellValueFactory(new PropertyValueFactory<Activite, String>("speciesRES"));
        etatAct.setCellValueFactory(new PropertyValueFactory<Activite, String>("etatAct"));
        viewActivite.setItems(FXCollections.observableArrayList(data));
    }

    @FXML
    private void SupprimerActivite(ActionEvent event) {
        Activite activiteSelectionnee = viewActivite.getSelectionModel().getSelectedItem();
        ServiceActivite serviceActivite = new ServiceActivite();
        
        if (activiteSelectionnee != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette Activité ?");

        ButtonType boutonOui = new ButtonType("Oui");
        ButtonType boutonNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(boutonOui, boutonNon);
        
        alert.showAndWait().ifPresent(reponse -> {
        if (reponse == boutonOui) {    
            serviceActivite.supprimer(activiteSelectionnee.getIdAct()); // Supprimez l'activité de la base de données

            // Mettez à jour la TableView
            data.remove(activiteSelectionnee);
            viewActivite.getItems().setAll(data);
            
        }});
        } else {
            System.out.println("Vous devez sélectionner une activité avant de la supprimer.");
        }
    }
    
    
    private void editData() {
    objetA.setCellFactory(TextFieldTableCell.<Activite>forTableColumn());
    objetA.setOnEditCommit(event -> {
        Activite activite = event.getTableView().getItems().get(event.getTablePosition().getRow());
        activite.setObjetAct(event.getNewValue());
        System.out.println("L'objet de l'activité a été mis à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        ServiceActivite sa = new ServiceActivite();
        sa.modifier(activite);
    });

    descriptionA.setCellFactory(TextFieldTableCell.<Activite>forTableColumn());
    descriptionA.setOnEditCommit(event -> {
        Activite activite = event.getTableView().getItems().get(event.getTablePosition().getRow());
        activite.setDescriptionAct(event.getNewValue());
        System.out.println("La description de l'activité a été mise à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        ServiceActivite sa = new ServiceActivite();
        sa.modifier(activite);
    });

    distinataireA.setCellFactory(TextFieldTableCell.<Activite>forTableColumn());
    distinataireA.setOnEditCommit(event -> {
        Activite activite = event.getTableView().getItems().get(event.getTablePosition().getRow());
        activite.setDistAct(event.getNewValue());
        System.out.println("Le destinataire de l'activité a été mis à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        ServiceActivite sa = new ServiceActivite();
        sa.modifier(activite);
    });

    EmailDisA.setCellFactory(TextFieldTableCell.<Activite>forTableColumn());
    EmailDisA.setOnEditCommit(event -> {
        Activite activite = event.getTableView().getItems().get(event.getTablePosition().getRow());
        activite.setEmailDist(event.getNewValue());
        System.out.println("L'e-mail du destinataire de l'activité a été mis à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        ServiceActivite sa = new ServiceActivite();
        sa.modifier(activite);
    });
    
    speciesAct.setCellFactory(TextFieldTableCell.<Activite>forTableColumn());
    speciesAct.setOnEditCommit(event -> {
    Activite activite = event.getTableView().getItems().get(event.getTablePosition().getRow());
    activite.setSpeciesRES(event.getNewValue());
    System.out.println("La colonne speciesAct a été mise à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
    ServiceActivite sa = new ServiceActivite();
    sa.modifier(activite);
});
    etatAct.setCellFactory(TextFieldTableCell.forTableColumn());
etatAct.setOnEditCommit(event -> {
    Activite activite = event.getTableView().getItems().get(event.getTablePosition().getRow());
    activite.setEtatAct(event.getNewValue());
    // Appel à la méthode de mise à jour pour sauvegarder l'état modifié dans la base de données
    ServiceActivite sa = new ServiceActivite();
    sa.modifier(activite);
});
}

    @FXML
    private void retourAfficherActivite(ActionEvent event) {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/firstPageFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle à partir du bouton
            Scene currentScene = btnRetourAfficherActivite.getScene();

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
    private void ajouterNouveauActivite(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterActiviteFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle à partir du bouton
            Scene currentScene = btnAjouterNouveauActivite.getScene();

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
            stage.setTitle("Ajouter Activité");
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
        }
    }

    @FXML
    private void chercherActivite(ActionEvent event) {
    String email = chercherActivite.getText();

    // Appelez la méthode getOneByEmail pour obtenir l'activité correspondante
    ServiceActivite sa = new ServiceActivite();
    Activite activite = sa.getOneByEmail(email);

    // Créez une liste contenant cette activité (ou vide si aucune correspondance n'est trouvée)
    List<Activite> data = new ArrayList<>();
    if (activite != null) {
        data.add(activite);
    }

    // Mettez à jour la TableView
    viewActivite.setItems(FXCollections.observableArrayList(data));
    objetA.setCellValueFactory(new PropertyValueFactory<Activite, String>("objetAct"));
    descriptionA.setCellValueFactory(new PropertyValueFactory<Activite, String>("descriptionAct"));
    distinataireA.setCellValueFactory(new PropertyValueFactory<Activite, String>("distAct"));
    EmailDisA.setCellValueFactory(new PropertyValueFactory<Activite, String>("emailDist"));
    speciesAct.setCellValueFactory(new PropertyValueFactory<Activite, String>("speciesRES"));
    etatAct.setCellValueFactory(new PropertyValueFactory<Activite, String>("etatAct"));

}

        
    }
    
