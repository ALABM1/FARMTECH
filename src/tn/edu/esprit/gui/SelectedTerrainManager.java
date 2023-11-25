/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import tn.edu.esprit.entities.Ressource;
import tn.edu.esprit.entities.Terrain;

/**
 *
 * @author rihab
 */
public class SelectedTerrainManager {
    private static Terrain selectedTerrain;
    private static Ressource selectedRessource ;

    public static Ressource getSelectedRessource() {
        return selectedRessource;
    }

    public static void setSelectedRessource(Ressource selectedRessource) {
        SelectedTerrainManager.selectedRessource = selectedRessource;
    }
    

    public static Terrain getSelectedTerrain() {
        return selectedTerrain;
    }

    public static void setSelectedTerrain(Terrain terrain) {
        selectedTerrain = terrain;
    }

    static void setSelectedTerrain(Ressource selectedRessource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
