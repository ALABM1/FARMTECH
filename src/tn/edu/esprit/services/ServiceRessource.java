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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tn.edu.esprit.entities.Ressource;
import tn.edu.esprit.tools.DataSource;

/**
 *
 * @author rihab
 */
public class ServiceRessource implements IService<Ressource> {
    Connection cnx ;

    public ServiceRessource() {
        this.cnx= DataSource.getInstance().getConnection();
    }
    
    
    
    
     @Override
    public void ajouter(Ressource t) {
        try {
            String req = "INSERT INTO `ressource`(`typeRes`, `speciesRes`, `quantiteRes`, `idterrain`) VALUES ('" + t.getTypeRes() + "','" + t.getSpeciesRes() + "','" + t.getQuantiteRes() + "','" + t.getIdterrain() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        
        
        
        
        
        
        
        
        
    @Override
    public List<Ressource> getAll(Ressource t) {
      String req = "SELECT * FROM `ressource`";
      ArrayList<Ressource> ressources = new ArrayList();
    Statement stm;
    try {
        stm = this.cnx.createStatement();
        ResultSet rs=  stm.executeQuery(req);
    while (rs.next()){
        Ressource p = new Ressource();
        p.setIdRes(rs.getInt(1));
        p.setTypeRes(rs.getString("typeres"));
        p.setSpeciesRes(rs.getString("speciesres"));
        p.setQuantiteRes(rs.getInt("quantiteres"));
        ressources.add(p);
    }  
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return ressources;
    }

    
    
    
    
    
    @Override
    public void modifier(Ressource t) {
       try {
        String req = "UPDATE `ressource` SET `typeRes` = ?, `speciesRes` = ?, `quantiteRes` = ? WHERE `idRes` = ?"; 
        PreparedStatement ps = cnx.prepareStatement(req);   
        ps.setString(1, t.getTypeRes());
        ps.setString(2, t.getSpeciesRes());
        ps.setInt(3, t.getQuantiteRes());
        ps.setInt(4, t.getIdRes());  
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }
    
    
    
    
    
    
    

    @Override
    public void supprimer(int id) {
    try {
        String req = "DELETE FROM `ressource` WHERE `idRes` = " + id; 
        Statement stm = cnx.createStatement(); 
        stm.executeUpdate(req);
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    
    public Ressource getOne(int id) {
    Ressource ressource = null;
    try {
        String req = "SELECT * FROM `ressource` WHERE `idRes` = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            ressource = new Ressource();
            ressource.setIdRes(rs.getInt("idRes"));
            ressource.setTypeRes(rs.getString("typeRes"));
            ressource.setSpeciesRes(rs.getString("speciesRes"));
            ressource.setQuantiteRes(rs.getInt("quantiteRes"));
        }

        ps.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return ressource;
}

    public List<Ressource> getAllres(int idTerrain) {
        List<Ressource> ressource = new ArrayList<>();
    String req = "SELECT `idRes`, `typeRes`, `speciesRes`, `quantiteRes` FROM `ressource` WHERE idterrain = ?;";

    try (PreparedStatement ps = cnx.prepareStatement(req)) {
        ps.setInt(1, idTerrain);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ressource ressources = new Ressource();
                ressources.setIdRes(rs.getInt("idRes"));
                ressources.setTypeRes(rs.getString("typeRes"));
                ressources.setSpeciesRes(rs.getString("speciesRes"));
                ressources.setQuantiteRes(rs.getInt("quantiteRes"));
                
                ressource.add(ressources);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return ressource;
}
    
    
    public Ressource getOneBySpecies(String species) {
    Ressource ressource = null;
    try {
        String req = "SELECT * FROM `ressource` WHERE `speciesRes` = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, species);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            ressource = new Ressource();
            ressource.setIdRes(rs.getInt("idRes"));
            ressource.setTypeRes(rs.getString("typeRes"));
            ressource.setSpeciesRes(rs.getString("speciesRes"));
            ressource.setQuantiteRes(rs.getInt("quantiteRes"));
        }

        ps.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return ressource;
}

   public Map<String, Integer> getNombreTotalRessourcesPourTousTerrains() {
    Map<String, Integer> nombreRessourcesParTerrain = new HashMap<>();

    try {
        String req = "SELECT t.nomterrain, SUM(r.quantiteRes) as totalRessources " +
                     "FROM terrain t " +
                     "LEFT JOIN ressource r ON t.idterrain = r.idterrain " +
                     "WHERE r.quantiteRes IS NOT NULL " +
                     "GROUP BY t.nomterrain";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String nomTerrain = rs.getString("nomterrain");
            int totalRessources = rs.getInt("totalRessources");

            nombreRessourcesParTerrain.put(nomTerrain, totalRessources);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return nombreRessourcesParTerrain;
}

    @Override
    public Ressource create(Ressource t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ressource getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ressource> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Ressource t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rechercheType(int id_tra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List remplircombo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ressource getOne(String categ_tra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ressource getPneById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int caisse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String chatGPT(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int nbligne() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    }
    
    
    

