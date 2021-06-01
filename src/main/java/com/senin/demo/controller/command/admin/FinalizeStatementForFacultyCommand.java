package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.CanNotMakePDFException;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.exception.FacultyNotFoundException;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FinalizeStatementForFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(FinalizeStatementForFacultyCommand.class);

    private final FacultyService facultyService;
    private final AdmissionRequestService admissionRequestService;

    public FinalizeStatementForFacultyCommand(FacultyService facultyService, AdmissionRequestService admissionRequestService) {
        this.facultyService = facultyService;
        this.admissionRequestService = admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = "block";
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty;

        try {
            faculty = facultyService.findById(facultyId);
            facultyService.changeAdmissionOpenStatus(action, facultyId);

        } catch (FacultyNotFoundException ex) {
            LOG.error("Can not find faculty: {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while changing faculty admissions status: {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=" + "report");
        try {
            response.getOutputStream().
                    write(admissionRequestService.finalizeStatement(faculty));
        } catch (CanNotMakePDFException | IOException e) {
            LOG.error("Error occurred while preparing PDF: {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        return "";

    }
}
