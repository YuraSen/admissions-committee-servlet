package com.senin.demo.service;

import com.senin.demo.exception.ApplicantNotFoundException;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.DAO.DAOFactory;
import com.senin.demo.model.entity.Applicant;

import java.sql.SQLException;
import java.util.List;

public class ApplicantService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);


    public List<Applicant> findAll() {

        try {
            return daoFactory.getApplicantDAO().findAll();
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void delete(Long applicantId) {
        try {
            daoFactory.getApplicantDAO().delete(applicantId);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void update(String role, String applicantStatus, Long applicantId)  {
        try {
            daoFactory.getApplicantDAO().updateApplicant(role, applicantStatus, applicantId);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public Applicant findById(Long applicantId) {
        try {
         return  daoFactory.getApplicantDAO().findApplicantById(applicantId).orElseThrow(ApplicantNotFoundException::new);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }
}
