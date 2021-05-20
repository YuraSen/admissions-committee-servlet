package com.senin.admissions_committee_servlet.DAO.mysql;

import com.senin.admissions_committee_servlet.DAO.AdmissionRequestDAO;
import com.senin.admissions_committee_servlet.DAO.ApplicantDAO;
import com.senin.admissions_committee_servlet.DAO.DAOFactory;
import com.senin.admissions_committee_servlet.DAO.FacultyDAO;

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

