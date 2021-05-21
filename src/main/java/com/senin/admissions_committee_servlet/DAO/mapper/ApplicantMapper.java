package com.senin.admissions_committee_servlet.DAO.mapper;

import com.senin.admissions_committee_servlet.entity.Applicant;
import com.senin.admissions_committee_servlet.entity.ApplicantStatus;
import com.senin.admissions_committee_servlet.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ApplicantMapper implements ObjectMapper<Applicant> {
    @Override
    public Applicant extractFromResultSet(ResultSet rs) throws SQLException {
        Applicant applicant = new Applicant();
        applicant.setId(rs.getLong("id"));
        applicant.setUsername(rs.getString("username"));
        applicant.setPassword(rs.getString("password"));
        applicant.setRole(Role.valueOf(rs.getString("role")));
        applicant.setApplicantStatus(ApplicantStatus.valueOf(rs.getString("applicant_status")));
        return applicant;
    }

    @Override
    public Applicant makeUnique(Map<Long, Applicant> cache, Applicant entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}