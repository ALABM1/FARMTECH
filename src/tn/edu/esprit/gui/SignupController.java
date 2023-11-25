/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import java.sql.Connection;
import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import static helper.AlertHelper.showAlert;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.entities.UserRole;
import tn.edu.esprit.services.ServiceUser;
import tn.edu.esprit.tools.DataSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.scene.Node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.edu.esprit.services.QRCodeGenerator;


/**
 * FXML Controller class
 *
 * @author aladi
 */
public class SignupController implements Initializable {
   
   

   
   
    @FXML
    private Button registerButton;
   
   
   
    @FXML
    private JFXButton annuler;
   
    @FXML
    private RadioButton agriculteurButton;
    @FXML
    private RadioButton veterinaireButton;
    @FXML
    private RadioButton ouvrierButton;
   
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
    private JFXTextField NomTextField1;
    @FXML
    private Label password;
    @FXML
    private ToggleGroup usertype;
    @FXML
    private ImageView MotDePasse;
    @FXML
    private Label password1;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXPasswordField passwordField1;
    @FXML
    private JFXTextField passwordidentique;
   
   
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
   

   @FXML
private void register(ActionEvent event) throws IOException {
    // Récupérez les informations de l'utilisateur depuis les champs de texte
    String nom = NomTextField1.getText();
    String prenom = partNameTextField.getText();
    String email = partLnvField.getText();
    String telephone = partPriceField.getText();
    String ville = villeField.getText();
    String sexe = sexeField.getText();
    String password = passwordField.getText();
    String confirmPassword = passwordField1.getText();
    UserRole role = null;

    if (agriculteurButton.isSelected()) {
        role = UserRole.AGRICULTEUR;
    } else if (ouvrierButton.isSelected()) {
        role = UserRole.OUVRIER;
    } else if (veterinaireButton.isSelected()) {
        role = UserRole.VETERINAIRE;
    }

    if (isValidInput(nom) && isValidInput(prenom) && isValidEmail(email) && isValidPhoneNumber(telephone) && role != null && !password.isEmpty() && !confirmPassword.isEmpty()) {
        if (password.equals(confirmPassword)) {
            try {
                // Utilisez SHA-256 pour hacher le mot de passe
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(password.getBytes());
                byte[] hashedPassword = md.digest();

                // Convertissez le tableau de bytes en une chaîne hexadécimale
                StringBuilder hexString = new StringBuilder();
                for (byte b : hashedPassword) {
                    hexString.append(String.format("%02x", b));
                }

                // Utilisez la chaîne hexadécimale comme mot de passe haché
                String hashedPasswordString = hexString.toString();

                // Vérifiez si l'e-mail est unique
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
                        ps.setString(6, hashedPasswordString); // Insérez le mot de passe haché
                        ps.setString(7, ville);
                        ps.setString(8, sexe);

                        int rowsAffected = ps.executeUpdate();
                        if (rowsAffected > 0) {
                            showAlert(Alert.AlertType.INFORMATION, "Inscription réussie", "Félicitations, votre inscription a été effectuée avec succès.");
                            resetFields();

                            // Générez le code QR
                           
                            String qrData =  email +" "+ password;
                            String qrImagePath = "C:/Users/megbl/Downloads/qrcode.png"; // Spécifiez un chemin absolu complet

                            QRCodeGenerator.generateQRCode(qrData, qrImagePath);

                             // Charger l'interface DisplayQR.fxml
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayQR.fxml"));
                            Parent root = loader.load();
                            DisplayQRController displayQRController = loader.getController();
                            displayQRController.displayQRImage(new Image(new File(qrImagePath).toURI().toString()));

                            // Créer une nouvelle scène et afficher l'interface DisplayQR.fxml
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
   
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", "Une erreur est survenue lors de l'inscription. Veuillez réessayer.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", "Une erreur est survenue lors de l'inscription. Veuillez réessayer.");
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", "Cet email est déjà utilisé.");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", "Une erreur est survenue lors de l'inscription. Veuillez réessayer.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", "Les mots de passe ne correspondent pas.");
        }
    } else {
        showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", "Veuillez remplir correctement tous les champs obligatoires.");
    }
     
   
}

private void handlePasswordChange() {
    String password1 = passwordField.getText();
    String password2 = passwordField1.getText();

    if (password1.equals(password2)) {
        passwordidentique.setText("Mot de passe identique");
        // You can enable the register button or call your register() method here
        // register();
    } else {
        passwordidentique.setText("Mot de passe différent");
        // You might want to disable the register button or show an error message
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
                    passwordField1.clear();

                    // Décochez également les boutons radio si nécessaire
                    agriculteurButton.setSelected(false);
                    ouvrierButton.setSelected(false);
                    veterinaireButton.setSelected(false);
            }

 @FXML
    private void annulerAction(ActionEvent event) {
        try {
            // Charger la scène signin.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signin.fxml"));
            Parent root = loader.load();
   
            // Créer une nouvelle scène
            Scene scene = new Scene(root);
   
            // Obtenir la scène actuelle (à partir du bouton "Annuler")
            Stage stage = (Stage) annuler.getScene().getWindow();
   
            // Définir la nouvelle scène
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Affichez l'erreur pour le débogage
        }
    }


    @FXML
    private void agriculteurButtonAction(ActionEvent event) {
    }

    @FXML
    private void ouvrierButtonAction(ActionEvent event) {
    }

    @FXML
    private void veterinaireButtonAction(ActionEvent event) {
    }
   

   
}