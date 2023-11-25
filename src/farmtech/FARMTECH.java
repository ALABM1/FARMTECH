/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmtech;

import java.util.List;
import tn.edu.esprit.entities.Activite;
import tn.edu.esprit.entities.Ressource;
import tn.edu.esprit.entities.Terrain;
import tn.edu.esprit.services.ServiceActivite;
import tn.edu.esprit.services.ServiceRessource;
import tn.edu.esprit.services.ServiceTerrain;
import tn.edu.esprit.tools.DataSource;

/**
 *
 * @author megbl
 */
public class FARMTECH {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataSource.getInstance();
        DataSource.getInstance();
        DataSource.getInstance();
        DataSource.getInstance();
        DataSource.getInstance();
        
        ServiceActivite serviceActivite = new ServiceActivite();

    
   // Activite nouvelleActivite = new Activite();
    //nouvelleActivite.setObjetAct("Objet de l'activité");
    //nouvelleActivite.setDescriptionAct("Description de l'activité");
    //nouvelleActivite.setDistAct("Distribution de l'activité");
    //nouvelleActivite.setEmailDist("email3@example.com");

   
    //serviceActivite.ajouter(nouvelleActivite);
    // serviceActivite.supprimer(1);
    //Activite activiteAModifier = new Activite();
    //activiteAModifier.setIdAct(3); // Remplacez 1 par l'ID de l'activité que vous souhaitez modifier
    //activiteAModifier.setObjetAct("Nouvel objet");
    //activiteAModifier.setDescriptionAct("Nouvelle description");
    //activiteAModifier.setDistAct("Nouvelle distribution");
    //activiteAModifier.setEmailDist("nouveau@email.com");

    //serviceActivite.modifier(activiteAModifier);
    
     //ServiceTerrain serviceTerrain = new ServiceTerrain();
    // Créez un objet Terrain avec les détails du terrain que vous souhaitez ajouter
    //Terrain terrain = new Terrain();
    //terrain.setNomTerrain("Terrain A");
    //terrain.setLocalisation("Localisation XYZ");
    //terrain.setSuperficie(1000.0);

    // Ajoutez le terrain à la base de données en utilisant le service
    //serviceTerrain.ajouter(terrain);

    
    
    }
   
    
    
    
}
