package com.senin.demo.controller.command.applicant;


import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.service.AdmissionRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetApplicantRequestsListCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(GetApplicantRequestsListCommand.class);
    private final AdmissionRequestService admissionRequestService;


    public GetApplicantRequestsListCommand(AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Applicant applicant = (Applicant) request.getSession().getAttribute("applicant");

        try {
            request.setAttribute("requestsList", admissionRequestService.selectAdmissionRequestsForApplicantWithId(applicant.getId()));
            return "/WEB-INF/jsp/applicant/applicant-requests.jsp";
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while getting requests for applicant {} : {}", applicant.getUsername(), e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
    }
}
