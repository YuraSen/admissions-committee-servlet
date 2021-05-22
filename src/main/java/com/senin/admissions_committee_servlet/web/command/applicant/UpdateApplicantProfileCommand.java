package com.senin.admissions_committee_servlet.web.command.applicant;

import com.senin.admissions_committee_servlet.DAO.DAOFactory;
import com.senin.admissions_committee_servlet.entity.ApplicantProfile;
import com.senin.admissions_committee_servlet.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateApplicantProfileCommand implements Command {
    private static final DAOFactory daoFactory = DAOFactory.getDAOFactory(1);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ApplicantProfile applicantProfile =  new ApplicantProfile();
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

        return "/controller?command=applicantProfile";
    }
}
