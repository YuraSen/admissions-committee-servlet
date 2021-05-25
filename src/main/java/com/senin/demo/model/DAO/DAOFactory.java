package com.senin.demo.model.DAO;

import com.senin.demo.model.DAO.mysql.MySqlDAOFactory;

public abstract class DAOFactory {

    public static final int MYSQL = 1;

    public static DAOFactory getDAOFactory(int whichFactory) {
        if (whichFactory == MYSQL) {
            return MySqlDAOFactory.getInstance();
        }
        throw new RuntimeException("Unknown factory");
    }

    public abstract ApplicantDAO getApplicantDAO();

    public abstract FacultyDAO getFacultyDAO();

    public abstract AdmissionRequestDAO getAdmissionRequestDAO();


}