package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.ApplicantNotFoundException;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.service.ApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditApplicantFormCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(EditApplicantCommand.class);
    private final ApplicantService applicantService;

    public EditApplicantFormCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long applicantId = Long.valueOf(request.getParameter("applicantId"));
        Applicant applicant;

        try {
            applicant = applicantService.findById(applicantId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while searching for applicant : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        } catch (ApplicantNotFoundException ex) {
            LOG.error("Can not find applicant: {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        request.setAttribute("applicant", applicant);

        return "WEB-INF\\jsp\\admin\\adminEditApplicant.jsp";
    }
}
