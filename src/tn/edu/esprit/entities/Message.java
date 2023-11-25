/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

public class Message implements IEntity {
    private int id;
    private String text;
    private User destinataire;
    private User  source;
    private String date;

    public Message() {
    }

    public Message(String text, User destinataire, User source, String date) {
        this.text = text;
        this.destinataire = destinataire;
        this.source = source;
        this.date = date;
    }

    // Getters et Setters pour les attributs
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
