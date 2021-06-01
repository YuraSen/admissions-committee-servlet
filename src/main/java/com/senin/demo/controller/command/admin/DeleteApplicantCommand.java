package com.senin.demo.controller.command.admin;


import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.service.ApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteApplicantCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(DeleteApplicantCommand.class);
    private final ApplicantService applicantService;

    public DeleteApplicantCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long applicantId = Long.valueOf(request.getParameter("applicantId"));
        try {
            applicantService.delete(applicantId);
            LOG.info("applicant with id: {} deleted", applicantId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while delete applicant : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        try {
            response.sendRedirect("/controller?command=applicantList");
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        return "";
    }
}
