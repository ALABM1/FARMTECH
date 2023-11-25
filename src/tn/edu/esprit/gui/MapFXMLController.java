/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author rihab
 */
public class MapFXMLController implements Initializable {

    @FXML
     WebView mapView;

    private String localisation;

    // Méthode pour définir la localisation à afficher sur la carte
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Obtenez le moteur WebEngine du WebView
        WebEngine webEngine = mapView.getEngine();

        // Chargez la page OpenStreetMap
        webEngine.load("https://www.google.com/maps/place/" + localisation  );
    }
    }    
    

