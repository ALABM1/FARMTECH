/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javax.mail.MessagingException;
import tn.edu.esprit.entities.Materiel;

import tn.edu.esprit.services.ExporterPDFAllMateriel;

import tn.edu.esprit.services.ServiceMateriel;

/**
 * FXML Controller class
 *
 * @author megbl
 */
public class TableAllMaterielController implements Initializable {

    @FXML
    private Button fxSuppMat;
    @FXML
    private TableView<Materiel> fxAllMateriel;
    @FXML
    private TableColumn<Materiel, String> fxNomParc;
    @FXML
    private TableColumn<Materiel, String> fxNomMat;
    @FXML
    private TableColumn<Materiel, Float> fxQuantMat;
    @FXML
    private TableColumn<Materiel, String> fxEtatMat;
    @FXML
    private TableColumn<Materiel, LocalDate> fxDateMat;

    @FXML
    private TextField fxChercheField;
    @FXML
    private Label fxmail;
    
    

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
     fxAfficherAll(new ActionEvent());
     editData();

    fxAllMateriel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Un élément a été sélectionné
            fxSuppMat.setVisible(true);
        } else {
            fxSuppMat.setVisible(false);
        }
    });
    }

    @FXML
private void fxSupp(ActionEvent event) {
    Materiel materielSelectionne = fxAllMateriel.getSelectionModel().getSelectedItem();

    if (materielSelectionne != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce matériel ?");

        ButtonType boutonOui = new ButtonType("Oui");
        ButtonType boutonNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(boutonOui, boutonNon);

        alert.showAndWait().ifPresent(reponse -> {
            if (reponse == boutonOui) {
                ServiceMateriel sm = new ServiceMateriel();
                sm.supprimerMateriel(materielSelectionne.getIdMat());

                // Mettez à jour la TableView après la suppression
                List<Materiel> materiels = sm.getAllMateriels(); // Mettez à jour la liste des matériels
                fxAllMateriel.getItems().setAll(materiels);
            }
        });
    } else {
        System.out.println("Aucun matériel sélectionné.");
    }
}


    @FXML
    private void fxChercher(ActionEvent event) {
    String nomCherche = fxChercheField.getText(); // Récupérer le texte du champ de recherche

    // Appeler la méthode getMaterielByNom avec le nom cherché
    ServiceMateriel sm = new ServiceMateriel();
    List<Materiel> materiels = sm.getMaterielByNom(nomCherche);

    if (!materiels.isEmpty()) {
        // Mettre à jour la TableView avec les détails des matériels trouvés
        fxAllMateriel.getItems().setAll(materiels);
    } else {
        // Afficher une alerte si aucun matériel n'est trouvé
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Aucun matériel trouvé");
        alert.setHeaderText(null);
        alert.setContentText("Aucun matériel trouvé avec ce nom.");
        alert.showAndWait();
    }
}


    
@FXML
private void fxAfficherAll(ActionEvent event) {
    ServiceMateriel smateriel = new ServiceMateriel();
    List<Materiel> materiels = smateriel.getAllMateriels();
    
    // Assurez-vous d'ajuster le type de TableView en conséquence
    TableView<Materiel> tableView = (TableView<Materiel>) fxAllMateriel;

    // Mettez à jour la TableView avec la liste de matériel
    tableView.setItems(FXCollections.observableArrayList(materiels));

    // Assurez-vous d'ajuster les TableColumn en conséquence
    TableColumn<Materiel, String> fxNomParcCol = (TableColumn<Materiel, String>) fxNomParc;
    TableColumn<Materiel, String> nomMatCol = (TableColumn<Materiel, String>) fxNomMat;
    TableColumn<Materiel, Float> quantMatCol = (TableColumn<Materiel, Float>) fxQuantMat;
    TableColumn<Materiel, String> etatMatCol = (TableColumn<Materiel, String>) fxEtatMat;
    TableColumn<Materiel, LocalDate> dateMatCol = (TableColumn<Materiel, LocalDate>) fxDateMat;

    // Assurez-vous que les CellValueFactory sont corrects
    fxNomParcCol.setCellValueFactory(new PropertyValueFactory<>("nomParc"));
    nomMatCol.setCellValueFactory(new PropertyValueFactory<>("nomMat"));
    quantMatCol.setCellValueFactory(new PropertyValueFactory<>("quantiteMat"));
    etatMatCol.setCellValueFactory(new PropertyValueFactory<>("etatMat"));
    dateMatCol.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
}

    
private void editData() {
    
    fxNomMat.setCellFactory(TextFieldTableCell.<Materiel>forTableColumn());
    fxNomMat.setOnEditCommit(event -> {
        Materiel materiel= event.getRowValue(); 
        materiel.setNomMat(event.getNewValue());
        System.out.println("Le nom de " +  materiel.getNomMat() + " a été mis à jour à " + event.getNewValue());
        ServiceMateriel smateriel = new ServiceMateriel();
        smateriel.modifierMateriel(materiel);
    });

    fxQuantMat.setCellFactory(TextFieldTableCell.<Materiel, Float>forTableColumn(new FloatStringConverter()));
    fxQuantMat.setOnEditCommit(event -> {
        Materiel materiel = event.getRowValue();
        float newValue = event.getNewValue();
        materiel.setQuantiteMat(newValue);
        ServiceMateriel smateriel = new ServiceMateriel();
        smateriel.modifierMateriel(materiel);
    });
    
    fxEtatMat.setCellFactory(TextFieldTableCell.<Materiel>forTableColumn());
    fxEtatMat.setOnEditCommit(event -> {
    String newValue = event.getNewValue();
    if (newValue.equals("On marche") || newValue.equals("On panne")) {
        Materiel materiel = event.getRowValue();
        Alert confirmation = new Alert(AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de la modification");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Êtes-vous sûr de vouloir changer l'état du matériel ?");
        
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            materiel.setEtatMat(newValue);
            ServiceMateriel smateriel = new ServiceMateriel();
            try {
                fxmail.setVisible(true);
                smateriel.verifierEtatModifier(materiel);
            } catch (MessagingException ex) {
                Logger.getLogger(TableAllMaterielController.class.getName()).log(Level.SEVERE, null, ex);
            }
            smateriel.modifierMateriel(materiel);
        }
    } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir soit 'On marche' ou 'On panne'.");
        alert.showAndWait();
        // Rafraîchir la table pour annuler la saisie incorrecte
        fxEtatMat.getTableView().refresh();
    }
});
    

}

    private void Fxgotoupdtae(ActionEvent event) {
         try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModifierMaterielFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Materiel selectedMateriel = new Materiel();
        selectedMateriel = fxAllMateriel.getSelectionModel().getSelectedItem();
        SelectedParcManager.setSelectedMateriel(selectedMateriel);
        if (selectedMateriel != null) {
          
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Modifier materiel ");
            stage.show();
        } else {
            System.out.println("Aucun materil sélectionné.");
        }
    } catch (IOException ex) {
        System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
    }
}

    @FXML
    private void GoToListeParc(ActionEvent event) {
             
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/GetAllFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Liste de materiels");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterMaterielFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotomenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/WelcomePage.fxml"));
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

    @FXML
    private void fxExportToPDF(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation d'exportation PDF");
    alert.setHeaderText("Voulez-vous exporter le PDF ?");
    alert.setContentText("Êtes-vous sûr de vouloir exporter le PDF ?");

    ButtonType buttonTypeOui = new ButtonType("Oui");
    ButtonType buttonTypeNon = new ButtonType("Non");

    alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);

    alert.showAndWait().ifPresent(response -> {
        if (response == buttonTypeOui) {
            ServiceMateriel sm = new ServiceMateriel();
            List<Materiel> materiel = sm.getAllMateriels();
            ExporterPDFAllMateriel pdfall = new ExporterPDFAllMateriel();
            pdfall.exportMaterielToPDF(materiel);
        }
    });
}

    }
    



    
    

