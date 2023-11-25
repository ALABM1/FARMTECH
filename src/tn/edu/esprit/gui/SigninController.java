/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import helper.AlertHelper;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;



import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import tn.edu.esprit.entities.UserRole;
import tn.edu.esprit.services.ServiceUser;
import tn.edu.esprit.tools.DataSource;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import sun.net.www.http.HttpClient;


import javafx.fxml.FXML;


import java.io.IOException;
import java.net.URI;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

import com.google.zxing.LuminanceSource;
import static helper.AlertHelper.showAlert;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.edu.esprit.services.QRCodeDecoder;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.edu.esprit.entities.User;




/**
 * FXML Controller class
 *
 * @author aladi
 */
public class SigninController implements Initializable {
    @FXML
    private com.gluonhq.charm.glisten.control.TextField emailField;
   
    @FXML
    private PasswordField passwordField;
   
    @FXML
    private Button loginButton;
   
    @FXML
    private Label fxnotfound;
   
    @FXML
    private Button signup;
   
    @FXML
    private Hyperlink forgetPassword;
   
    @FXML
    private Label fxnotfound1;
    @FXML
    private Button QRCode;
   
   
   

    /**
     * Initializes the controller class.
     */
   
    public void initialize(URL url, ResourceBundle rb) {
       
    }
 

// ...


@FXML
private void loginAction() {
    String email = emailField.getText();
    String password = passwordField.getText();

    DataSource dataSource = DataSource.getInstance();
    Connection conn = dataSource.getConnection();

    if (conn != null) {
        // Préparez la requête SQL avec des paramètres pour éviter les injections SQL
        String query = "SELECT * FROM users WHERE mail = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Récupérez le mot de passe haché depuis la base de données
                String hashedPasswordFromDB = resultSet.getString("motDePasse");

                // Utilisez la bibliothèque MessageDigest pour hacher le mot de passe entré
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(password.getBytes());
                byte[] hashedPassword = md.digest();
                StringBuilder hexString = new StringBuilder();
                for (byte b : hashedPassword) {
                    hexString.append(String.format("%02x", b));
                }
                String hashedPasswordString = hexString.toString();

                if (hashedPasswordString.equals(hashedPasswordFromDB)) {
                    // L'authentification a réussi
                    if (hashedPasswordString.equals(hashedPasswordFromDB)) {
                     UserRole userRole = UserRole.valueOf(resultSet.getString("role"));
                     User connectedUser = new User(email, userRole); // Créez un objet User avec l'email et le rôle
                     UserSession.setConnectedUser(connectedUser);

    // Redirigez l'utilisateur selon son rôle...
}


                    // Vérifiez le rôle de l'utilisateur
                    UserRole userRole = UserRole.valueOf(resultSet.getString("role"));
                    

                    if (userRole == UserRole.ADMIN) {
                        
                        // Redirigez l'administrateur vers l'interface d'administrateur
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_interface.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        // Fermez la fenêtre de connexion actuelle
                        Stage loginStage = (Stage) loginButton.getScene().getWindow();
                        loginStage.close();


                    } else {
                        if (userRole == UserRole.AGRICULTEUR) {
                        // Redirigez l'administrateur vers l'interface d'administrateur
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Agriculteur1.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        // Fermez la fenêtre de connexion actuelle
                        Stage loginStage = (Stage) loginButton.getScene().getWindow();
                        loginStage.close();
                    }
                        else{
                            if (userRole == UserRole.VETERINAIRE) {
                        // Redirigez l'administrateur vers l'interface d'administrateur
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Vet1.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        // Fermez la fenêtre de connexion actuelle
                        Stage loginStage = (Stage) loginButton.getScene().getWindow();
                        loginStage.close();
                    }
                            else{
                                     if (userRole == UserRole.OUVRIER) {
                        // Redirigez l'administrateur vers l'interface d'administrateur
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOuvrierActiviter.fxml"));
                        
                        Parent root = loader.load();
                   
                     

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        // Fermez la fenêtre de connexion actuelle
                        Stage loginStage = (Stage) loginButton.getScene().getWindow();
                        loginStage.close();
                                   
                                    
                                }
                            
                            }
                            
                        }
                    }
                }else {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Erreur d'authentification", "Email ou mot de passe incorrect.");
                }
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Erreur d'authentification", "Email ou mot de passe incorrect.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Erreur d'authentification", "Erreur de base de données.");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Erreur d'authentification", "Erreur lors du hachage du mot de passe.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

 


// Méthode pour ouvrir l'interface Ajout_user
private void openAjoutUserWindow() {
    try {
        // Charger la vue FXML de l'interface Ajout_user
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_interface.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Créer un nouveau stage (fenêtre)
        Stage stage = new Stage();
        stage.setTitle("Ajout d'utilisateur"); // Titre de la nouvelle fenêtre
        stage.setScene(scene);

        // Afficher la nouvelle fenêtre
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}






    @FXML
private void goToSignupAction(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) signup.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace(); // Gérez l'exception correctement en cas d'erreur de chargement de la scène
    }
}

   @FXML
private void forgotPasswordAction(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPassword1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) forgetPassword.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

 @FXML
private void importqrcodeActtion(ActionEvent event) {
    FileChooser fileChooser = new FileChooser(); // Initialisez l'objet FileChooser

    // Définissez les filtres de fichiers si nécessaire
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

    // Affichez la boîte de dialogue de sélection de fichiers
    File file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());

    if (file != null) {
        try {
            BufferedImage qrCodeImage = ImageIO.read(file);

            QRCodeDecoder qrCodeDecoder = new QRCodeDecoder();
            String qrCodeData = qrCodeDecoder.readQRCode(qrCodeImage);
            insererContenuQR(qrCodeData);

            if (qrCodeData != null) {
                // Utilisez qrCodeData comme vous le souhaitez, par exemple, pour le traitement du mot de passe et de l'e-mail.
                System.out.println("Données du code QR : " + qrCodeData);
            } else {
                System.out.println("Aucun code QR valide trouvé dans l'image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public void insererContenuQR(String qrData) {
    // Diviser la chaîne en email et mot de passe en utilisant l'espace comme séparateur
    String[] parties = qrData.split(" ");
   
    if (parties.length == 2) {
        // La première partie est l'email, la deuxième partie est le mot de passe
        String email = parties[0];
        String motDePasse = parties[1];

        // Insérer l'email et le mot de passe dans les champs correspondants
        emailField.setText(email);
        passwordField.setText(motDePasse);
    } else {
        // Gérer le cas où la chaîne ne peut pas être divisée en deux parties
        System.out.println("QR Code non valide : " + qrData);
    }
}







   
}