/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;


import java.net.URL;
import java.sql.Date;

import java.util.List;
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
import tn.edu.esprit.entities.Parc;
import tn.edu.esprit.services.ExporterPDFMateriel;
import tn.edu.esprit.services.ServiceMateriel;

/**
 * FXML Controller class
 *
 * @author megbl
 */
public class TableViewMaterielController implements Initializable {
GetAllFXMLController tableparc;
    @FXML
    private TableView<Materiel> fxTableMateriel;
    @FXML
    private TableColumn<Materiel, String> fxNom;
    @FXML
    private TableColumn<Materiel, String> fxNomMateriel;
    @FXML
    private TableColumn<Materiel, Float> fxQunatite;
    @FXML
    private TableColumn<Materiel, String> fxEtat;
    @FXML
    private TableColumn<Materiel, Date> fxDate;
        private List<Materiel>  listeMateriel;
       ServiceMateriel smateriel = new ServiceMateriel();

    private Parc selectedParc ;
    @FXML
    private TextField fxfind;
    @FXML
    private Button fxdelete;
    @FXML
    private Label fxnomdeparc;
    @FXML
    private Label fxmail;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editData();

        fxTableMateriel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Un élément a été sélectionné
            fxdelete.setVisible(true);
        } else {
            fxdelete.setVisible(false);
        }
    });
    }
      

 public void initData(Parc selectedParc) {
    int idParc = selectedParc.getIdParc();
    ServiceMateriel sm = new ServiceMateriel();
    List<Materiel> materiels = sm.getAllMaterielsForParc(idParc);
    
    // Mettez à jour la TableView
    fxTableMateriel.setItems(FXCollections.observableArrayList(materiels));
    

   
    // Set cell value factories
    fxNom.setCellValueFactory(new PropertyValueFactory<>("nomParc"));
    fxNomMateriel.setCellValueFactory(new PropertyValueFactory<>("nomMat"));
    fxQunatite.setCellValueFactory(new PropertyValueFactory<>("quantiteMat"));
    fxEtat.setCellValueFactory(new PropertyValueFactory<>("etatMat"));
    fxDate.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
    String nomParc = selectedParc.getNomParc();
    fxnomdeparc.setText("' "+nomParc+" '");
}
 
   
   
private void editData() {
    // Éditer le nom du parc
    fxNomMateriel.setCellFactory(TextFieldTableCell.<Materiel>forTableColumn());
    fxNomMateriel.setOnEditCommit(event -> {
        Materiel materiel= event.getRowValue(); 
        materiel.setNomMat(event.getNewValue());
        System.out.println("Le nom de " +  materiel.getNomMat() + " a été mis à jour à " + event.getNewValue());
        ServiceMateriel smateriel = new ServiceMateriel();
        smateriel.modifierMateriel(materiel);
    });

      
   fxEtat.setCellFactory(TextFieldTableCell.<Materiel>forTableColumn());
   fxEtat.setOnEditCommit(event -> {
    String newValue = event.getNewValue();
    if (newValue.equals("On marche") || newValue.equals("On panne")) {
        Materiel materiel = event.getRowValue();
        materiel.setEtatMat(newValue);
        ServiceMateriel smateriel = new ServiceMateriel();
        try {        fxmail.setVisible(true);

            smateriel.verifierEtatModifier(materiel);
        } catch (MessagingException ex) {
            Logger.getLogger(TableViewMaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
        smateriel.modifierMateriel(materiel);
    } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir soit 'On marche' ou 'On panne'.");
        alert.showAndWait();
    }

    });

    // Éditer la superficie du parc
        fxQunatite.setCellFactory(TextFieldTableCell.<Materiel, Float>forTableColumn(new FloatStringConverter()));
        fxQunatite.setOnEditCommit(event -> {
        Materiel materiel = event.getRowValue();
        float newValue = event.getNewValue();
        materiel.setQuantiteMat(newValue);
        ServiceMateriel smateriel = new ServiceMateriel();
        smateriel.modifierMateriel(materiel);
    });
  
}

@FXML
private void fxSupprimer(ActionEvent event) {
    ServiceMateriel sm = new ServiceMateriel();
    Materiel MaterielSelectionne = fxTableMateriel.getSelectionModel().getSelectedItem();

    if (MaterielSelectionne != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce matériel ?");

        ButtonType boutonOui = new ButtonType("Oui");
        ButtonType boutonNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(boutonOui, boutonNon);

        alert.showAndWait().ifPresent(reponse -> {
            if (reponse == boutonOui) {
                sm.supprimerMateriel(MaterielSelectionne.getIdMat());

                // Rafraîchir la TableView
                List<Materiel> nouvellesDonnees = sm.getAllMaterielsForParc(MaterielSelectionne.getIdParc());
                fxTableMateriel.setItems(FXCollections.observableArrayList(nouvellesDonnees));
            }
        });
    } else {
        System.out.println("Vous devez sélectionner un élément avant de le supprimer.");
    }

    fxRefrech(new ActionEvent());
}




    @FXML
    private void fxbacktoTableViewParc(ActionEvent event) throws IOException {
        
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
    private void fxRefrech(ActionEvent event) {
        
     // Récupérez les nouvelles données depuis la base de données ou le modèle
            selectedParc = SelectedParcManager.getSelectedParc();

     ServiceMateriel sm = new ServiceMateriel();
    List<Materiel> nouvellesDonnees = sm.getAllMaterielsForParc(selectedParc.getIdParc()); // Par exemple, récupérez les nouveaux matériels

    // Mettez à jour la TableView avec les nouvelles données
    fxTableMateriel.setItems(FXCollections.observableArrayList(nouvellesDonnees));
    }

    @FXML
    private void fxchercherMat(ActionEvent event) {
        
        String nomCherche = fxfind.getText(); // Récupérer le texte du champ de recherche

    // Appeler la méthode getMaterielByNom avec le nom cherché
    ServiceMateriel sm = new ServiceMateriel();
    List<Materiel> materiels = sm.getMaterielByNom(nomCherche);

    if (!materiels.isEmpty()) {
        // Mettre à jour la TableView avec les détails des matériels trouvés
        fxTableMateriel.getItems().setAll(materiels);
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
    private void fxexportoPDF(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation d'exportation PDF");
    alert.setHeaderText("Voulez-vous exporter le PDF ?");
    alert.setContentText("Êtes-vous sûr de vouloir exporter le PDF ?");

    ButtonType buttonTypeOui = new ButtonType("Oui");
    ButtonType buttonTypeNon = new ButtonType("Non");

    alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);

    alert.showAndWait().ifPresent(response -> {
        if (response == buttonTypeOui) {
            selectedParc = SelectedParcManager.getSelectedParc();
            int idParc = selectedParc.getIdParc();
            ServiceMateriel sm = new ServiceMateriel();
            List<Materiel> matariel = sm.getAllMaterielsForParc(idParc);
            ExporterPDFMateriel pdfmat = new ExporterPDFMateriel();
            pdfmat.exportMaterielsToPDF(matariel);
        }
    });

    }
}
    



