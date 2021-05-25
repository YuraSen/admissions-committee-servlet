package com.senin.demo.controller.command.applicant;

import com.senin.demo.model.entity.Applicant;
import com.senin.demo.controller.command.Command;
import com.senin.demo.model.entity.ApplicantProfile;
import com.senin.demo.model.entity.Faculty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class GetSubmitRequestFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Applicant applicant = (Applicant) session.getAttribute("applicant");

        ApplicantProfile applicantProfile = null;
        try {
            applicantProfile = daoFactory.getApplicantDAO().getApplicantProfile(applicant).get();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        applicant.setApplicantProfile(applicantProfile);
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        Faculty faculty = null;
        try {
            faculty = daoFactory.getFacultyDAO().findById(facultyId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("applicant",applicant );
        request.setAttribute("faculty", faculty);

        return "/WEB-INF/jsp/applicant/applicant-submit-request-form.jsp";
    }
}
