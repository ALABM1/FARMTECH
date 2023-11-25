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
public class Terrain {
    private int idTerrain;
    private String nomTerrain;
    private String localisation;
    private String superficie;
    private List<Ressource> ressource ; 

    public Terrain() {
    }

    public Terrain(int idTerrain, String nomTerrain, String localisation, String superficie, List<Ressource> ressource) {
        this.idTerrain = idTerrain;
        this.nomTerrain = nomTerrain;
        this.localisation = localisation;
        this.superficie = superficie;
        this.ressource = ressource;
    }

    public Terrain(String nomTerrain, String localisation, String superficie, List<Ressource> ressource) {
        this.nomTerrain = nomTerrain;
        this.localisation = localisation;
        this.superficie = superficie;
        this.ressource = ressource;
    }

    public Terrain(String nomTerrain, String localisation, String superficie) {
        this.nomTerrain = nomTerrain;
        this.localisation = localisation;
        this.superficie = superficie;
    }

    

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public List<Ressource> getRessource() {
        return ressource;
    }

    public void setRessource(List<Ressource> ressource) {
        this.ressource = ressource;
    }

    @Override
    public String toString() {
        return "Terrain{" + "idTerrain=" + idTerrain + ", nomTerrain=" + nomTerrain + ", localisation=" + localisation + ", superficie=" + superficie + ", ressource=" + ressource + '}';
    }

    
    
    
}
