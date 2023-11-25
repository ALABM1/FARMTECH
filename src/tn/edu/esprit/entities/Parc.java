/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author megbl
 */
public class Parc {
    private int idParc ;
    private String nomParc ;
    private String adresseParc ;
    private float  superficieParc ;
    private List<Materiel> materiels; 

    public Parc() {
    }

    
    public Parc(String nomParc, String adresseParc, float superficieParc, List<Materiel> materiels) {
        this.nomParc = nomParc;
        this.adresseParc = adresseParc;
        this.superficieParc = superficieParc;
        this.materiels = materiels;
    }

    public Parc(String nomParc, String adresseParc, float superficieParc) {
        this.nomParc = nomParc;
        this.adresseParc = adresseParc;
        this.superficieParc = superficieParc;
    }

    public int getIdParc() {
        return idParc;
    }

    public String getNomParc() {
        return nomParc;
    }

    public String getAdresseParc() {
        return adresseParc;
    }

    public float getSuperficieParc() {
        return superficieParc;
    }

    public List<Materiel> getMateriels() {
        return materiels;
    }

    public void setIdParc(int idParc) {
        this.idParc = idParc;
    }

    public void setNomParc(String nomParc) {
        this.nomParc = nomParc;
    }

    public void setAdresseParc(String adresseParc) {
        this.adresseParc = adresseParc;
    }

    public void setSuperficieParc(float superficieParc) {
        this.superficieParc = superficieParc;
    }

    public void setMateriels(List<Materiel> materiels) {
        this.materiels = materiels;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idParc;
        hash = 89 * hash + Objects.hashCode(this.nomParc);
        hash = 89 * hash + Objects.hashCode(this.adresseParc);
        hash = 89 * hash + Float.floatToIntBits(this.superficieParc);
        hash = 89 * hash + Objects.hashCode(this.materiels);
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
        final Parc other = (Parc) obj;
        if (this.idParc != other.idParc) {
            return false;
        }
        if (Float.floatToIntBits(this.superficieParc) != Float.floatToIntBits(other.superficieParc)) {
            return false;
        }
        if (!Objects.equals(this.nomParc, other.nomParc)) {
            return false;
        }
        if (!Objects.equals(this.adresseParc, other.adresseParc)) {
            return false;
        }
        if (!Objects.equals(this.materiels, other.materiels)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Parc{" + "nomParc=" + nomParc + ", adresseParc=" + adresseParc + ", superficieParc=" + superficieParc + ", materiels=" + materiels + '}';
    }

    
    
}

