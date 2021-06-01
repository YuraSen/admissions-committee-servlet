package com.senin.demo.model.DAO.mapper;

import com.senin.demo.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class AdmissionRequestMapper implements ObjectMapper<AdmissionRequest> {
    public Optional<AdmissionRequest> getAdmissionRequest(ResultSet rs) throws SQLException {

        FacultyMapper facultyMapper = new FacultyMapper();
        ApplicantMapper applicantMapper = new ApplicantMapper();
        ApplicantProfileMapper applicantProfileMapper = new ApplicantProfileMapper();

        Optional<AdmissionRequest> admissionRequest = Optional.of(extractFromResultSet(rs));
        Optional<Faculty> faculty = Optional.of(facultyMapper.extractFromResultSet(rs));
        Optional<Applicant> applicant = Optional.of(applicantMapper.extractFromResultSet(rs));
        Optional<ApplicantProfile> applicantProfile = Optional.of(applicantProfileMapper.extractFromResultSet(rs));
        applicant.ifPresent(c -> c.setApplicantProfile(applicantProfile.get()));
        admissionRequest.ifPresent(ar -> {
            ar.setApplicant(applicant.get());
            ar.setFaculty(faculty.get());
        });
        return admissionRequest;
    }

    @Override
    public AdmissionRequest makeUnique(Map<Long, AdmissionRequest> cache, AdmissionRequest entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }

    public AdmissionRequest extractFromResultSet(ResultSet rs) throws SQLException {
        AdmissionRequest admissionRequest = new AdmissionRequest();
        admissionRequest.setId(rs.getLong("admission_request.id"));
        admissionRequest.setAdmissionRequestStatus(AdmissionRequestStatus.getAdmissionRequestStatus(rs.getInt("admission_request.status")));
        admissionRequest.setRequiredSubject1Grade(rs.getInt("req_subject1_grade"));
        admissionRequest.setRequiredSubject2Grade(rs.getInt("req_subject2_grade"));
        admissionRequest.setRequiredSubject3Grade(rs.getInt("req_subject3_grade"));
        Timestamp timestamp = rs.getTimestamp("creation_date_time");
        if (Objects.nonNull(timestamp)) {
            admissionRequest.setCreationDateTime(timestamp.toLocalDateTime());
        }
        return admissionRequest;
    }
}