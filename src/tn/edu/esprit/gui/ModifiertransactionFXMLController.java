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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Transaction;
import tn.edu.esprit.services.Servicecategtra;
import tn.edu.esprit.services.Servicetransaction;

/**
 * FXML Controller class
 *
 * @author jouin
 */
public class ModifiertransactionFXMLController implements Initializable {

     @FXML
    private Label modnotfound;
     @FXML
    private Button fxAjoutertra1;
    @FXML
    private RadioButton fxRevenu1;
    @FXML
    private RadioButton fxDepense1;
    @FXML
    private DatePicker fxDatetra1;
    @FXML
    private TextField fxMontant1;
    @FXML
    private ChoiceBox<String> fxCategtra1;
    Servicecategtra Servicecategtra = new Servicecategtra();
    List list = Servicecategtra.remplircombo();
    private String typetra1;
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         fxCategtra1.getItems().addAll(list);
    }    
     @FXML
    private void ModifierAction(ActionEvent event) throws IOException {
     try {
        Transaction selectedtransaction= Selectedtransmanager.getSelectedTransaction();
        String selectedText = fxCategtra1.getSelectionModel().getSelectedItem();
        Integer Montant= Integer.parseInt(fxMontant1.getText());
        String typetrans = "";
        if(fxDepense1.isSelected()){typetrans= "Dépense" ;
        }else{typetrans= "Revenu";  }
        LocalDate localDate = fxDatetra1.getValue();
        Date date = Date.valueOf(localDate);
        selectedtransaction.setCateg_tra(selectedText);
        selectedtransaction.setType_tra(typetrans);
        selectedtransaction.setDate_tra((date));
        selectedtransaction.setMontant(Montant);
        Servicetransaction servicetransaction = new Servicetransaction();
        servicetransaction.modifier(selectedtransaction);
        root = FXMLLoader.load(getClass().getResource("../gui/tabletransFXML.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Transactions");
        stage.show();
        // Si la modification réussit, vous pouvez effectuer d'autres actions ici.
    } catch (NumberFormatException e) {
        System.out.println("Erreur de format.");
        modnotfound.setText("remplir tous les champs");

    }
        
        
        
    }
    
    @FXML
    private void retourAction(ActionEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("../gui/tabletransFXML.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("trésorerie");
            stage.show();
    
}
}
