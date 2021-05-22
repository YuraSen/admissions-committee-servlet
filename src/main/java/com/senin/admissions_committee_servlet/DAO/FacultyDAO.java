package com.senin.admissions_committee_servlet.DAO;

import com.senin.admissions_committee_servlet.entity.Faculty;

import javax.sql.RowSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface FacultyDAO {

    int createFaculty();

    boolean deleteFaculty();

    Faculty findFaculty(Long id);

    boolean updateFaculty();

    public List<Faculty> getAllFacultiesTO()throws SQLException;

    boolean changeAdmissionOpenStatus(String action, Long facultyId);

}
