package tn.edu.esprit.entities;

public class Activite {
    private int idAct;
    private String objetAct;
    private String descriptionAct;
    private String distAct;
    private String emailDist;
    private String speciesRES;
    private String etatAct;

    public Activite() {
    }

    public Activite(String objetAct, String descriptionAct, String distAct, String emailDist, String speciesRES, String etatAct) {
        this.objetAct = objetAct;
        this.descriptionAct = descriptionAct;
        this.distAct = distAct;
        this.emailDist = emailDist;
        this.speciesRES = speciesRES;
        this.etatAct = etatAct;
    }

    public Activite(int idAct, String objetAct, String descriptionAct, String distAct, String emailDist, String speciesRES, String etatAct) {
        this.idAct = idAct;
        this.objetAct = objetAct;
        this.descriptionAct = descriptionAct;
        this.distAct = distAct;
        this.emailDist = emailDist;
        this.speciesRES = speciesRES;
        this.etatAct = etatAct;
    }

    public int getIdAct() {
        return idAct;
    }

    public void setIdAct(int idAct) {
        this.idAct = idAct;
    }

    public String getObjetAct() {
        return objetAct;
    }

    public void setObjetAct(String objetAct) {
        this.objetAct = objetAct;
    }

    public String getDescriptionAct() {
        return descriptionAct;
    }

    public void setDescriptionAct(String descriptionAct) {
        this.descriptionAct = descriptionAct;
    }

    public String getDistAct() {
        return distAct;
    }

    public void setDistAct(String distAct) {
        this.distAct = distAct;
    }

    public String getEmailDist() {
        return emailDist;
    }

    public void setEmailDist(String emailDist) {
        this.emailDist = emailDist;
    }

    public String getSpeciesRES() {
        return speciesRES;
    }

    public void setSpeciesRES(String speciesRES) {
        this.speciesRES = speciesRES;
    }

    public String getEtatAct() {
        return etatAct;
    }

    public void setEtatAct(String etatAct) {
        this.etatAct = etatAct;
    }

    @Override
    public String toString() {
        return "Activite{" + "idAct=" + idAct + ", objetAct=" + objetAct + ", descriptionAct=" + descriptionAct + ", distAct=" + distAct + ", emailDist=" + emailDist + ", speciesRES=" + speciesRES + ", etatAct=" + etatAct + '}';
    }
}
