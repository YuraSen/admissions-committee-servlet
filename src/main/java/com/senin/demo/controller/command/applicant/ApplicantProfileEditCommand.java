package com.senin.demo.controller.command.applicant;

import com.senin.demo.model.entity.Applicant;
import com.senin.demo.model.entity.ApplicantProfile;
import com.senin.demo.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicantProfileEditCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        HttpSession session = request.getSession();
        Applicant applicant = (Applicant) session.getAttribute("applicant");

        ApplicantProfile applicantProfile =
                null;
        try {
            applicantProfile = daoFactory.getApplicantDAO().getApplicantProfile(applicant)
                    .orElseThrow(()->new Exception("APPLICANT PROFILE"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("applicantProfile", applicantProfile);
        return "WEB-INF/jsp/applicant/applicant-profile-edit.jsp";

    }
}
