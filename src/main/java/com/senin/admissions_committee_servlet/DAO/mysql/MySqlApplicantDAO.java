package com.senin.admissions_committee_servlet.DAO.mysql;

import com.senin.admissions_committee_servlet.DAO.ApplicantDAO;
import com.senin.admissions_committee_servlet.DAO.mapper.ApplicantMapper;
import com.senin.admissions_committee_servlet.DAO.mapper.ApplicantProfileMapper;
import com.senin.admissions_committee_servlet.entity.Applicant;
import com.senin.admissions_committee_servlet.entity.ApplicantProfile;
import com.senin.admissions_committee_servlet.entity.ApplicantStatus;
import com.senin.admissions_committee_servlet.entity.Role;

import javax.sql.RowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MySqlApplicantDAO implements ApplicantDAO {

    private static MySqlApplicantDAO instance;
    private static Connection connection;

    public MySqlApplicantDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertApplicant(Applicant applicant, ApplicantProfile applicantProfile) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Long applicantId = null;
        try {
            conn = connection;
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            pstmt = conn.prepareStatement(
                    "INSERT INTO applicant (username,password,role,status) Values(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, applicant.getUsername());
            pstmt.setString(2, applicant.getPassword());
            pstmt.setString(3, applicant.getRole().getName());
            pstmt.setString(4, applicant.getApplicantStatus().name());
            applicantId = getGeneratedKey(pstmt);
//            pstmt.executeUpdate();


            pstmt = conn.prepareStatement(
                    "INSERT INTO applicant_profile(first_name,last_name,email,address,city,region,school,phone_number,applicant_id)" +
                            " Values(?,?,?,?,?,?,?,?,?);");

            pstmt.setString(1, applicantProfile.getFirstName());
            pstmt.setString(2, applicantProfile.getLastName());
            pstmt.setString(3, applicantProfile.getEmail());
            pstmt.setString(4, applicantProfile.getAddress());
            pstmt.setString(5, applicantProfile.getCity());
            pstmt.setString(6, applicantProfile.getRegion());
            pstmt.setString(7, applicantProfile.getSchool());
            pstmt.setString(8, applicantProfile.getPhoneNumber());
            pstmt.setLong(9, applicantId);
            pstmt.execute();
            conn.commit();

        } catch (SQLException ex) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException exp) {
                throw new SQLException("Can not make rollback of transaction ", exp);
            }
            throw new SQLException("Can not make  transaction" + ex.getMessage(), ex);
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException ex) {

            }
        }

    }

    private Long getGeneratedKey(PreparedStatement pstmt) throws SQLException {
        ResultSet rs = null;
        try {
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Can not return generated keys!", ex);
        }

        return -1L;
    }

    @Override
    public boolean deleteApplicant() {
        return false;
    }

    public Applicant findApplicantById(int id) {
        return null;
    }

    @Override
    public Applicant findApplicantByUsername(String username) {
        Applicant applicant = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = connection;
            pstmt = con.prepareStatement(Constants.SQL_FIND_APPLICANT_BY_USERNAME);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            ApplicantMapper applicantMapper = new ApplicantMapper();
            if (rs.next())
                applicant = applicantMapper.extractFromResultSet(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {
            try {
                rs.close();

                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return applicant;
    }

    public boolean updateApplicant() {
        return false;
    }

    public RowSet selectApplicantRS() {
        return null;
    }

    @Override
    public List<Applicant> getAllApplicantTO() {
        List<Applicant> listApplicants = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_APPLICANTS)) {
            ApplicantMapper applicantMapper = new ApplicantMapper();
            while (rs.next()) {
                listApplicants.add(applicantMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listApplicants;

    }

    private Applicant mapApplicant(ResultSet rs) throws SQLException {
        Applicant applicant = new Applicant();
        applicant.setId(rs.getLong("id"));
        applicant.setUsername(rs.getString("username"));
        applicant.setPassword(rs.getString("password"));
        applicant.setRole(Role.valueOf(rs.getString("role")));
        applicant.setApplicantStatus(ApplicantStatus.valueOf(rs.getString("status")));
        return applicant;
    }

    @Override
    public Optional<ApplicantProfile> getApplicantProfile(Applicant applicant) {
        Optional<ApplicantProfile> result = Optional.empty();
        try (PreparedStatement ps = connection.prepareCall("SELECT * From applicant_profile Where applicant_id=?")) {
            ps.setLong(1, applicant.getId());
            ResultSet rs;
            rs = ps.executeQuery();
            ApplicantProfileMapper mapper = new ApplicantProfileMapper();
            if (rs.next()) {
                result = Optional.of(mapper.extractFromResultSet(rs));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

    @Override
    public void updateApplicantProfile(ApplicantProfile applicantProfile) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Long applicantId = null;
        try {
            conn = connection;
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = conn.prepareStatement(
                    "UPDATE candidate_profile SET first_name=?,last_name=?,email=?,address=?,city=?,region=?,school=?,phone_number=? " +
                            "WHERE id=?");
            pstmt.setString(1, applicantProfile.getFirstName());
            pstmt.setString(2, applicantProfile.getLastName());
            pstmt.setString(3, applicantProfile.getEmail());
            pstmt.setString(4, applicantProfile.getAddress());
            pstmt.setString(5, applicantProfile.getCity());
            pstmt.setString(6, applicantProfile.getRegion());
            pstmt.setString(7, applicantProfile.getSchool());
            pstmt.setString(8, applicantProfile.getPhoneNumber());
            pstmt.setLong(9, applicantProfile.getId());
            pstmt.execute();
            conn.commit();

        } catch (SQLException ex) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException exp) {
                throw new SQLException("Can not make rollback of transaction ", exp);
            }
            throw new SQLException("Can not make  transaction" + ex.getMessage(), ex);
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException ex) {

            }
        }
    }
}
