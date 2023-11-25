/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import static tn.edu.esprit.services.SmsSender.sendSms;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.UUID;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import tn.edu.esprit.tools.DataSource; // Assurez-vous d'importer correctement votre classe DataSource


/**
 * FXML Controller class
 *
 * @author aladi
 */
public class ForgetPassword1Controller implements Initializable {

    @FXML
    private JFXTextField mailField1;
    @FXML
    private JFXButton envoyerButton;
    @FXML
    private JFXButton annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
@FXML
private void envoyerAction(ActionEvent event) {
    // Récupérez le numéro de téléphone saisi par l'utilisateur
    String toPhoneNumber = mailField1.getText();

    // Vérifiez si le numéro de téléphone existe dans la base de données
    boolean phoneNumberExists = doesPhoneNumberExist(toPhoneNumber);

    if (phoneNumberExists) {
        toPhoneNumber = "+" + mailField1.getText();
        // Générez un code de réinitialisation (une chaîne aléatoire, un jeton unique, etc.)
        String codeDeReinitialisation = generateResetCode(); // Vous devez implémenter cette méthode

        // Enregistrez ce code dans votre base de données ou un autre endroit sécurisé pour une vérification ultérieure
        saveResetCodeInDatabase(toPhoneNumber, codeDeReinitialisation); // Vous devez implémenter cette méthode

        // Envoyez le code de réinitialisation par SMS au numéro spécifié
        sendSms(toPhoneNumber, codeDeReinitialisation); // Vous devez implémenter cette méthode

        // Affichez un message de succès à l'utilisateur
        showInfoMessage("Code de réinitialisation envoyé", "Veuillez consulter votre boîte de réception pour le code de réinitialisation.");

        // Passez à l'écran de réinitialisation du mot de passe (par exemple, en chargeant un nouvel FXML)
        loadPasswordResetScreen(toPhoneNumber);
    } else {
        // Le numéro de téléphone n'existe pas, affichez une alerte à l'utilisateur
        showErrorMessage("Numéro de téléphone introuvable", "Le numéro de téléphone saisi n'existe pas dans notre système. Veuillez vérifier votre numéro de téléphone.");
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////

/*public boolean doesEmailExist(String email) {
    try {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT COUNT(*) FROM users WHERE mail = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0; // Si le compte est supérieur à zéro, l'e-mail existe
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // En cas d'erreur ou si l'e-mail n'existe pas
}*/
/////////////////////////////////////////////////////////////////////////////////////////////
public boolean doesPhoneNumberExist(String phoneNumber) {
    try {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT COUNT(*) FROM users WHERE numeroTelephone = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, phoneNumber);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0; // Si le compte est supérieur à zéro, le numéro de téléphone existe
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // En cas d'erreur ou si le numéro de téléphone n'existe pas
}
///////////////////////////////////////////////////////////////////////////////

public void showInfoMessage(String title, String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

public void showErrorMessage(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}




//////////////////////////////////////////////////////////////////////////////////
/*private void sendResetCodeByEmail(String toEmail, String resetCode) {
   final String username = "alabm1@proton.me";
        final String password = "aqwzsxedc2000";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.protonmail.ch"); // Adresse du serveur SMTP du fournisseur
        props.put("mail.smtp.port", "587"); // Port SMTP du fournisseur

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Réinitialisation de mot de passe");
            message.setText("Votre code de réinitialisation : " + resetCode);

            Transport.send(message);

            System.out.println("E-mail envoyé avec succès.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'envoi de l'e-mail.");
        }
}
*/

public String generateResetCode() {
    // Générer un UUID
    UUID uuid = UUID.randomUUID();
    String uuidStr = uuid.toString();

    try {
        // Obtenir un hachage MD5 de l'UUID (32 caractères)
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(uuidStr.getBytes());
        byte[] byteData = md.digest();

        // Convertir le hachage MD5 en une chaîne hexadécimale (32 caractères)
        StringBuilder hexString = new StringBuilder();
        for (byte b : byteData) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        // Prendre les 6 premiers caractères de la chaîne hexadécimale
        String code = hexString.toString().substring(0, 6);

        return code;
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null;
    }
}


public void saveResetCodeInDatabase(String phoneNumber, String resetCode) {
        Connection conn = DataSource.getInstance().getConnection();
        if (conn != null) {
            String query = "INSERT INTO password_reset1 (numeroTelephone, reset_code) VALUES (?, ?)";
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, phoneNumber);
                statement.setString(2, resetCode);
                statement.executeUpdate();
            } catch (SQLException e) {
                showErrorMessage("Erreur base de données", "Une erreur s'est produite lors de l'enregistrement du code de réinitialisation.");
            }
        }
    }

 private void loadPasswordResetScreen(String phoneNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPassword2.fxml"));
            Parent root = loader.load();
            ForgetPassword2Controller controller = loader.getController();
            controller.setNumero(phoneNumber);

            Scene scene = new Scene(root);
            Stage stage = (Stage) envoyerButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            showErrorMessage("Erreur de chargement", "Une erreur s'est produite lors du chargement de la page de réinitialisation de mot de passe.");
        }
    }


    @FXML
private void annulerAction(ActionEvent event) {
    // Chargez l'interface de connexion (sign in)
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signin.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        // Obtenez la scène actuelle (la scène de l'interface de réinitialisation de mot de passe)
        Scene currentScene = annuler.getScene();
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
}