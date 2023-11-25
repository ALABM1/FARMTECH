/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;
import tn.edu.esprit.entities.Materiel;
import tn.edu.esprit.entities.Parc;
/**
 *
 * @author megbl
 */
public class SelectedParcManager {
    
    private static Parc selectedParc;
    private static Materiel selectedMateriel ;

    public static Materiel getSelectedMateriel() {
        return selectedMateriel;
    }

    public static void setSelectedMateriel(Materiel selectedMateriel) {
        SelectedParcManager.selectedMateriel = selectedMateriel;
    }
    

    public static Parc getSelectedParc() {
        return selectedParc;
    }

    public static void setSelectedParc(Parc parc) {
        selectedParc = parc;
    }

    static void setSelectedParc(Materiel selectedMateriel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
