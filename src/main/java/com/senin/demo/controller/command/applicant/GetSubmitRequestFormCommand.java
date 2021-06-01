package com.senin.demo.controller.command.applicant;

import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.exception.FacultyNotFoundException;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.controller.command.Command;
import com.senin.demo.model.entity.ApplicantProfile;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class GetSubmitRequestFormCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(GetSubmitRequestFormCommand.class);
    private final FacultyService facultyService;

    public GetSubmitRequestFormCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty;
        try {
            faculty = facultyService.findById(facultyId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while searching for faculty : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        } catch (FacultyNotFoundException ex) {
            LOG.error("Can not find faculty: {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        request.setAttribute("applicant",applicant );
        request.setAttribute("faculty", faculty);

        return "/WEB-INF/jsp/applicant/applicant-submit-request-form.jsp";
    }
}
