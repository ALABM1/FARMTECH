/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.stage.Stage;
import tn.edu.esprit.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author aladi
 */
public class ForgetPassword3Controller implements Initializable {

    
    @FXML
    private JFXPasswordField passwordField1;
    @FXML
    private JFXButton enregistrerButton;
    @FXML
    private JFXButton annulerButton;
    @FXML
    private JFXPasswordField passwordField;
   
    
   
    @FXML
    private JFXTextField phoneField;
    @FXML
    private JFXTextField codeField;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
private void enregistrerAction(ActionEvent event) throws NoSuchAlgorithmException {
    String nouveauMotDePasse = passwordField1.getText();
    String confirmationMotDePasse = passwordField.getText();
    String numeroTelephone = phoneField.getText();

    if (nouveauMotDePasse.isEmpty() || confirmationMotDePasse.isEmpty() || numeroTelephone.isEmpty()) {
        showErrorMessage("Champs vides", "Veuillez remplir tous les champs.");
    } else if (!nouveauMotDePasse.equals(confirmationMotDePasse)) {
        showErrorMessage("Mots de passe ne correspondent pas", "Les mots de passe ne correspondent pas. Veuillez réessayer.");
    } else if (!numeroTelephone.matches("\\d+")) {
        showErrorMessage("Numéro de téléphone invalide", "Le numéro de téléphone ne doit contenir que des chiffres.");
    } else {
        boolean miseAJourReussie = updatePasswordInDatabase(numeroTelephone, nouveauMotDePasse);

        if (miseAJourReussie) {
            showSuccessMessage("Mise à jour réussie", "Mot de passe mis à jour avec succès.");
            redirectToSignIn();
        } else {
            showErrorMessage("Échec de la mise à jour", "Échec de la mise à jour du mot de passe. Veuillez réessayer.");
        }
    }
}

    private void showErrorMessage(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
    }

    private void showSuccessMessage(String title, String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
    }


    


    @FXML
    private void annulerAction(ActionEvent event) {
        redirectToSignIn();
    }
    
    
 private boolean updatePasswordInDatabase(String numeroTelephone, String newPassword) throws NoSuchAlgorithmException {
    Connection conn = null;
    try {
        conn = DataSource.getInstance().getConnection();

        if (conn != null) {
            String hashedPassword = hashPassword(newPassword);
            // Vérifiez d'abord que l'utilisateur avec ce numéro de téléphone existe
            String checkUserQuery = "SELECT numeroTelephone FROM users WHERE numeroTelephone = ?";
            PreparedStatement checkUserStatement = conn.prepareStatement(checkUserQuery);
            checkUserStatement.setString(1, numeroTelephone);
            ResultSet userResultSet = checkUserStatement.executeQuery();

            if (userResultSet.next()) {
                // L'utilisateur existe, mettez à jour le mot de passe
                String updateQuery = "UPDATE users SET MotDePasse = ? WHERE numeroTelephone = ?";
                PreparedStatement statement = conn.prepareStatement(updateQuery);
                statement.setString(1, hashedPassword);
                statement.setString(2, numeroTelephone);

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    // La mise à jour du mot de passe a réussi
                    return true;
                }
            } else {
                // L'utilisateur avec ce numéro de téléphone n'existe pas
                // Vous pouvez afficher un message d'erreur ou gérer la situation comme vous le souhaitez
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // La mise à jour du mot de passe a échoué
    return false;
}

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private void redirectToSignIn() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Obtenez la scène actuelle (la scène de la page "ForgetPassword3")
        Stage currentStage = (Stage) enregistrerButton.getScene().getWindow();

        // Changez la scène pour passer à l'interface "signin"
        currentStage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
        // Gérez l'exception correctement en cas d'erreur de chargement de la scène
    }
}
    ///////////////////////////////////////////////////
    private String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(password.getBytes());
    byte[] hashedPassword = md.digest();

    // Convertissez le tableau de bytes en une chaîne hexadécimale
    StringBuilder hexString = new StringBuilder();
    for (byte b : hashedPassword) {
        hexString.append(String.format("%02x", b));
    }

    return hexString.toString();
}


    
}
