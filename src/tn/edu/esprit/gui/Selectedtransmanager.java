/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;
import tn.edu.esprit.entities.Transaction;
import tn.edu.esprit.entities.Categtra;
/**
 *
 * @author jouin
 */
public class Selectedtransmanager {
    
    private static Transaction selectedTransaction;
    private static Categtra selectedCategtra ;

    public static Categtra getSelectedCategtra() {
        return selectedCategtra;
    }

    public static void setSelectedCategtra(Categtra selectedCategtra) {
        Selectedtransmanager.selectedCategtra = selectedCategtra;
    }
    

    public static Transaction getSelectedTransaction() {
        return selectedTransaction;
    }

    public static void setSelectedTransaction(Transaction transaction) {
        selectedTransaction = transaction;
    }

    static void setSelectedTransaction(Categtra selectedCategtra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
