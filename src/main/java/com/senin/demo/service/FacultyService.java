package com.senin.demo.service;

import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.exception.FacultyNotFoundException;
import com.senin.demo.model.DAO.DAOFactory;
import com.senin.demo.model.DAO.FacultyDAO;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.utils.util.FacultyPage;

import java.sql.SQLException;
import java.util.List;


public class FacultyService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    public List<Faculty> findAll() {
        try (FacultyDAO dao = daoFactory.getFacultyDAO()) {
            return dao.findAll();
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void changeAdmissionOpenStatus(String action, Long facultyId) {
        try (FacultyDAO dao = daoFactory.getFacultyDAO()) {
            dao.changeAdmissionOpenStatus(action, facultyId);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }


    public void create(Faculty faculty) {
        try (FacultyDAO dao = daoFactory.getFacultyDAO()) {
            dao.create(faculty);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public Faculty findById(Long id) {
        try (FacultyDAO dao = daoFactory.getFacultyDAO()) {
            return dao.findById(id)
                    .orElseThrow(() -> new FacultyNotFoundException("Faculty not found!"));
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void update(Faculty faculty) {

        try (FacultyDAO dao = daoFactory.getFacultyDAO()) {
            dao.update(faculty);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void delete(Long id) {
        try (FacultyDAO dao = daoFactory.getFacultyDAO()) {
            dao.delete(id);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public FacultyPage findAllSorted(String name, String direction, int page, int itemsPerPage) {
        try (FacultyDAO dao = daoFactory.getFacultyDAO()) {
            return dao.findAllSorted(name, direction, page, itemsPerPage);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not get sorted list");
        }
    }
}
