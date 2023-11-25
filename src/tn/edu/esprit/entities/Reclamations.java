/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

/**
 *
 * @author Houssem HENCHIR
 */

public class Reclamations implements IEntity {

    private int id;

    private String type;

    private String description;

    private String email;

    private String telephone;



    public Reclamations() {

        // Default constructor

    }

 

    public Reclamations(String type, String description, String email, String telephone) {
        this.type = type;
        this.description = description;
        this.email = email;
        this.telephone = telephone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

 
    @Override

    public String toString() {

        return "Reclamation [id=" + id + ", type=" + type + ", description=" + description + ", email=" + email

                + ", telephone=" + telephone + "]";

    }
}