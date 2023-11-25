package tn.edu.esprit.entities;

public class Agriculteur extends User {
    private static int lastAdminId = 0; 
    private String ville;
    private String sexe;// Variable statique pour suivre le dernier ID attribué aux administrateurs
    private double superficieFerme; // Attribut pour stocker la superficie de la ferme de l'agriculteur

    // Constructeur de la classe AgriculteurClient
public Agriculteur(String nom, String prenom, String mail, String numeroTelephone, String motDePasse, String sexe, String ville, double superficieFerme) {
    super(nom, prenom, mail, numeroTelephone, UserRole.AGRICULTEUR, motDePasse);
    this.sexe = sexe;
    this.ville = ville;
    this.superficieFerme = superficieFerme;
}
     
     @Override
public String toString() {
    return "Agriculteur [id=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", mail=" + getMail() + ", numeroTelephone=" + getNumeroTelephone() + ", role=" + getRole() + ", motDePasse=" + getMotDePasse() + ", sexe=" + sexe + ", ville=" + ville + ", superficieFerme=" + superficieFerme + "]";
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

    // Getter pour la superficie de la ferme
    public double getSuperficieFerme() {
        return superficieFerme;
    }

    // Setter pour la superficie de la ferme
    public void setSuperficieFerme(double superficieFerme) {
        this.superficieFerme = superficieFerme;
}
}
