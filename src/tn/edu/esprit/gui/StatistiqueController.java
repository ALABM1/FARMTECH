package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class StatistiqueController implements Initializable {
    @FXML
    private TextArea nombreTerrain;
    
    @FXML
    private TextArea totalSuperficie;
    @FXML
    private TextArea statistiquesTerrains;
    @FXML
    private TextArea sommeActAttent;
    @FXML
    private TextArea sommeActTermine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Vous pouvez effectuer des opérations d'initialisation ici si nécessaire.
    }

    // Méthode pour définir le nombre de terrains affiché dans l'interface
    public void setNombreTerrain(String nombreTerrains) {
        nombreTerrain.setText("Le nombre totale des terrains est :  " +nombreTerrains+" terrains");
        
    }
    
    // Méthode pour définir la superficie totale affichée dans l'interface
    public void setTotalSuperficie(String superficieTotale) {
        totalSuperficie.setText("La superficie totale de vos terrains est:  " +superficieTotale +" m2");
    }

    

    public void setStatistiquesTerrains(Map<String, Integer> nombreRessourcesParTerrain) {
    StringBuilder statistiques = new StringBuilder("Nombre total de ressources par terrain :  \n");

    for (Map.Entry<String, Integer> entry : nombreRessourcesParTerrain.entrySet()) {
        statistiques.append("Terrain ").append(entry.getKey()).append("  :   ").append(entry.getValue()).append("   ressources\n");
    }

    statistiquesTerrains.setText(statistiques.toString());
}

    void setSommeActivitesEnAttente(String sommeEnAttente) {
        sommeActAttent.setText("Le nombre d'activités en attente est : " + sommeEnAttente);
    }

    void setSommeActivitesTerminees(String sommeTerminees) {
        sommeActTermine.setText("Le nombre d'activités terminées est : " + sommeTerminees);
    }

   @FXML
private void retourStat(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/firstPageFXML.fxml"));
        Parent root = loader.load();

        // Obtenez la scène actuelle à partir de l'événement
        Scene scene = ((Node) event.getSource()).getScene();

        // Mettez la nouvelle scène dans la fenêtre principale (Stage)
        Stage stage = (Stage) scene.getWindow();
        stage.setScene(new Scene(root));

    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
