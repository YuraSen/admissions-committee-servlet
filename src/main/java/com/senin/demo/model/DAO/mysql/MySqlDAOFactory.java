package com.senin.demo.model.DAO.mysql;

import com.senin.demo.model.DAO.AdmissionRequestDAO;
import com.senin.demo.model.DAO.ApplicantDAO;
import com.senin.demo.model.DAO.DAOFactory;
import com.senin.demo.model.DAO.FacultyDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDAOFactory extends DAOFactory {
    private static MySqlDAOFactory instance;
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private MySqlDAOFactory() {
        super();
    }

    public static synchronized MySqlDAOFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDAOFactory();
        }

        return instance;
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ApplicantDAO getApplicantDAO() {
        return new MySqlApplicantDAO(getConnection());
    }

    @Override
    public FacultyDAO getFacultyDAO() {
        return new MySqlFacultyDAO(getConnection());
    }

    @Override
    public AdmissionRequestDAO getAdmissionRequestDAO() {
        return new MySqlAdmissionRequestDAO(getConnection());
    }
}

