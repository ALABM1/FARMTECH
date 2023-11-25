/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import tn.edu.esprit.entities.Materiel;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.gui.UserSession;
/**
 *
 * @author megbl
 */
public class SendMail {
    public void envoyerEmailMaterielEnPanne(Materiel materiel) throws MessagingException {
    if (materiel == null ) {
        throw new IllegalArgumentException("Le matériel spécifié est invalide.");
    }

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
        message.setSubject("Matériel en panne - Rappel");
        String contenuHTML = "<html><body>"
                + "<h2 style='color: #FF5733;'>Details du materiel en panne :</h2>"
                + "<p><strong>Nom :</strong> " + materiel.getNomMat() + "</p>"
                + "<p><strong>Etat :</strong> " + materiel.getEtatMat() + "</p>"
                + "<p style='color: #FF5733;'>Ce materiel actuellement est on panne  et necessite une reparation.</p>"
                + "<p style='color: #FF5733;'>Merci de prendre les mesures necessaires pour remedier a la situation. </p>"
                + "<br>"
                + "<h3 style='color:#0000FF;'>Conseils :</h3>"
                + "<ul>"
                + "<li>Verifiez avec vos techniciens immediatement.</li>"
                + "<li>Consultez le manuel d'utilisation.</li>"
                + "<li>Si necessaire, contactez le support technique.</li>"
                + "</ul>"
                +"<br>"
                +"<br>"
                + "<p style='color:#bbae98;'>INFINITYFARM vos succes agricole commence avec nous.<p>"
                + "</body></html>";

        message.setContent(contenuHTML, "text/html");

        Transport.send(message);

        System.out.println("E-mail envoyé avec succès.");

    } catch (MessagingException e) {
        e.printStackTrace();
        System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
    }
}
    
    /////////////////////////////////////////////////////////////////////////
    
    public void envoyerMailConfirmation(Materiel materiel) throws MessagingException {
    if (materiel == null) {
        throw new IllegalArgumentException("Le matériel spécifié est invalide.");
    }

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
        User connectedUser = UserSession.getConnectedUser();
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("houssemmeguebli@outlook.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(connectedUser.getMail()));
        message.setSubject("Confirmation de l'état du matériel");

        // Contenu de l'e-mail
                String contenuHTML = "<html><body>"
                + "<h2 style='color: #008000;'>Confirmation de l'etat du materiel :</h2>"
                + "<p>Cher utilisateur,</p>"
                + "<p>Nous tenons a vous informer que le materiel <strong>" + materiel.getNomMat() + "</strong> fonctionne correctement. "
                + "Il n y a aucun probleme a signaler.</p>"
                + "<p>Merci pour votre confiance.</p>"
                + "<p>Cordialement,<br/>"
                + "Votre equipe de support technique</p>"
                + "<p style='color:#bbae98;'>INFINITYFARM vos succes agricoles commencent avec nous.</p>"
                + "</body></html>";

        message.setContent(contenuHTML, "text/html");

        Transport.send(message);

        System.out.println("E-mail de confirmation envoyé avec succès.");

    } catch (MessagingException e) {
        e.printStackTrace();
        System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
    }
}


}






    

