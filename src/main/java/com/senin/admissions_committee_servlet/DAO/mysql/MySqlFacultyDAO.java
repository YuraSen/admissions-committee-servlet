package com.senin.admissions_committee_servlet.DAO.mysql;

import com.senin.admissions_committee_servlet.DAO.FacultyDAO;
import com.senin.admissions_committee_servlet.DAO.mapper.AdmissionRequestMapper;
import com.senin.admissions_committee_servlet.DAO.mapper.ApplicantMapper;
import com.senin.admissions_committee_servlet.DAO.mapper.ApplicantProfileMapper;
import com.senin.admissions_committee_servlet.DAO.mapper.FacultyMapper;
import com.senin.admissions_committee_servlet.entity.AdmissionRequest;
import com.senin.admissions_committee_servlet.entity.Applicant;
import com.senin.admissions_committee_servlet.entity.ApplicantProfile;
import com.senin.admissions_committee_servlet.entity.Faculty;

import javax.sql.RowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlFacultyDAO implements FacultyDAO {
    private static Connection connection;

    public MySqlFacultyDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int createFaculty() {
        return 0;
    }

    @Override
    public boolean deleteFaculty() {
        return false;
    }

    @Override
    public Faculty findFaculty(Long id) {
        Faculty faculty = null;
        Faculty facultyUnique = null;
        Map<Long, Faculty> facultyMap = new HashMap<>();

        String sql = "SELECT  " +
                "f.id, f.budget_capacity, f.description, f.name, f.req_subject1, f.req_subject2, f.req_subject3, f.total_capacity, f.admission_open," +
                "admission_request.id, admission_request.status, admission_request.creation_date_time, admission_request.req_subject1_grade, admission_request.req_subject2_grade, admission_request.req_subject3_grade, admission_request.applicant_id, admission_request.faculty_id," +
                "c.id, c.applicant_status, c.password, c.role, c.username," +
                "cp.id, cp.address, cp.city, cp.email, cp.first_name, cp.last_name, cp.phone_number, cp.region, cp.school, cp.applicant_id " +
                "FROM faculty f " +
                "Join admission_request  on f.id = admission_request.faculty_id " +
                "Join applicant c on admission_request.applicant_id = c.id  " +
                "Join applicant_profile cp on c.id = cp.applicant_id" +
                " WHERE f.id=?";
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
                FacultyMapper facultyMapper = new FacultyMapper();
                ApplicantMapper applicantMapper = new ApplicantMapper();
                ApplicantProfileMapper applicantProfileMapper = new ApplicantProfileMapper();
                while (rs.next()) {
                    AdmissionRequest admissionRequest = admissionRequestMapper.extractFromResultSet(rs);
                    Applicant applicant = applicantMapper.extractFromResultSet(rs);
                    ApplicantProfile applicantProfile = applicantProfileMapper.extractFromResultSet(rs);
                    applicant.setApplicantProfile(applicantProfile);
                    admissionRequest.setApplicant(applicant);
                    faculty = facultyMapper.extractFromResultSet(rs);
                    facultyUnique = facultyMapper.makeUnique(facultyMap, faculty);
                    facultyUnique.getAdmissionRequestList().add(admissionRequest);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultyUnique;
    }

    @Override
    public boolean updateFaculty() {
        return false;
    }

    @Override
    public RowSet selectFacultyRS() {
        return null;
    }

    @Override
    public List<Faculty> getAllFacultiesTO() throws SQLException{
        Map<Long, Faculty> faculties = new HashMap<>();
        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * From faculty f JOIN admission_request on f.id = admission_request.faculty_id")) {
            FacultyMapper facultyMapper = new FacultyMapper();
            AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
            while (rs.next()) {
                AdmissionRequest admissionRequest = admissionRequestMapper.extractFromResultSet(rs);
                Faculty faculty = facultyMapper.extractFromResultSet(rs);
                Faculty uniqueFaculty = facultyMapper.makeUnique(faculties, faculty);
                uniqueFaculty.getAdmissionRequestList().add(admissionRequest);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("Cannot get all faculties!", throwables);
        }
        return new ArrayList<Faculty>(faculties.values());
    }
}
