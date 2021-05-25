package com.senin.demo.model.DAO.mapper;

import com.senin.demo.model.entity.Applicant;
import com.senin.demo.model.entity.ApplicantStatus;
import com.senin.demo.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

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

    public Optional<Applicant> extractFromResultSetOpt(ResultSet rs) throws SQLException {
        Applicant applicant = new Applicant();
        applicant.setId(rs.getLong("c.id"));
        applicant.setUsername(rs.getString("username"));
        applicant.setPassword(rs.getString("password"));
        applicant.setRole(Role.valueOf(rs.getString("role")));
        applicant.setApplicantStatus(ApplicantStatus.valueOf(rs.getString("applicant_status")));
        return Optional.of(applicant);
    }
}
