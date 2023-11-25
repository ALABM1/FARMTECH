/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author rihab
 */
public class DashboardController implements Initializable {

    @FXML
    private BorderPane bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterTerrain(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterTerrainFXML.fxml"));
Parent root = loader.load();
    }

    @FXML
    private void AfficherTerrain(MouseEvent event) {
        loadPage("AfficherTerrain");
    }

    @FXML
    private void AjouterRessource(MouseEvent event) {
        loadPage("AjouterResssource");
    }

    @FXML
    private void AfficherRessource(MouseEvent event) {
        loadPage("AfficherRessource");
    }

    @FXML
    private void AjouterActivite(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterActiviteFXML.fxml"));
Parent root = loader.load();
    }

    @FXML
    private void AfficherActivite(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherActiviteFXML.fxml"));
Parent root = loader.load();
    }
    
    private void loadPage (String page){
       Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }
    
    
    
}
