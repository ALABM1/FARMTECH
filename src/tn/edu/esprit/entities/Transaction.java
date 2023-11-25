/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import java.util.Date;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author jouin
 */
public class Transaction {
  private int id_tra;
    private String categ_tra;
    private String type_tra;
    private Date date_tra;
    private int montant;
    public Transaction(){
        
    }

    public Transaction(String categ_tra,String type_tra,Date date_tra, int montant ){
        this.categ_tra = categ_tra;
        this.type_tra = type_tra;
        this.date_tra = date_tra;
        this.montant = montant;
    }

    public Date getDate_tra() {
        return date_tra;
    }
   
    public int getId_tra() {
        return id_tra;
    }

    public String getCateg_tra() {
        return categ_tra;
    }

    public String getType_tra() {
        return type_tra;
    }

    public int getMontant() {
        return montant;
    }

    public void setCateg_tra(String categ_tra) {
        this.categ_tra = categ_tra;
    }

    public void setType_tra(String type_tra) {
        this.type_tra = type_tra;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id_tra;
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
        final Transaction other = (Transaction) obj;
        if (this.id_tra != other.id_tra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transaction{" + "categ_tra=" + categ_tra + ", type_tra=" + type_tra + ", date_tra=" + date_tra + ", montant=" + montant + '}';
    }

    

    public void setDate_tra(Date date) {
        this.date_tra = date;    }

    /*public void setDate_tra(int year, int month, int day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public void setId_tra(int id) {
            this.id_tra= id;}


   

    

   
    
}
   

