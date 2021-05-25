package com.senin.demo.model.DAO.mysql;

import com.senin.demo.model.DAO.ApplicantDAO;
import com.senin.demo.model.DAO.mapper.ApplicantMapper;
import com.senin.demo.model.DAO.mapper.ApplicantProfileMapper;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.model.entity.ApplicantProfile;
import com.senin.demo.model.entity.ApplicantStatus;
import com.senin.demo.model.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MySqlApplicantDAO implements ApplicantDAO {
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
                    "INSERT INTO applicant (username,password,role,applicant_status) Values(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

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

    @Override
    public Optional<Applicant> findApplicantById(Long id) throws SQLException {
        Optional<Applicant> applicant = Optional.empty();
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement("SELECT c.id, applicant_status, password, role, username," +
                     " cp.id, address, city, email, first_name, last_name, phone_number, region, school, applicant_id " +
                     "FROM applicant c  left join applicant_profile cp on c.id = cp.applicant_id WHERE c.id =?")) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                ApplicantMapper applicantMapper = new ApplicantMapper();
                ApplicantProfileMapper applicantProfileMapper = new ApplicantProfileMapper();
                while (rs.next()) {
                    applicant = applicantMapper.extractFromResultSetOpt(rs);
                    ApplicantProfile applicantProfile = applicantProfileMapper.extractFromResultSet(rs);
                    applicant.ifPresent(c -> c.setApplicantProfile(applicantProfile));
                }

            } catch (SQLException ex) {
                throw new SQLException("Cannot get  applicant with id: !" + id, ex);
            }
        } catch (SQLException exp) {
            throw new SQLException("Cannot get  applicant with id: !" + id, exp);
        }
        return applicant;
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

    public Applicant findApplicantById(int id) {
        return null;
    }

    @Override
    public Optional<Applicant> findApplicantByUsername(String username) throws SQLException {
        Optional<Applicant> applicant = Optional.empty();
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT c.id, c.applicant_status, c.password, c.role, c.username " +
                             "FROM  applicant c " +
                             " WHERE username = ?;")) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                ApplicantMapper applicantMapper = new ApplicantMapper();
                if (rs.next())
                    applicant = Optional.ofNullable(applicantMapper.extractFromResultSet(rs));

            } catch (SQLException ex) {
                throw new SQLException("Cannot find  applicant with username: !" + username, ex);
            }
        } catch (SQLException exp) {
            throw new SQLException("Cannot find  applicant with username: !" + username, exp);
        }
        return applicant;
    }

    @Override
    public boolean updateApplicant(String role, String applicantStatus, Long id) throws SQLException {
        String sql = " UPDATE  applicant " +
                "SET role=?,applicant_status=? " +
                " WHERE id=?;";


        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, role);
            pstmt.setString(2, applicantStatus);
            pstmt.setLong(3, id);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Cannot update  applicant with id: " + id, e);
        }
    }

    @Override
    public List<Applicant> getAllApplicantTO() throws SQLException {
        List<Applicant> listApplicants = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT c.id, applicant_status, password, role, username," +
                     " cp.id, address, city, email, first_name, last_name, phone_number, region, school, applicant_id " +
                     "FROM applicant c left join applicant_profile cp on c.id = cp.applicant_id")) {
            ApplicantMapper applicantMapper = new ApplicantMapper();
            ApplicantProfileMapper applicantProfileMapper = new ApplicantProfileMapper();
            while (rs.next()) {
                Applicant applicant = applicantMapper.extractFromResultSet(rs);
                ApplicantProfile applicantProfile = applicantProfileMapper.extractFromResultSet(rs);
                applicant.setApplicantProfile(applicantProfile);
                listApplicants.add(applicant);
            }
        } catch (SQLException ex) {
            throw new SQLException("Cannot get all applicants!", ex);

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
    public Optional<ApplicantProfile> getApplicantProfile(Applicant applicant) throws SQLException {
        Optional<ApplicantProfile> result = Optional.empty();
        try (Connection con = connection;
             PreparedStatement ps = con.prepareCall("SELECT cp.id, address, city, email, first_name, last_name, phone_number, region, school, applicant_id From applicant_profile cp Where applicant_id=?")) {
            ps.setLong(1, applicant.getId());
            try (ResultSet rs = ps.executeQuery()) {
                ApplicantProfileMapper mapper = new ApplicantProfileMapper();
                if (rs.next()) {
                    result = Optional.of(mapper.extractFromResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return result;
    }

    @Override
    public void updateApplicantProfile(ApplicantProfile applicantProfile) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = connection;
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = conn.prepareStatement(
                    "UPDATE applicant_profile SET first_name=?,last_name=?,email=?,address=?,city=?,region=?,school=?,phone_number=? " +
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

    @Override
    public void create(Applicant entity) throws SQLException {

    }

    @Override
    public Applicant findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Applicant> findAll() throws SQLException {
        return null;
    }

    @Override
    public void update(Applicant entity) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void close() throws SQLException {

    }
}
