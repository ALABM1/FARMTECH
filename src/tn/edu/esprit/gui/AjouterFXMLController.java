/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
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
import javafx.stage.Stage;
import tn.edu.esprit.entities.Transaction;
import tn.edu.esprit.services.Servicecategtra;
import tn.edu.esprit.services.Servicetransaction;

/**
 * FXML Controller class
 *
 * @author jouin
 */
public class AjouterFXMLController implements Initializable {
     @FXML
    private Label tranotadd;
    @FXML
    private Button fxAjoutertra;
    @FXML
    private RadioButton fxRevenu;
    @FXML
    private RadioButton fxDepense;
    @FXML
    private DatePicker fxDatetra;
    @FXML
    private TextField fxMontant;
    @FXML
    private ChoiceBox<String> fxCategtra;
    Servicecategtra Servicecategtra = new Servicecategtra();
    List list = Servicecategtra.remplircombo();
    private String typetra;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fxCategtra.getItems().addAll(list);
     }
     @FXML
private void AjouterAction(ActionEvent event) throws IOException {
    if (fxMontant.getText().isEmpty() || fxCategtra.getSelectionModel().getSelectedItem() == null || fxDatetra.getValue() == null) {
        tranotadd.setText("Veuillez remplir tous les champs");
        return;
    }

    boolean isSelected = fxDepense.isSelected();
    String typetrans = isSelected ? "Dépense" : "Revenu";

    // Vérifiez si le montant est positif
    try {
        float montantValue = Float.parseFloat(fxMontant.getText());
        if (montantValue <= 0) {
            tranotadd.setText("Le montant doit être positif");
            return;
        }
    } catch (NumberFormatException e) {
        tranotadd.setText("Le montant doit être un nombre valide");
        return;
    }

    Servicetransaction sp = new Servicetransaction();
    int Montant = (int) Float.parseFloat(fxMontant.getText());
    Transaction trans = new Transaction();
    Servicetransaction Servicetransaction = new Servicetransaction();

    String selectedText = fxCategtra.getSelectionModel().getSelectedItem();
    LocalDate localDate = fxDatetra.getValue();
    trans.setCateg_tra(selectedText);
    trans.setType_tra(typetrans);
    Date date = Date.valueOf(localDate);
    trans.setDate_tra((date));
    trans.setMontant(Montant);
    tranotadd.setText("Transaction ajoutée avec succès");
    Servicetransaction.ajouter(trans);

    root = FXMLLoader.load(getClass().getResource("../gui/tabletransFXML.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
 @FXML
    private void retourAction(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("../gui/tabletransFXML.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
    
}