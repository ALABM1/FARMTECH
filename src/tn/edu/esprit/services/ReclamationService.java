/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.Reclamations;

/**
 *
 * @author Houssem HENCHIR
 */



public class ReclamationService extends AbstractService<Reclamations> {

    public ReclamationService() {
        super();
    }

    @Override
    public void create(Reclamations entity) {
        String query = "INSERT INTO reclamations (type, description, email, telephone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setString(1, entity.getType());
            ps.setString(2, entity.getDescription());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getTelephone());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error while inserting reclamation: " + ex.getMessage());
        }
    }

    @Override
    public Reclamations getById(int id) {
        String query = "SELECT * FROM reclamations WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToReclamations(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while fetching reclamation by ID: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Reclamations> getAll() {
        List<Reclamations> reclamations = new ArrayList<>();
        String query = "SELECT * FROM reclamations";
        try (PreparedStatement ps = cnx.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                reclamations.add(mapResultSetToReclamations(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Error while fetching all reclamations: " + ex.getMessage());
        }
        return reclamations;
    }

    @Override
    public void update(Reclamations entity) {
        String query = "UPDATE reclamations SET type = ?, description = ?, email = ?, telephone = ? WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setString(1, entity.getType());
            ps.setString(2, entity.getDescription());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getTelephone());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error while updating reclamation: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM reclamations WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error while deleting reclamation: " + ex.getMessage());
        }
    }

    public List<Reclamations> searchReclamations(String searchText) {
        List<Reclamations> matchingReclamations = new ArrayList<>();
        String query = "SELECT * FROM reclamations WHERE description LIKE ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setString(1, "%" + searchText + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    matchingReclamations.add(mapResultSetToReclamations(rs));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error searching reclamations: " + ex.getMessage());
        }
        return matchingReclamations;
    }

    private Reclamations mapResultSetToReclamations(ResultSet rs) throws SQLException {
        Reclamations reclamation = new Reclamations();
        reclamation.setId(rs.getInt("id"));
        reclamation.setType(rs.getString("type"));
        reclamation.setDescription(rs.getString("description"));
        reclamation.setEmail(rs.getString("email"));
        reclamation.setTelephone(rs.getString("telephone"));
        return reclamation;
    }
}
