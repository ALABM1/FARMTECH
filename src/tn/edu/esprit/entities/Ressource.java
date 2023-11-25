/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import java.util.List;


/**
 *
 * @author rihab
 */
public class Ressource {
    private int idRes;
    private String typeRes;
    private String speciesRes;
    private int quantiteRes;
    private int idterrain;


    public Ressource() {
    }

    public Ressource(String typeRes, String speciesRes, int quantiteRes) {
        this.typeRes = typeRes;
        this.speciesRes = speciesRes;
        this.quantiteRes = quantiteRes;
    }

    public Ressource(int idRes, String typeRes, String speciesRes, int quantiteRes, int idterrain) {
        this.idRes = idRes;
        this.typeRes = typeRes;
        this.speciesRes = speciesRes;
        this.quantiteRes = quantiteRes;
        this.idterrain = idterrain;
    }

   

    public Ressource(String typeRes, String speciesRes, int quantiteRes, int idterrain) {
        this.typeRes = typeRes;
        this.speciesRes = speciesRes;
        this.quantiteRes = quantiteRes;
        this.idterrain = idterrain;
    }
    
    

    

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public String getTypeRes() {
        return typeRes;
    }

    public void setTypeRes(String typeRes) {
        this.typeRes = typeRes;
    }

    public String getSpeciesRes() {
        return speciesRes;
    }

    public void setSpeciesRes(String speciesRes) {
        this.speciesRes = speciesRes;
    }

    public int getQuantiteRes() {
        return quantiteRes;
    }

    public void setQuantiteRes(int quantiteRes) {
        this.quantiteRes = quantiteRes;
    }

    public int getIdterrain() {
        return idterrain;
    }

    public void setIdterrain(int idterrain) {
        this.idterrain = idterrain;
    }


    @Override
    public String toString() {
        return "Ressource{" + "idRes=" + idRes + ", typeRes=" + typeRes + ", speciesRes=" + speciesRes + ", quantiteRes=" + quantiteRes + ", idterrain=" + idterrain +  '}';
    }
    

   

   
    
    
}
