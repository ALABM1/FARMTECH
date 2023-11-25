package tn.edu.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.Terrain;
import tn.edu.esprit.tools.DataSource;

public class ServiceTerrain implements IService<Terrain> {
    Connection cnx;

    public ServiceTerrain() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Terrain t) {
        try {
            String req = "INSERT INTO `terrain`(`nomTerrain`, `localisation`, `superficie`) VALUES (?, ?, ?)";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNomTerrain());
            ps.setString(2, t.getLocalisation());
            ps.setString(3, t.getSuperficie());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Terrain t) {
        try {
            String req = "UPDATE `terrain` SET `nomTerrain` = ?, `localisation` = ?, `superficie` = ? WHERE `idTerrain` = ?";

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, t.getNomTerrain());
            ps.setString(2, t.getLocalisation());
            ps.setString(3, t.getSuperficie());
            ps.setInt(4, t.getIdTerrain());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `terrain` WHERE `idTerrain` = " + id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
@Override
    public Terrain getOne(int id) {
        Terrain terrain = null;
        try {
            String req = "SELECT * FROM `terrain` WHERE `idTerrain` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                terrain = new Terrain();
                terrain.setIdTerrain(rs.getInt("idTerrain"));
                terrain.setNomTerrain(rs.getString("nomTerrain"));
                terrain.setLocalisation(rs.getString("localisation"));
                terrain.setSuperficie(rs.getString("superficie"));
            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return terrain;
    }
    
    
public Terrain getOneByNom(String nomTerrain) {
    Terrain terrain = null;
    try {
        String req = "SELECT * FROM `terrain` WHERE `nomTerrain` = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, nomTerrain);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            terrain = new Terrain();
            terrain.setIdTerrain(rs.getInt("idTerrain"));
            terrain.setNomTerrain(rs.getString("nomTerrain"));
            terrain.setLocalisation(rs.getString("localisation"));
            terrain.setSuperficie(rs.getString("superficie"));
        }

        ps.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return terrain;
}



    @Override
    public List<Terrain> getAll(Terrain t) {
       String req = "SELECT * FROM `terrain`";
        ArrayList<Terrain> terrains = new ArrayList<>();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                Terrain terrain = new Terrain();
                terrain.setIdTerrain(rs.getInt("idTerrain"));
                terrain.setNomTerrain(rs.getString("nomTerrain"));
                terrain.setLocalisation(rs.getString("localisation"));
                terrain.setSuperficie(rs.getString("superficie"));
                terrains.add(terrain);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return terrains;
    }

    public int getNombreTotalTerrains() {
    int nombreTotalTerrains = 0;
    try {
        String req = "SELECT COUNT(*) AS total FROM `terrain`";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        if (rs.next()) {
            nombreTotalTerrains = rs.getInt("total");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return nombreTotalTerrains;
}

public double getSuperficieTotale() {
    double superficieTotale = 0.0;
    try {
        String req = "SELECT SUM(CAST(superficie AS DECIMAL(10,2))) AS totalSuperficie FROM `terrain`";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        if (rs.next()) {
            superficieTotale = rs.getDouble("totalSuperficie");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return superficieTotale;
}

    @Override
    public Terrain create(Terrain t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Terrain getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Terrain> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Terrain t) {
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
    public Terrain getOne(String categ_tra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Terrain getPneById(int id) throws SQLException {
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
