package com.senin.demo.model.DAO;

import com.senin.demo.model.dto.FacultyListDTO;
import com.senin.demo.model.entity.Faculty;

import java.sql.SQLException;
import java.util.List;

public interface FacultyDAO extends GenericDao<Faculty>{

    boolean changeAdmissionOpenStatus(String action, Long facultyId) throws SQLException;

    FacultyListDTO findAllSorted(String name, String direction, int page, int itemsPerPage) throws SQLException;

}
