package com.senin.demo.controller.command.applicant;

import com.senin.demo.exception.ApplicantNotFoundException;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.model.entity.ApplicantProfile;
import com.senin.demo.controller.command.Command;
import com.senin.demo.service.ApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicantProfileEditCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(ApplicantProfileCommand.class);
    private final ApplicantService applicantService;

    public ApplicantProfileEditCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        HttpSession session = request.getSession();
        Applicant applicant = (Applicant) session.getAttribute("applicant");

        ApplicantProfile applicantProfile = null;
        try {
            applicantProfile = applicantService.getApplicantProfile(applicant)
                    .orElseThrow(()->new ApplicantNotFoundException("APPLICANT PROFILE"));
        } catch (ApplicantNotFoundException e) {
            LOG.error("Can not find applicant profile : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        request.setAttribute("applicantProfile", applicantProfile);
        return "WEB-INF/jsp/applicant/applicant-profile-edit.jsp";

    }
}
