package com.senin.demo.model.DAO.mysql;

import com.senin.demo.model.DAO.AdmissionRequestDAO;
import com.senin.demo.model.DAO.mapper.AdmissionRequestMapper;
import com.senin.demo.model.DAO.mapper.ApplicantMapper;
import com.senin.demo.model.DAO.mapper.ApplicantProfileMapper;
import com.senin.demo.model.DAO.mapper.FacultyMapper;
import com.senin.demo.model.entity.*;

import java.sql.*;
import java.util.ArrayList;
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
    public List<AdmissionRequest> selectAdmissionRequestsForApplicantWithId(Long id) throws SQLException {

        List<AdmissionRequest> admissionRequestList = new ArrayList<>();

        String sql = "SELECT " +
                "cp.id, cp.address, cp.city, cp.email, cp.first_name, cp.last_name, cp.phone_number, cp.region, cp.school,cp.certificate_file, cp.applicant_id, " +
                "f.id, budget_capacity, description_en, name_en,description_uk,name_uk, req_subject1_en,req_subject1_uk, req_subject2_en,req_subject2_uk, req_subject3_en,req_subject3_uk, total_capacity, admission_open," +
                "admission_request.id, admission_request.status, creation_date_time, req_subject1_grade, req_subject2_grade, req_subject3_grade,admission_request.applicant_id,faculty_id," +
                " c.id,c.username,c.password,c.role,c.applicant_status " +
                "FROM admission_request " +
                "Left JOIN applicant c on admission_request.applicant_id=c.id " +
                "Left JOIN  applicant_profile cp on admission_request.applicant_id = cp.applicant_id " +
                "Left JOIN faculty f on admission_request.faculty_id = f.id WHERE admission_request.applicant_id=?;";
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
                    Optional<AdmissionRequest> admissionRequest = admissionRequestMapper.getAdmissionRequest(rs);
                    admissionRequest.ifPresent(admissionRequestList::add);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot find a admission requests for applicant with id: " + id, e);
        }
        return admissionRequestList;
    }

    @Override
    public boolean changeAdmissionRequestStatus(Long id, AdmissionRequestStatus status) throws SQLException {
        boolean res = false;
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement("UPDATE  admission_request SET admission_request.status=? WHERE  id= ?")) {
            pstmt.setInt(1, status.ordinal());
            pstmt.setLong(2, id);
            res = pstmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new SQLException("Cannot delete a admission request with id:" + id, ex);
        }
        return res;
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
    public List<AdmissionRequest> selectAdmissionRequests() throws SQLException {
        List<AdmissionRequest> admissionRequests = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM admission_request")) {
            AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
            while (rs.next()) {
                admissionRequests.add(admissionRequestMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {

            throw new SQLException("Cannot get all admission requests!", ex);
        }

        return admissionRequests;
    }

    @Override
    public void create(AdmissionRequest admissionRequest) throws SQLException {
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO admission_request " +
                     "(faculty_id,applicant_id,req_subject1_grade,req_subject2_grade,req_subject3_grade,admission_request.status)" +
                     "Values(?,?,?,?,?,?);")) {
            pstmt.setLong(1, admissionRequest.getFacultyId());
            pstmt.setLong(2, admissionRequest.getApplicantId());
            pstmt.setInt(3, admissionRequest.getRequiredSubject1Grade());
            pstmt.setInt(4, admissionRequest.getRequiredSubject2Grade());
            pstmt.setInt(5, admissionRequest.getRequiredSubject3Grade());
            pstmt.setInt(6, admissionRequest.getAdmissionRequestStatus().ordinal());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Cannot save admission request !");
        }
    }

    @Override
    public Optional<AdmissionRequest> findById(Long id) throws SQLException {
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement("SELECT cp.id, cp.address, cp.city, cp.email, cp.first_name, cp.last_name, cp.phone_number, cp.region, cp.school,cp.certificate_file, cp.applicant_id, " +
                     "                f.id, budget_capacity, description_en, name_en, name_uk,description_uk, req_subject1_en,req_subject1_uk, req_subject2_en,req_subject2_uk, req_subject3_en,req_subject3_uk, total_capacity, admission_open, " +
                     "                admission_request.id, admission_request.status, creation_date_time, req_subject1_grade, req_subject2_grade, req_subject3_grade,admission_request.applicant_id,faculty_id, " +
                     "                 c.id,c.username,c.password,c.role,c.applicant_status " +
                     "                FROM admission_request  " +
                     "                Left JOIN applicant c on admission_request.applicant_id=c.id " +
                     "                Left JOIN  applicant_profile cp on admission_request.applicant_id = cp.applicant_id " +
                     "                Left JOIN faculty f on admission_request.faculty_id = f.id WHERE admission_request.id=?")) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
                    return admissionRequestMapper.getAdmissionRequest(rs);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot find a admission request with id: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<AdmissionRequest> findAll() throws SQLException {
        List<AdmissionRequest> admissionRequests = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM admission_request")) {
            AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
            while (rs.next()) {
                admissionRequests.add(admissionRequestMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {

            throw new SQLException("Cannot get all admission requests!", ex);
        }
        return admissionRequests;
    }

    @Override
    public void update(AdmissionRequest entity) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement("DELETE  FROM admission_request WHERE  id= ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException("Cannot delete a admission request with id:" + id, ex);
        }
    }

    @Override
    public void close() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}