/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

/**
 *
 * @author Wael.Ibnezzine
 */
import tn.edu.esprit.entities.Message;
import tn.edu.esprit.entities.User;

import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;



public class MessageService extends AbstractService<Message> {

    public MessageService() {
        super();
    }

    @Override
    public void create(Message entity) {
        String query = "INSERT INTO messages (text, destinataire, source, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setString(1, entity.getText());
            ps.setInt(2, entity.getDestinataire().getId());
            ps.setInt(3, entity.getSource().getId());
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(entity.getDate());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(4, sqlDate);
            ps.executeUpdate();
        } catch (SQLException | ParseException ex) {
            System.out.println("Error while inserting message: " + ex.getMessage());
        }
    }

    @Override
    public Message getById(int id) {
        String query = "SELECT m.*, d.nom as destinataire_name,d.prenom as destinataire_prenom, s.nom as source_name, s.prenom as source_prenom FROM messages m " +
                "JOIN users d ON m.destinataire = d.id " +
                "JOIN users s ON m.source = s.id WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMessage(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while fetching message by ID: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT m.*, d.nom as destinataire_name,d.prenom as destinataire_prenom, s.nom as source_name, s.prenom as source_prenom FROM messages m " +
                "JOIN users d ON m.destinataire = d.id " +
                "JOIN users s ON m.source = s.id";
        try (PreparedStatement ps = cnx.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                messages.add(mapResultSetToMessage(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Error while fetching all messages: " + ex.getMessage());
        }
        return messages;
    }

    @Override
    public void update(Message entity) {
        String query = "UPDATE messages SET text = ?, destinataire = ?, source = ?, date = ? WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setString(1, entity.getText());
            ps.setInt(2, entity.getDestinataire().getId());
            ps.setInt(3, entity.getSource().getId());
            ps.setString(4, entity.getDate());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error while updating message: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM messages WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error while deleting message: " + ex.getMessage());
        }
    }

    public List<Message> searchMessages(String searchText) {
        List<Message> matchingMessages = new ArrayList<>();
        String query = "SELECT m.*, d.nom as destinataire_name,d.prenom as destinataire_prenom, s.nom as source_name, s.prenom as source_prenom FROM messages m " +
                "JOIN users d ON m.destinataire = d.id " +
                "JOIN users s ON m.source = s.id WHERE text LIKE ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setString(1, "%" + searchText + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    matchingMessages.add(mapResultSetToMessage(rs));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error searching messages: " + ex.getMessage());
        }
        return matchingMessages;
    }


    private Message mapResultSetToMessage(ResultSet rs) throws SQLException {
        Message message = new Message();
        message.setId(rs.getInt("id"));
        message.setText(rs.getString("text"));

        User destinataire = new User();
        destinataire.setNom(rs.getString("destinataire_name"));
        destinataire.setPrenom(rs.getString("destinataire_prenom"));
        message.setDestinataire(destinataire);

        User source = new User();
        source.setNom(rs.getString("source_name"));
        source.setPrenom(rs.getString("source_prenom"));
        message.setSource(source);

        message.setDate(rs.getString("date"));
        return message;
    }
}


