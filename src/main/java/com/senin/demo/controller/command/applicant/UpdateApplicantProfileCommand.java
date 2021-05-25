package com.senin.demo.controller.command.applicant;

import com.senin.demo.controller.command.Command;
import com.senin.demo.model.DAO.DAOFactory;
import com.senin.demo.model.entity.ApplicantProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateApplicantProfileCommand implements Command {
    private static final DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

        try {
            daoFactory.getApplicantDAO().updateApplicantProfile(applicantProfile);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        response.sendRedirect("/controller?command=applicantProfile");

        return "";
    }
}
