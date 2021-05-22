package com.senin.admissions_committee_servlet.DAO.mysql;

import com.senin.admissions_committee_servlet.DAO.AdmissionRequestDAO;
import com.senin.admissions_committee_servlet.DAO.mapper.AdmissionRequestMapper;
import com.senin.admissions_committee_servlet.DAO.mapper.ApplicantProfileMapper;
import com.senin.admissions_committee_servlet.DAO.mapper.ApplicantMapper;
import com.senin.admissions_committee_servlet.DAO.mapper.FacultyMapper;
import com.senin.admissions_committee_servlet.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MySqlAdmissionRequestDAO implements AdmissionRequestDAO {

    private static Connection connection;

    public MySqlAdmissionRequestDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int saveAdmissionRequest(AdmissionRequest admissionRequest) {

        String sql = "INSERT INTO admission_request " +
                "(faculty_id,applicant_id,req_subject1_grade,req_subject2_grade,req_subject3_grade,status)" +
                "Values(?,?,?,?,?,?);";
        try (Connection con = connection;
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setLong(1, admissionRequest.getFacultyId());
            preparedStatement.setLong(2, admissionRequest.getApplicantId());
            preparedStatement.setInt(3, admissionRequest.getRequiredSubject1Grade());
            preparedStatement.setInt(4, admissionRequest.getRequiredSubject2Grade());
            preparedStatement.setInt(5, admissionRequest.getRequiredSubject3Grade());
            preparedStatement.setInt(6, admissionRequest.getAdmissionRequestStatus().ordinal());
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<AdmissionRequest> selectAdmissionRequestsForApplicantWithId(Long id) {

        List<AdmissionRequest> admissionRequestList = new ArrayList<>();

        String sql = "SELECT " +
                "cp.id, cp.address, cp.city, cp.email, cp.first_name, cp.last_name, cp.phone_number, cp.region, cp.school, cp.applicant_id, " +
                "f.id, budget_capacity, description, name, req_subject1, req_subject2, req_subject3, total_capacity, admission_open," +
                "admission_request.id, status, creation_date_time, req_subject1_grade, req_subject2_grade, req_subject3_grade,admission_request.applicant_id,faculty_id," +
                " c.id,c.username,c.password,c.role,c.applicant_status " +
                "FROM admission_request " +
                "JOIN applicant c on admission_request.applicant_id=c.id " +
                "JOIN  applicant_profile cp on admission_request.applicant_id = cp.applicant_id " +
                "JOIN faculty f on admission_request.faculty_id = f.id WHERE admission_request.applicant_id=?;";
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
                FacultyMapper facultyMapper = new FacultyMapper();
                ApplicantMapper applicantMapper = new ApplicantMapper();
                ApplicantProfileMapper applicantProfileMapper =  new ApplicantProfileMapper();
                while (rs.next()) {
                    AdmissionRequest admissionRequest = admissionRequestMapper.extractFromResultSet(rs);
                    Faculty faculty =  facultyMapper.extractFromResultSet(rs);
                    Applicant applicant = applicantMapper.extractFromResultSet(rs);
                    ApplicantProfile applicantProfile = applicantProfileMapper.extractFromResultSet(rs);
                    applicant.setApplicantProfile(applicantProfile);
                    admissionRequest.setApplicant(applicant);
                    admissionRequest.setFaculty(faculty);
                    admissionRequestList.add(admissionRequest);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admissionRequestList;
    }

    @Override
    public List<AdmissionRequest> selectAdmissionRequestsForFacultyWithId(Long id) {
        return null;
    }

    @Override
    public boolean changeAdmissionRequestStatus(Long id, AdmissionRequestStatus status) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteAdmissionRequest(Long id) throws SQLException {
        boolean res = false;
        try (Connection conn = connection;
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE  FROM admission_request WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            res = preparedStatement.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new SQLException("Cannot delete a admission request with id:" + id, ex);
        }
        return res;
    }

    @Override
    public Optional<AdmissionRequest> findAdmissionRequest(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean updateAdmissionRequest() {
        return false;
    }


    @Override
    public List<AdmissionRequest> selectAdmissionRequests() throws SQLException {
        List<AdmissionRequest> admissionRequests = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_ADMISSION_REQUESTS)) {
            AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
            while (rs.next()) {
                admissionRequests.add(admissionRequestMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {

            throw new SQLException("Cannot get all admission requests!", ex);
        }

        return admissionRequests;
    }
}