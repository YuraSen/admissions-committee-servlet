package com.senin.demo.controller.command.applicant;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.ApplicantNotFoundException;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.entity.ApplicantProfile;
import com.senin.demo.service.ApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateApplicantProfileCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(UpdateApplicantProfileCommand.class);
    private final ApplicantService applicantService;

    public UpdateApplicantProfileCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {

        ApplicantProfile applicantProfile = new ApplicantProfile();
        applicantProfile.setId(Long.valueOf(request.getParameter("applicantProfileId")));
        applicantProfile.setEmail(request.getParameter("email"));
        applicantProfile.setFirstName(request.getParameter("firstName"));
        applicantProfile.setLastName(request.getParameter("lastName"));
        applicantProfile.setAddress(request.getParameter("address"));
        applicantProfile.setCity(request.getParameter("city"));
        applicantProfile.setRegion(request.getParameter("region"));
        applicantProfile.setSchool(request.getParameter("school"));
        applicantProfile.setPhoneNumber(request.getParameter("phoneNumber"));

        String fileName = null;
        try {
            fileName = applicantService.saveFile(request);
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        if (fileName.isEmpty()) {
            ApplicantProfile oldCp = applicantService.getApplicantProfileById(Long.valueOf(request.getParameter("applicantProfileId")))
                    .orElseThrow(() -> new ApplicantNotFoundException("Can not find profile"));
            applicantProfile.setFileName(oldCp.getFileName());
        } else {
            applicantProfile.setFileName(fileName);
        }

        try {
            applicantService.updateApplicantProfile(applicantProfile);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while updating applicant profile : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        try {
            response.sendRedirect("/controller?command=applicantProfile");
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        return "";
    }
}
