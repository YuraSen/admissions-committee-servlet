package com.senin.admissions_committee_servlet.DAO;

import com.senin.admissions_committee_servlet.entity.Applicant;
import com.senin.admissions_committee_servlet.entity.ApplicantProfile;

import javax.sql.RowSet;
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
