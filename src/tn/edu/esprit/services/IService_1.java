/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.util.List;

/**
 *
 * @author abdelazizmezri
 * @param <T>
 */
public interface IService_1 <T> {
    public void ajouter(T t);
    public void modifier(T t);
    public void supprimer(int id);
    public T getOne(String nom);
    public List<T> getAll(T t);
}
