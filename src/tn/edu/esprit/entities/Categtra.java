/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

/**
 *
 * @author jouin
 */
public class Categtra {
    private int id_cat_tra;
    private String nom_cat_tra;
    private String descrip_cat_tra;
    
    public Categtra(){
        
    }
    public Categtra(String nom_cat_tra, String descrip_cat_tra){
        this.nom_cat_tra=nom_cat_tra;
        this.descrip_cat_tra=descrip_cat_tra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id_cat_tra;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categtra other = (Categtra) obj;
        if (this.id_cat_tra != other.id_cat_tra) {
            return false;
        }
        return true;
    }

    public void setNom_cat_tra(String nom_cat_tra) {
        this.nom_cat_tra = nom_cat_tra;
    }

    public void setDescrip_cat_tra(String descrip_cat_tra) {
        this.descrip_cat_tra = descrip_cat_tra;
    }

    public int getId_cat_tra() {
        return id_cat_tra;
    }

    public String getNom_cat_tra() {
        return nom_cat_tra;
    }

    public String getDescrip_cat_tra() {
        return descrip_cat_tra;
    }

    public void setId_cat_tra(int id) {
        this.id_cat_tra= id;    }
    
}