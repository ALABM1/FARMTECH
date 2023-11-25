/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.Categtra;
import tn.edu.esprit.tools.DataSource;

/**
 *
 * @author jouin
 */
public class Servicecategtra implements IService <Categtra>{
    Connection cnx ;
    public Servicecategtra(){
    this.cnx= DataSource.getInstance().getConnection();
}
    @Override
    public void ajouter(Categtra t) {
        try {
        String req = "INSERT INTO `categtrans`(`nom_cat_tra`, `descrip_cat_tra`) VALUES (? ,?)";
        
               PreparedStatement ps = cnx.prepareStatement(req); 
               ps.setString(1, t.getNom_cat_tra());
               ps.setString(2, t.getDescrip_cat_tra());
               ps.executeUpdate();
           
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        }

    
    
    
    
    @Override
    public void modifier(Categtra t) {
    try {
            String req = "UPDATE `categtrans` SET `nom_cat_tra`=?, `descrip_cat_tra`=? WHERE id_cat_tra=?";
        
               try(PreparedStatement ps =(PreparedStatement) cnx.prepareStatement(req)){
               ps.setString(1, t.getNom_cat_tra());
               ps.setString(2, t.getDescrip_cat_tra());
               ps.setInt(3, t.getId_cat_tra()); 

               ps.executeUpdate();
               System.out.println("modifié");}
           
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }        
    }
    
    
    
    
    @Override
  public List<String> remplircombo() {
    List<String> list = new ArrayList<>();

    try {
        // Establish a database connection
       
        // Create a SQL statement
        Statement statement = cnx.createStatement();

        // Execute the SQL query
        String query = "SELECT `nom_cat_tra` FROM `categtrans`";
        ResultSet resultSet = statement.executeQuery(query);

        // Iterate over the results and add each name to the list
        while (resultSet.next()) {
            list.add(resultSet.getString("nom_cat_tra"));
        }

        // Close resources (statement, connection, etc.) when done.

    } catch (Exception e) {
        // Handle any exceptions that may occur during the process.
        e.printStackTrace();
    }

    return list;
}
  
  
    @Override
    public void supprimer(int id_cat_tra) {
try{
            String req = "DELETE FROM `categtrans` WHERE id_cat_tra="+id_cat_tra;
            PreparedStatement ps = cnx.prepareStatement(req); 
            ps.executeUpdate();
            System.out.println("supprimé");
           
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }          }
    
    
    
    
    
     public void rechercheType (int id_cat_tra){}
     
     
     
     
     
     
    @Override
   public Categtra getOne(String nom_cat_tra) {
    Categtra categtra = new Categtra();
    String req = "SELECT id_cat_tra, nom_cat_tra, descrip_cat_tra FROM categtrans WHERE nom_cat_tra = ?";

    try (PreparedStatement ps = (PreparedStatement) cnx.prepareStatement(req)) {
        ps.setString(1, nom_cat_tra);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                categtra.setId_cat_tra(rs.getInt("id_cat_tra"));
                categtra.setNom_cat_tra(rs.getString("nom_cat_tra"));
                categtra.setDescrip_cat_tra(rs.getString("descrip_cat_tra"));
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return categtra;
}
    
    
    
    
    

    @Override
    public List<Categtra> getAll() {
         ArrayList<Categtra> categtranss = new ArrayList();
    try {
        String req = "SELECT * FROM `categtrans`";
        try (Statement stm = cnx.createStatement(); ResultSet rs = stm.executeQuery(req)) {
            while (rs.next()){
            Categtra c = new Categtra();
            c.setId_cat_tra(rs.getInt("Id_cat_tra"));
            c.setNom_cat_tra(rs.getString("Nom_cat_tra"));
            c.setDescrip_cat_tra(rs.getString("Descrip_cat_tra"));
            categtranss.add(c);
            }
    }
        
        
    } catch (SQLException ex) {
    
        System.out.println(ex.getMessage());
    
    }
    return categtranss; 
    }

    @Override
    public Categtra getPneById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  @Override
    public int caisse () {  
        return 5;
    } 

    @Override
    public String chatGPT(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int nbligne() {
    int nb = 0; // Initialize dep to 0 for Dépense
    

    // Calculate the sum of Dépense transactions
    String queryDepense = "SELECT COUNT(*) FROM categtrans";
    try (PreparedStatement psDepense = (PreparedStatement) cnx.prepareStatement(queryDepense);
         ResultSet rsDepense = psDepense.executeQuery()) {
        if (rsDepense.next()) {
            nb = rsDepense.getInt(1); // Retrieve the sum and store it in dep
            
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
     return nb;    }

    @Override
    public Categtra create(Categtra t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categtra getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Categtra t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categtra getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categtra> getAll(Categtra t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
