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
public class ouvrier extends User {
    private static int lastveterinaireId = 0; // Variable statique pour suivre le dernier ID attribué aux administrateurs
    private String ville;
    private String sexe;
    private String specialite; // Attribut pour stocker la spécialité de l'ouvrier

    // Constructeur de la classe OuvrierClient
    public ouvrier( String nom, String prenom, String mail, String numeroTelephone, String motDePasse, String ville, String sexe, String specialite) {
        super(nom, prenom, mail, numeroTelephone, UserRole.OUVRIER, motDePasse);
        this.ville = ville;
        this.sexe = sexe;
        this.specialite = specialite;
    }

    // Getter et setter pour l'attribut specialite
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

   // Getter pour la ville
    public String getVille() {
        return ville;
    }

    // Setter pour la ville
    public void setVille(String ville) {
        this.ville = ville;
    }

    // Getter pour le sexe
    public String getSexe() {
        return sexe;
    }

    // Setter pour le sexe
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
}

