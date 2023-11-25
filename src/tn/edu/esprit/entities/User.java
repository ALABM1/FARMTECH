/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

/**
 *
 * @author aladi
 */
import java.awt.Image;
import java.util.List;

public class User {
    private static int lastId = 0; // Variable statique pour suivre le dernier ID attribué
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String numeroTelephone;
    private UserRole role;
    private String motDePasse;
     private String ville;
    private String sexe;
    private Image certification;
    private String specialite;
    private double superficieFerme;

    // Constructeur de la classe User
    public User(String nom, String prenom, String mail, String numeroTelephone, UserRole role, String motDePasse) {
    this.id = ++lastId; // Incrémentation de l'ID automatiquement
    this.nom = nom;
    this.prenom = prenom;
    this.mail = mail;
    this.numeroTelephone = numeroTelephone;
    this.role = role; // Utilisez la variable locale "role" pour initialiser la propriété "UserRole".
    this.motDePasse = motDePasse;
}

    public User() {
       
    }
    
    

    public User(String nom, String prenom, String email, String telephone, UserRole role, String sexe, String ville, String motDePasse) {
    this.id = ++lastId; // Incrémentation de l'ID automatiquement
    this.nom = nom;
    this.prenom = prenom;
    this.mail = email;
    this.numeroTelephone = telephone;
    this.role = role; // Utilisez la variable locale "role" pour initialiser la propriété "UserRole".
    this.motDePasse = motDePasse;
     this.sexe= sexe;
    this.ville= ville;
   }

    public User(String mail, UserRole role) {
        this.mail = mail;
        this.role = role;
    }

      
     // Getter pour l'ID
    public int getId() {
        return id;
    }
     public void setId(int id) {
        this.id=id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
    public UserRole getRole() {
        return role;
}

    public void setRole(UserRole role) {
        this.role = role;
}


    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getVille() {
        return ville;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Image getCertification() {
        return certification;
    }

    public void setCertification(Image certification) {
        this.certification = certification;
    }


    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public double getSuperficieFerme() {
        return superficieFerme;
    }

    public void setSuperficieFerme(double superficieFerme) {
        this.superficieFerme = superficieFerme;
    }
    
    @Override
public String toString() {
    return "User{" +
           "id=" + id +
           ", nom='" + nom + '\'' +
           ", prenom='" + prenom + '\'' +
           ", mail='" + mail + '\'' +
           ", numeroTelephone='" + numeroTelephone + '\'' +
           ", role=" + role +
           ", motDePasse='" + motDePasse + '\'' +
           ", ville='" + ville + '\'' +
           ", sexe='" + sexe + '\'' +
           ", certification=" + certification +
           ", specialite='" + specialite + '\'' +
           ", superficieFerme=" + superficieFerme +
           '}';
}


   

  
    
}
