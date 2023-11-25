/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.Parc;
import tn.edu.esprit.tools.DataSource;

/**
 *
 * @author megbl
 */
public class ServiceParc implements IService<Parc> {
    
    Connection cnx ;

    
    public ServiceParc() {
    this.cnx= DataSource.getInstance().getConnection();    
    }

    

  

   
    
    @Override
    public void ajouter(Parc t) {
        try {
        String req = "INSERT INTO `parc`( `nomParc`, `adresseParc`, `superficieParc`) VALUES (?, ?, ?)";
        
           try (PreparedStatement ps = (PreparedStatement) cnx.prepareStatement(req)) {
               ps.setString(1, t.getNomParc());
               ps.setString(2, t.getAdresseParc());               
                ps.setFloat(3, t.getSuperficieParc()); 
               ps.executeUpdate();
           }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
       
    }
     @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM  `parc` WHERE idParc = ?";
         try (PreparedStatement ps = (PreparedStatement) cnx.prepareStatement(req)) {
             ps.setInt(1, id);
             ps.executeUpdate();
         }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

@Override
public Parc getOne(String nomParc) {
    Parc parc = null;
    String req = "SELECT adresseParc, superficieParc FROM parc WHERE nomParc = ?";

    try (PreparedStatement ps = (PreparedStatement) cnx.prepareStatement(req)) {
        ps.setString(1, nomParc);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                parc = new Parc();
                parc.setNomParc(nomParc);
                parc.setAdresseParc(rs.getString("adresseParc"));
                float superficie = Float.parseFloat(rs.getString("superficieParc"));
                parc.setSuperficieParc(superficie);            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return parc;
}

   public List<Parc> getAll() {
    List<Parc> parcs = new ArrayList<>();
    
    try {
        String req = "SELECT * FROM parc";
        try (Statement stm = cnx.createStatement(); ResultSet rs = stm.executeQuery(req)) {
            
            while (rs.next()) {
                Parc parc = new Parc();
                parc.setIdParc(rs.getInt("idParc"));
                parc.setNomParc(rs.getString("nomParc"));
                parc.setAdresseParc(rs.getString("adresseParc"));
                float superficie = Float.parseFloat(rs.getString("superficieParc"));
                parc.setSuperficieParc(superficie); 
                parcs.add(parc);
            }
            
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return parcs;
}

    @Override
public void modifier(Parc t) {
    try {
        String req = "UPDATE `parc` SET `nomParc`=?, `adresseParc`=?, `superficieParc`=? WHERE idParc=?";
        
        try (PreparedStatement ps = (PreparedStatement) cnx.prepareStatement(req)) {
            ps.setString(1, t.getNomParc());
            ps.setString(2, t.getAdresseParc());
            ps.setFloat(3, t.getSuperficieParc()); // Assurez-vous d'avoir une méthode getSuperficieParc() dans la classe Parc
            ps.setInt(4, t.getIdParc()); // Assurez-vous d'avoir une méthode getIdParc() dans la classe Parc
            ps.executeUpdate();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
    public List<Parc> getAll(Parc t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Parc create(Parc t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Parc getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Parc t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Parc getOne(int id) {
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
    public Parc getPneById(int id) throws SQLException {
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



