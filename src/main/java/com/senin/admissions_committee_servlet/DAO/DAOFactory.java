package com.senin.admissions_committee_servlet.DAO;

import com.senin.admissions_committee_servlet.DAO.mysql.MySqlDAOFactory;

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