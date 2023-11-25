/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.gui.UserSession;

/**
 *
 * @author megbl
 */
public class SendMailTrasnc {
    public void envoyerEmailCaisseNegative() throws MessagingException {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true"); // Activation de l'authentification
    props.put("mail.smtp.starttls.enable", "true"); // Utilisation de TLS
    props.put("mail.smtp.host", "smtp.office365.com"); // Serveur SMTP d'Outlook
    props.put("mail.smtp.port", "587"); // Port SMTP

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("houssemmeguebli@outlook.com", "esprit@2023"); // Ajoutez votre mot de passe ici
        }
    });

    try {
        User connectedUser = UserSession.getConnectedUser();
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("houssemmeguebli@outlook.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(connectedUser.getMail())); // Remplacez par l'adresse du destinataire
        message.setSubject("Attention : Caisse devenue negative");

        String contenuHTML = "<html><body>"
                + "<h2 style='color: #FF5733;'>Avis Urgent :</h2>"
                + "<p style='color: #FF5733;'>La caisse est devenue negative. Des mesures doivent etre prises pour remedier a la situation.</p>"
                + "<br>"
                + "<h3 style='color:#0000FF;'>Actions Recommandées :</h3>"
                + "<ul>"
                + "<li>Effectuez un suivi des transactions recentes.</li>"
                + "<li>Verifiez les factures et les paiements en attente.</li>"
                + "<li>Contactez les clients pour les paiements en retard.</li>"
                + "<li>Envisagez des solutions pour augmenter les liquidites.</li>"
                + "</ul>"
                + "<br>"
                + "<p style='color:#bbae98;'>Merci de prendre des mesures immédiates pour resoudre ce probleme.<p>"
                + "<p style='color:#bbae98;'>INFINITYFARM, votre succes agricole commence avec nous.<p>"
                + "</body></html>";

        message.setContent(contenuHTML, "text/html");

        Transport.send(message);

        System.out.println("E-mail envoyé avec succès.");

    } catch (MessagingException e) {
        e.printStackTrace();
        System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
    }
}

    
}
