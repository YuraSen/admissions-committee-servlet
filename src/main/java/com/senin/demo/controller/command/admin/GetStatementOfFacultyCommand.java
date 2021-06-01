package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.exception.FacultyNotFoundException;
import com.senin.demo.model.entity.AdmissionRequest;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetStatementOfFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(GetStatementOfFacultyCommand.class);

    private final FacultyService facultyService;
    private final AdmissionRequestService admissionRequestService;

    public GetStatementOfFacultyCommand(FacultyService facultyService, AdmissionRequestService admissionRequestService) {
        this.facultyService = facultyService;
        this.admissionRequestService = admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Faculty faculty;
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        try {
            faculty = facultyService.findById(facultyId);

        } catch (FacultyNotFoundException ex) {
            LOG.error("Can not find faculty: {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while preparing list of admission requests: {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        List<AdmissionRequest> admissionRequestsList = admissionRequestService.getSortedListOfRequestForFaculty(faculty);

        request.setAttribute("admissionRequestsList", admissionRequestsList);
        request.setAttribute("facultyId", facultyId);

        return "/WEB-INF/jsp/admin/statementOfFaculty.jsp";
    }

}
