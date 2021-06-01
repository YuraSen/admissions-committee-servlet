package com.senin.demo.model.DAO;

import com.senin.demo.model.entity.Applicant;
import com.senin.demo.model.entity.ApplicantProfile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ApplicantDAO extends GenericDao<Applicant> {

    void insertApplicant(Applicant applicant, ApplicantProfile applicantProfile) throws SQLException;

    boolean updateApplicant(String role, String applicantStatus, Long id) throws SQLException;

    Optional<Applicant> findApplicantById(Long id) throws SQLException;

    Optional<Applicant> findApplicantByUsername(String username) throws SQLException;


    void updateApplicantProfile(ApplicantProfile applicantProfile) throws SQLException;

    Optional<ApplicantProfile> getApplicantProfile(Applicant applicant) throws SQLException;

    Optional<ApplicantProfile> getApplicantProfileById(Long id) throws SQLException;

}
