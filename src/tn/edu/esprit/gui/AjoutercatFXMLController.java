/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Categtra;
import tn.edu.esprit.services.Servicecategtra;

/**
 * FXML Controller class
 *
 * @author jouin
 */
public class AjoutercatFXMLController implements Initializable {
    
    @FXML
    private Label catnotadd;
    @FXML
    private Button fxAjoutercat;
    @FXML
    private TextField fxdescripcat;
    @FXML
    private TextField fxNomcat;
    Servicecategtra sc = new Servicecategtra();
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void AjouterAction1(ActionEvent event) throws IOException {
        
        catnotadd.setText("");
        //TextField fxNomcat = (TextField) scene.lookup("#fxNomcat");
        //TextField fxdescripcat = (TextField) scene.lookup("#fxdescripcat");
         if (fxNomcat.getText().isEmpty() || fxdescripcat.getText().isEmpty()) {
        catnotadd.setText("saisir tous les champs");
        return;
        }
        Servicecategtra sc = new Servicecategtra();
        String nomText = fxNomcat.getText();
        String descripText = fxdescripcat.getText();
        Categtra categ=new Categtra();
        Servicecategtra Servicecategtra = new Servicecategtra();
        categ.setNom_cat_tra(nomText);
        categ.setDescrip_cat_tra(descripText);
        Servicecategtra.ajouter(categ);
        
        root = FXMLLoader.load(getClass().getResource("../gui/tablecatFXML.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void retourAction1(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("../gui/tablecatFXML.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
    
}
