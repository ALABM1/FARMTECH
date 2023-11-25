/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.mail.MessagingException;
import tn.edu.esprit.entities.Materiel;
import tn.edu.esprit.tools.DataSource;


/**
 *
 * @author megbl
 */
public class ServiceMateriel {
    
    private Connection cnx;

    public ServiceMateriel() {
        this.cnx = DataSource.getInstance().getConnection();
    }
 public void ajouterMateriel(Materiel materiel) {
        try {
            String req = "INSERT INTO `materiel`(`nomParc`, `nomMat`, `etatMat`, `QuantiteMat`, `dateAjout` ,`idParc`) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = cnx.prepareStatement(req)) {
                ps.setString(1, materiel.getNomParc());
                ps.setString(2, materiel.getNomMat());
                ps.setString(3, materiel.getEtatMat());
                ps.setFloat(4, materiel.getQuantiteMat());
                ps.setDate(5, Date.valueOf(materiel.getDateAjout()));
                ps.setInt(6, materiel.getIdParc());


                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 public void supprimerMateriel(int idMat) {
    try {
        String req = "DELETE FROM `materiel` WHERE idMat = ?";

        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, idMat);
            ps.executeUpdate();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



public List<Materiel> getAllMaterielsForParc(int idParc) {
    List<Materiel> materiels = new ArrayList<>();
    String req = "SELECT `idMat`, `nomMat`, `etatMat`, `QuantiteMat`,`dateAjout` FROM `materiel` WHERE idParc = ?";

    try (PreparedStatement ps = cnx.prepareStatement(req)) {
        ps.setInt(1, idParc);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Materiel materiel = new Materiel();
                materiel.setNomParc(getNomParcForId(idParc));
                materiel.setIdMat(rs.getInt("idMAt"));// Appel de la méthode pour récupérer le nomParc
                materiel.setNomMat(rs.getString("nomMat"));
                materiel.setEtatMat(rs.getString("etatMat"));
                materiel.setQuantiteMat(rs.getFloat("QuantiteMat"));
                materiel.setDateAjout(rs.getDate("dateAjout").toLocalDate());
                materiels.add(materiel);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return materiels;
}

// Méthode pour récupérer le nomParc
private String getNomParcForId(int idParc) {
    String req = "SELECT `nomParc` FROM `parc` WHERE idParc = ?";

    try (PreparedStatement ps = cnx.prepareStatement(req)) {
        ps.setInt(1, idParc);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("nomParc");
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}

    public List<Materiel> getMaterielByNom(String nomMat) {
    List<Materiel> materiels = new ArrayList<>();
    String req = "SELECT `idMat`, `nomMat`, `etatMat`, `QuantiteMat`,`dateAjout`, `nomParc` FROM `materiel` WHERE nomMat= ?";

    try (PreparedStatement ps = cnx.prepareStatement(req)) {
        ps.setString(1, nomMat);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Materiel materiel = new Materiel();
                materiel.setIdMat(rs.getInt("idMAt"));// Appel de la méthode pour récupérer le nomParc
                materiel.setNomParc(rs.getString("nomParc"));
                materiel.setNomMat(rs.getString("nomMat"));
                materiel.setEtatMat(rs.getString("etatMat"));
                materiel.setQuantiteMat(rs.getFloat("QuantiteMat"));
                materiel.setDateAjout(rs.getDate("dateAjout").toLocalDate());
                materiels.add(materiel);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return materiels;
}

  public List<Materiel> getAllMateriels() {
        List<Materiel> materiels = new ArrayList<>();

        try {
            String req = "SELECT * FROM `materiel`";

            try (Statement stm = cnx.createStatement(); ResultSet rs = stm.executeQuery(req)) {

                while (rs.next()) {
                    Materiel materiel = new Materiel();
                    materiel.setNomParc(rs.getString("nomParc"));
                    materiel.setNomMat(rs.getString("nomMat"));
                    materiel.setEtatMat(rs.getString("etatMat"));
                    materiel.setQuantiteMat(rs.getFloat("QuantiteMat"));
                    materiel.setDateAjout(rs.getDate("dateAjout").toLocalDate());
                  

                    materiels.add(materiel);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return materiels;
    }
 
   public void modifierMateriel(Materiel materiel) {
       try {
        String req = "UPDATE `materiel` SET `nomMat`=?, `etatMat`=?, `QuantiteMat`=? WHERE idMat=?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setString(1, materiel.getNomMat());
            ps.setString(2, materiel.getEtatMat());
            ps.setFloat(3, materiel.getQuantiteMat());
            ps.setInt(4, materiel.getIdMat()); // Assurez-vous d'avoir getIdMat() dans votre classe Materiel
            ps.executeUpdate();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
 
    }
 
   public void verifierEtat(Materiel materiel) throws MessagingException {
       SendMail mail = new SendMail();
    if (materiel.getEtatMat().trim().equalsIgnoreCase("On panne")) { 
        System.out.println("Le matériel est en panne.");
        planifierEnvoiMailRappel(materiel);
    } 
    }
    public void verifierEtatModifier(Materiel materiel) throws MessagingException {
       SendMail mail = new SendMail();
    if (materiel.getEtatMat().trim().equalsIgnoreCase("On panne")) { 
        System.out.println("Le matériel est changé a on panne.");
        planifierEnvoiMailRappel(materiel);
    }else{
        System.out.println("Le matériel est changé a on marche.");
        mail.envoyerMailConfirmation(materiel);
    }
    }
   public void planifierEnvoiMailRappel( Materiel materiel) {
    
    SendMail mail = new SendMail();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    Runnable envoyeurMail = () -> {
        try {
        mail.envoyerEmailMaterielEnPanne(materiel);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    };

    int delaiInitial = 0; // Démarrer immédiatement
    int intervalle = 20; // Tous les 3 jours
    scheduler.scheduleAtFixedRate(envoyeurMail, delaiInitial, intervalle, TimeUnit.SECONDS);
}

}





