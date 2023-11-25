/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Categtra;
import tn.edu.esprit.services.Servicecategtra;

/**
 * FXML Controller class
 *
 * @author jouin
 */
public class TablecatFXMLController implements Initializable {
     @FXML
    private TableView<Categtra> transactionstable2;
    private List<Categtra> data;

   
    @FXML
    private TableColumn<Categtra, String> nomcatcolumn;
    @FXML
    private TableColumn<Categtra, String> descriptioncatcolumn;
    @FXML
    private TextField textcherchercat;
    Servicecategtra sc = new Servicecategtra();
    @FXML
    private Label catnotfound;
     @FXML
    private Label nbcat;
    @FXML
    private Label catnotfound1;
    @FXML
    private Button buttonajoutercat;
    private Categtra selectedCategtra;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button buttonsupprimercat;
    //@FXML
    //private Button buttonmodifiertra;
    @FXML
    private Button buttoncherchercat;
    @FXML
    private Button buttonaffichercat;
    @FXML
    private Button buttonvoircat;
    @FXML
    private Button buttonretourcat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Servicecategtra servicecategtra = new Servicecategtra();
        data = servicecategtra.getAll(); // Assurez-vous que votre ServiceParc retourne une List<Parc>

        transactionstable2.setItems(FXCollections.observableArrayList(data));
        nomcatcolumn.setCellValueFactory(new PropertyValueFactory<>("nom_cat_tra"));
        descriptioncatcolumn.setCellValueFactory(new PropertyValueFactory<>("descrip_cat_tra"));
        transactionstable2.setEditable(true);
        editData();
        nbcat.setText("vous avez "+ servicecategtra.nbligne() + " catégories de transaction");
        transactionstable2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            buttonajoutercat.setVisible(true);
        } else buttonajoutercat.setVisible(false);

        selectedCategtra = newSelection;
    });
    }   
    
    
    
    
    
     @FXML
    private void cherchercat(ActionEvent event) throws IOException {
        //String nomCherche = textcherchertra.getText(); // Récupérer le texte du champ de recherche
        catnotfound1.setText("");
        catnotfound.setText("");
        String textInput= textcherchercat.getText();
         String nom_cat_tra = textInput;
    // Appeler la méthode getOne avec le id cherché
    
        Categtra categtra = sc.getOne(nom_cat_tra);
       // catnotfound1.setText("catégorie disponible");
        if (categtra != null) {
        // Mettre à jour la TableView avec les détails du parc trouvé
            if(categtra.getId_cat_tra()!= 0){
           catnotfound1.setText("catégorie disponible");
           transactionstable2.getItems().setAll(categtra);
            } else {
          // System.out.println("Aucune categorie trouvée avec cet id.");
           //catnotfound1.setText("");
           catnotfound.setText("catégorie non disponible");}
        }else{
                    catnotfound.setVisible(true);
                    
        

    }    
    }
    
    
    
     @FXML
    private void ajoutercat(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("../gui/ajoutercatFXML.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
    
    
    
    
    
    
     @FXML
    private void affichercat(ActionEvent event) throws IOException {
        Servicecategtra servicecategtra = new Servicecategtra();
        data = servicecategtra.getAll(); // Assurez-vous que votre ServiceParc retourne une List<Parc>

        transactionstable2.setItems(FXCollections.observableArrayList(data));
        nomcatcolumn.setCellValueFactory(new PropertyValueFactory<>("nom_cat_tra"));
        descriptioncatcolumn.setCellValueFactory(new PropertyValueFactory<>("descrip_cat_tra"));
        
    }
    
    
    
    
    
     @FXML
    private void supprimercat(ActionEvent event) throws IOException {
        catnotfound.setText("");
        catnotfound1.setText("");
         nbcat.setText("");
    Servicecategtra sc = new Servicecategtra();
    Categtra  categSelectionne = transactionstable2.getSelectionModel().getSelectedItem();

    if (categSelectionne != null) {
        sc.supprimer(categSelectionne.getId_cat_tra()); // Supprimez l'élément de la base de données

        // Mettez à jour la TableView
        data.remove(categSelectionne);
        transactionstable2.getItems().setAll(data);
        catnotfound1.setText("supprimé");
        nbcat.setText("vous avez "+ sc.nbligne() + " catégories de transaction");
    } else {
        System.out.println("Vous devez sélectionner un élément avant de le supprimer.");
        catnotfound.setText("selectionner catégorie à supprimer");
    
    }
    
    }
    
    
    
    
    
    
     @FXML
    private void voircat(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../gui/meteoFXML.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("météo");
        stage.show();
    
    
    }
    
    
    
    
    
    
     @FXML
    private void retourcat(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../gui/tresorerieFXML.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("trésorerie");
        stage.show();
    
    }
    
    
    
    

    private void editData() {
    nomcatcolumn.setCellFactory(TextFieldTableCell.<Categtra>forTableColumn());
    nomcatcolumn.setOnEditCommit(event -> {
        Categtra categtra = event.getTableView().getItems().get(event.getTablePosition().getRow());
        categtra.setNom_cat_tra(event.getNewValue());
        System.out.println("Le nom de " + categtra.getNom_cat_tra() + " a été mis à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        sc.modifier(categtra);
    });
        descriptioncatcolumn.setCellFactory(TextFieldTableCell.<Categtra>forTableColumn());
        descriptioncatcolumn.setOnEditCommit(event -> {
        Categtra categtra = event.getTableView().getItems().get(event.getTablePosition().getRow());
        categtra.setDescrip_cat_tra(event.getNewValue());
        System.out.println("Le nom de " + categtra.getDescrip_cat_tra() + " a été mis à jour à " + event.getNewValue() + " à la ligne " + (event.getTablePosition().getRow() + 1));
        sc.modifier(categtra);
    });   
    }
}
