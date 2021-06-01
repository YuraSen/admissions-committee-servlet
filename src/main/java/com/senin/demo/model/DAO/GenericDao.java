package com.senin.demo.model.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    void create (T entity) throws SQLException;

    Optional<T> findById(Long id)throws SQLException;

    List<T> findAll()throws SQLException;

    void update(T entity) throws SQLException;

    void delete(Long id) throws SQLException;

    void close()throws SQLException;
}
