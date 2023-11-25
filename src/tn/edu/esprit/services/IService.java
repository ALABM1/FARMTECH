/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.sql.SQLException;
import java.util.List;
import tn.edu.esprit.entities.Transaction;

/**
 *
 * @author abdelazizmezri
 * @param <T>
 */
import java.util.List;

public interface IService<T> {
    public T create(T t);

    public T getById(int id);

    public List<T> getAll();

    public void update(T t);

    public boolean delete(int id);


    public void ajouter(T t);
    public void modifier(T t);

    public void supprimer(int id);
    public T getOne(int id);
    public List<T> getAll(T t);


    //public void supprimer(int id_tra);
    public void rechercheType (int id_tra);
    public List remplircombo ();
    /**
     *
     * @param id_tra
     * @return
     */
   
    public T getOne(String categ_tra);
    //public List<T> getAll();
    public T getPneById(int id) throws SQLException;
    public int caisse ();
    public String chatGPT (String message);
    public int nbligne();
   
}

