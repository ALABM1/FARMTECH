package tn.edu.esprit.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.edu.esprit.entities.Activite;
import tn.edu.esprit.services.ServiceActivite;
import tn.edu.esprit.services.ServiceRessource;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AjouterActiviteFXMLController implements Initializable {

    @FXML
    private TextField txtObjetA;
    @FXML
    private TextField txtDistA;
    @FXML
    private TextField txtDescriptionA;
    @FXML
    private TextField txtEmaildistA;
    @FXML
    private CheckBox checkActivite;
    @FXML
    private TextField txtSpecies;
    @FXML
    private Button btnRetourAjoutAct;
    @FXML
    private RadioButton etat1;
    @FXML
    private RadioButton etat2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

   @FXML
private void AjouterActivite(ActionEvent event) {
    String objet = txtObjetA.getText();
    String dist = txtDistA.getText();
    String description = txtDescriptionA.getText();
    String emailDist = txtEmaildistA.getText();
    String speciesRES = txtSpecies.getText();

    String etatAct = "";
    if (etat1.isSelected()) {
        etatAct = "en_attente";
    } else if (etat2.isSelected()) {
        etatAct = "termine";
    }

    String contenuEmail = "Détails de Message d'urgence :\n" +
            "Objet : " + objet + "\n" +
            "Description : " + description + "\n" +
            "Species : " + speciesRES + "\n" +
            "État : " + etatAct;

    // Vérification du champ species
    if (speciesRES.isEmpty()) {
        afficherAlerte("Erreur de saisie", "Le champ species ne peut pas être vide.");
    } else if (!validerEmail(emailDist)) {
        afficherAlerte("Erreur de saisie", "L'email n'a pas le format valide (exemple@example.com).");
    } else if (objet.isEmpty() || dist.isEmpty() || description.isEmpty()) {
        afficherAlerte("Erreur de saisie", "Tous les champs sont obligatoires.");
    } else {
        // Si la case à cocher est cochée, envoyez l'e-mail
        if (checkActivite.isSelected()) {
            contenuEmail = "Détails de Message d'urgence :\n" +
                    "Objet : " + objet + "\n" +
                    "Description : " + description + "\n" +
                    "Species : " + speciesRES + "\n" +
                    "État : " + etatAct;
            envoyerEmail(emailDist, "Email d'urgence", contenuEmail);
        }

        // Ajoutez l'activité
        Activite activite = new Activite(objet, description, dist, emailDist, speciesRES, etatAct);
        ServiceActivite serviceActivite = new ServiceActivite();
        serviceActivite.ajouter(activite);
        afficherConfirmation("Succès", "L'activité a été ajoutée avec succès.");
    }
}

    @FXML
    private void RetourAjoutAct(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherActiviteFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle à partir du bouton
            Scene currentScene = btnRetourAjoutAct.getScene();

            // Créez une transition de translation
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), root);
            translateTransition.setFromX(-currentScene.getWidth());
            translateTransition.setToX(0);

            // Créez une transition de fondu
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), root);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);

            // Exécutez les deux transitions en parallèle
            translateTransition.play();
            fadeTransition.play();

            // Changez de scène après la fin de la transition
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(scene);
            stage.setTitle("Afficher Activité");
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
        }
    }

    private boolean validerEmail(String email) {
    // Utilisation d'une expression régulière pour valider le format de l'email
    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
    return email.matches(regex);
}

    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    private void afficherConfirmation(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    private void envoyerEmail(String emailDestinataire, String sujet, String contenu) {
    // Remplacez ces valeurs par vos informations d'identification Outlook
    String emailExpediteur = "hassan.jlassi@esprit-tn.com";
    String motDePasse = "yassine123/";

    // Configuration des propriétés pour la session
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp-mail.outlook.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    // Créez une session avec vos informations d'identification
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        @Override
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(emailExpediteur, motDePasse);
        }
    });

    try {
        // Créez un message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailExpediteur));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinataire));
        message.setSubject(sujet);
        message.setText(contenu);

        // Envoyez le message
        Transport.send(message);

        // Affichez une confirmation
        afficherConfirmation("Succès", "E-mail envoyé avec succès.");
    } catch (Exception e) {
        // Affichez une alerte en cas d'erreur
        afficherAlerte("Erreur d'envoi d'e-mail", "Une erreur s'est produite lors de l'envoi de l'e-mail : " + e.getMessage());
    }
    }
    
    

}
