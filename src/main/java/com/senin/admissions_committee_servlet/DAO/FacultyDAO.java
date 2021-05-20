package com.senin.admissions_committee_servlet.DAO;

import com.senin.admissions_committee_servlet.entity.Faculty;

import javax.sql.RowSet;
import java.util.Collection;

public interface FacultyDAO {

    public int createFaculty();

    public boolean deleteFaculty();

    public Faculty findFaculty(int id);

    public boolean updateFaculty();

    public RowSet selectFacultyRS();

    public Collection<Faculty> getAllFacultiesTO();

}
