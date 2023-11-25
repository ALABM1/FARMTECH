/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import static helper.AlertHelper.showAlert;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.entities.UserRole;
import tn.edu.esprit.services.ServiceUser;
import tn.edu.esprit.tools.DataSource;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * FXML Controller class
 *
 * @author aladi
 */
public class Ajout_userController implements Initializable {

    @FXML
    private JFXRadioButton agriculteurButton;
    @FXML
    private JFXRadioButton veterinaireButton;
    @FXML
    private JFXRadioButton ouvrierButton;
    
    @FXML
    private JFXTextField partNameTextField;
    @FXML
    private JFXTextField partLnvField;
    @FXML
    private JFXTextField partPriceField;
    @FXML
    private JFXTextField villeField;
    @FXML
    private JFXTextField sexeField;
    @FXML
    private JFXTextField passwordField;
    @FXML
    private JFXTextField partCompanyNameField;
    @FXML
    private Label partCompanyNameLabel;
    @FXML
    private JFXTextField NomTextField1;
    @FXML
    private JFXButton save;
    @FXML
    private Label password;
    @FXML
    private JFXButton close;
    @FXML
    private ToggleGroup usertype;
    
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void agriculteurButtonAction(ActionEvent event) {
    }

    @FXML
    private void veterinaireButtonAction(ActionEvent event) {
    }
    
    

 @FXML
private void SaveButtonAction(ActionEvent event) {
    String nom = NomTextField1.getText();
    String prenom = partNameTextField.getText();
    String email = partLnvField.getText();
    String telephone = partPriceField.getText();
    String ville = villeField.getText();
    String sexe = sexeField.getText();
    String password = passwordField.getText();
    UserRole role = null;

    if (agriculteurButton.isSelected()) {
        role = UserRole.AGRICULTEUR;
    } else if (ouvrierButton.isSelected()) {
        role = UserRole.OUVRIER;
    } else if (veterinaireButton.isSelected()) {
        role = UserRole.VETERINAIRE;
    }

    if (isValidInput(nom) && isValidInput(prenom) && isValidEmail(email) && isValidPhoneNumber(telephone) && role != null && !password.isEmpty()) {
        // Utilisez la méthode de UserService pour vérifier l'unicité de l'email
        ServiceUser userService = new ServiceUser();
        if (userService.isEmailUnique(email)) {
            Connection con = DataSource.getInstance().getConnection();

            try {
                String query = "INSERT INTO users (nom, prenom, mail, numeroTelephone, role, motDePasse, ville, sexe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, nom);
                ps.setString(2, prenom);
                ps.setString(3, email);
                ps.setString(4, telephone);
                ps.setString(5, role.toString());
                ps.setString(6, password);
                ps.setString(7, ville);
                ps.setString(8, sexe);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    Alert successAlert = new Alert(AlertType.INFORMATION);
                    successAlert.setTitle("Inscription réussie");
                    successAlert.setHeaderText("Félicitations, votre inscription a été effectuée avec succès.");
                    successAlert.showAndWait();
                    resetFields();
                } else {
                    showAlert(AlertType.ERROR, "Erreur d'inscription", "Une erreur est survenue lors de l'inscription. Veuillez réessayer.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(AlertType.ERROR, "Erreur d'inscription", "Une erreur est survenue lors de l'inscription. Veuillez réessayer.");
            }
        } else {
            showAlert(AlertType.ERROR, "Erreur d'inscription", "Cet email est déjà utilisé.");
        }
    } else {
        showAlert(AlertType.ERROR, "Erreur d'inscription", "Veuillez remplir correctement tous les champs obligatoires.");
    }
}

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\d+$");
    }

    private boolean isValidEmail(String email) {
    return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$");
}


    private boolean isValidInput(String input) {
        return !input.isEmpty() && !input.matches(".*\\d+.*");
    }
    
    private void resetFields() {
        // Réinitialisez les champs de texte après une inscription réussie
        NomTextField1.clear();
        partNameTextField.clear();
        partLnvField.clear();
        partPriceField.clear();
        villeField.clear();
        sexeField.clear();
        passwordField.clear();

        // Décochez également les boutons radio si nécessaire
        agriculteurButton.setSelected(false);
        ouvrierButton.setSelected(false);
        veterinaireButton.setSelected(false);
    }



    

    @FXML
    private void ouvrierButtonAction(ActionEvent event) {
        
        
        
        
        
    }
    
  @FXML
private void closeButtonAction(ActionEvent event) {
    try {
        // Charger la scène ala.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signin.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Obtenir la scène actuelle (à partir du bouton "Close")
        Stage stage = (Stage) close.getScene().getWindow();

        // Définir la nouvelle scène
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace(); // Affichez l'erreur pour le débogage
    }
}



    
}
