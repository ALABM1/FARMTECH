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
import tn.edu.esprit.entities.Materiel;
import tn.edu.esprit.entities.Reclamations;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.gui.UserSession;

/**
 *
 * @author megbl
 */
public class SendMailReclamation {
 public void envoyerEmailReclamation(Reclamations reclamation) throws MessagingException {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.office365.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("houssemmeguebli@outlook.com", "esprit@2023");
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("houssemmeguebli@outlook.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reclamation.getEmail()));
        message.setSubject("Nouvelle Réclamation - " + reclamation.getType());
        String contenuHTML = "<html><body>"
                + "<h2 style='color: #FF5733;'>Nouvelle Réclamation :</h2>"
                + "<p><strong>Type :</strong> " + reclamation.getType() + "</p>"
                + "<p><strong>Description :</strong> " + reclamation.getDescription() + "</p>"
                + "<p><strong>Email :</strong> " + reclamation.getEmail() + "</p>"
                + "<p><strong>Téléphone :</strong> " + reclamation.getTelephone() + "</p>"
                + "</body></html>";

        message.setContent(contenuHTML, "text/html");

        Transport.send(message);

        System.out.println("E-mail envoyé avec succès.");

    } catch (MessagingException e) {
        e.printStackTrace();
        System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        throw e; // Re-lancez l'exception pour la gérer au niveau supérieur si nécessaire
    }
}

    
}
