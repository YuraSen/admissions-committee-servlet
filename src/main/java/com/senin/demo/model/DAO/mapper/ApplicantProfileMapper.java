package com.senin.demo.model.DAO.mapper;

import com.senin.demo.model.entity.ApplicantProfile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ApplicantProfileMapper implements ObjectMapper<ApplicantProfile> {

    @Override
    public ApplicantProfile extractFromResultSet(ResultSet rs) throws SQLException {
        ApplicantProfile applicantProfile = new ApplicantProfile();
        applicantProfile.setId(rs.getLong("cp.id"));
        applicantProfile.setFirstName(rs.getString("first_name"));
        applicantProfile.setLastName(rs.getString("last_name"));
        applicantProfile.setEmail(rs.getString("email"));
        applicantProfile.setAddress(rs.getString("address"));
        applicantProfile.setCity(rs.getString("city"));
        applicantProfile.setRegion(rs.getString("region"));
        applicantProfile.setSchool(rs.getString("school"));
        applicantProfile.setPhoneNumber(rs.getString("phone_number"));
        applicantProfile.setFileName(rs.getString("certificate_file"));

        return applicantProfile;
    }

    @Override
    public ApplicantProfile makeUnique(Map<Long, ApplicantProfile> cache, ApplicantProfile entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
