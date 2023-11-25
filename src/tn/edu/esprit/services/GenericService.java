package tn.edu.esprit.services;

import java.util.List;

public interface GenericService<T> {
    void create(T entity);
    T getById(int id);
    List<T> getAll();
    void update(T entity);
    void delete(int id);
}