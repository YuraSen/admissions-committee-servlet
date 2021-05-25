package com.senin.demo.model.DAO;

import com.senin.demo.model.entity.Faculty;

import java.sql.SQLException;
import java.util.List;

public interface FacultyDAO {

    int createFaculty();

    boolean deleteFaculty();

    Faculty findFaculty(Long id);

    boolean updateFaculty();

    public List<Faculty> getAllFacultiesTO()throws SQLException;

    boolean changeAdmissionOpenStatus(String action, Long facultyId);

}
