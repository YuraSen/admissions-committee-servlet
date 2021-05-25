package com.senin.demo.model.DAO;

import com.senin.demo.model.entity.Applicant;
import com.senin.demo.model.entity.ApplicantProfile;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface ApplicantDAO {
    void insertApplicant(Applicant applicant, ApplicantProfile applicantProfile) throws SQLException;

    boolean deleteApplicant();

    Applicant findApplicantById(int id);

    Applicant findApplicantByUsername(String username);

    boolean updateApplicant();

    void updateApplicantProfile(ApplicantProfile applicantProfile) throws SQLException;

    Collection<Applicant> getAllApplicantTO();

    Optional<ApplicantProfile> getApplicantProfile(Applicant applicant);
}
