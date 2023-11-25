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

public class veterinaire extends User {
    private static int lastveterinaireId = 0; // Variable statique pour suivre le dernier ID attribué aux administrateurs
    private Image certification; // Attribut pour stocker l'image de certification du vétérinaire
    private String ville;
    private String sexe;
    // Constructeur de la classe VeterinaireClient
    public veterinaire(String nom, String prenom, String mail, String numeroTelephone, String motDePasse, String ville, String sexe, Image certification) {
        super(nom, prenom, mail, numeroTelephone, UserRole.VETERINAIRE, motDePasse);
        this.ville = ville;
        this.sexe = sexe;
        this.certification = certification;
    }
   
     public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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
}
    


    

    // Ajoutez d'autres méthodes spécifiques au vétérinaire si nécessaire


