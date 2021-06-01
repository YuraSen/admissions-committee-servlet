package com.senin.demo.model.DAO;

import com.senin.demo.model.entity.Faculty;
import com.senin.demo.utils.util.FacultyPage;

import java.sql.SQLException;

public interface FacultyDAO extends GenericDao<Faculty>{

    boolean changeAdmissionOpenStatus(String action, Long facultyId) throws SQLException;

    FacultyPage findAllSorted(String name, String direction, int page, int itemsPerPage) throws SQLException;

}
