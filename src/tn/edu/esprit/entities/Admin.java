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
import java.util.List;

public class Admin extends User {

    // Constructeur de la classe Admin
    public Admin(String nom, String prenom, String mail, String numeroTelephone, String motDePasse) {
        super( nom, prenom, mail, numeroTelephone, UserRole.ADMIN, motDePasse); // Incrémentation de l'ID automatiquement
    }

    public Admin(String nom, String prenom, String mail, String numeroTelephone, String motDePasse, String ville, String sexe) {
    super(nom, prenom, mail, numeroTelephone, UserRole.ADMIN, motDePasse, ville, sexe);
}

    

    
}
