package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.exception.FacultyNotFoundException;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditFacultyFormCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(EditFacultyFormCommand.class);
    private final FacultyService facultyService;

    public EditFacultyFormCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        Faculty faculty;
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        try {
            faculty = faculty = facultyService.findById(facultyId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while searching for faculty : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        } catch (FacultyNotFoundException ex) {
            LOG.error("Can not find faculty: {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        request.setAttribute("faculty", faculty);

        return "WEB-INF\\jsp\\admin\\adminEditFaculty.jsp";
    }
}
