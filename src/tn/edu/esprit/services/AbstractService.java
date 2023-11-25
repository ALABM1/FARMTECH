package tn.edu.esprit.services;

import tn.edu.esprit.tools.DataSource;

import java.sql.Connection;

abstract class AbstractService<T> implements GenericService<T> {
    protected Connection cnx;

    public AbstractService() {
        this.cnx = DataSource.getInstance().getConnection();
    }
}
