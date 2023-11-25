/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import tn.edu.esprit.entities.Materiel;
import tn.edu.esprit.entities.Parc;
import tn.edu.esprit.services.ServiceMateriel;

/**
 * FXML Controller class
 *
 * @author megbl
 */
public class AjouterMaterielFXMLController implements Initializable {

    @FXML
    private TextField fxQunMat;
    @FXML
    private TextField fxNomMat;
    @FXML
    private RadioButton fxON;
    @FXML
    private RadioButton fxOff;
    @FXML
    private ToggleGroup Etat;
    private Parc selectedParc ;
    @FXML
    private Label fxnomparc;
    @FXML
    private Label fxmail;

    /**  
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
     
    }    
  
    
 public Parc initData(Parc selectedParc) {
   
             
    String nomParc = selectedParc.getNomParc();
    fxnomparc.setText("' "+nomParc+" '");
     return selectedParc;
}
@FXML
private void fxSaveMateriel(ActionEvent event) throws MessagingException {
    Parc parcSelectionne = SelectedParcManager.getSelectedParc();

    if (parcSelectionne != null) {
        try {
            String nomMat = fxNomMat.getText();
            float quantiteMat = Float.parseFloat(fxQunMat.getText());
            boolean etatMat = fxON.isSelected(); 

            // Vérification si le nom du matériel existe déjà
            
            ServiceMateriel sm = new ServiceMateriel();
            List<Materiel> materiels = sm.getMaterielByNom(nomMat);
            if (!materiels.isEmpty()) {
                afficherAlerte("Erreur d'ajout", "Le nom du matériel existe déjà.");
                return;
            }
            if(nomMat.length()<3)
            { afficherAlerte("Errer d'ajout", "Nom de carectere minimale est 3 ");
            return;
            }
            // Vérification des contraintes de saisie
            if (nomMat.isEmpty() || quantiteMat <= 0 || (!fxON.isSelected() && !fxOff.isSelected())) {
                afficherAlerte("Erreur de saisie", "Veuillez remplir tous les champs.");
                return;
            }

            // Appeler la méthode d'ajout de matériel
            ServiceMateriel serviceMateriel = new ServiceMateriel();

            Materiel nouveauMateriel = new Materiel();
            nouveauMateriel.setNomMat(nomMat);
            nouveauMateriel.setQuantiteMat(quantiteMat);
            nouveauMateriel.setIdParc(parcSelectionne.getIdParc());
            nouveauMateriel.setDateAjout(LocalDate.now());
            nouveauMateriel.setNomParc(parcSelectionne.getNomParc());
            String etatmateriel = "";
            if(fxON.isSelected()){etatmateriel= "On marche" ;
            }else{etatmateriel= "On panne "; 
                        fxmail.setVisible(true);
                    } 
              nouveauMateriel.setEtatMat(etatmateriel);
              
            
            serviceMateriel.ajouterMateriel(nouveauMateriel);
           
     
            serviceMateriel.verifierEtat(nouveauMateriel);
            
            // Si l'ajout réussit, afficher une alerte de confirmation
            afficherConfirmation("Succès", "Le matériel a été ajouté avec succès.");

        } catch (NumberFormatException e) {
            afficherAlerte("Erreur de format", "La quantité doit être un nombre valide.");
        }
    } else {
        System.out.println("Aucun parc sélectionné.");
    }
}

private void afficherAlerte(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}
private void afficherConfirmation(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}
    void initData(Materiel selectedMateriel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void gotolistparc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/GetAllFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Liste de materiels");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterMaterielFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}



    

