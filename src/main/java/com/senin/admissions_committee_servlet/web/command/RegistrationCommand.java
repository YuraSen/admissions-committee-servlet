package com.senin.admissions_committee_servlet.web.command;

import com.senin.admissions_committee_servlet.DAO.DAOFactory;
import com.senin.admissions_committee_servlet.entity.Applicant;
import com.senin.admissions_committee_servlet.entity.ApplicantProfile;
import com.senin.admissions_committee_servlet.entity.ApplicantStatus;
import com.senin.admissions_committee_servlet.entity.Role;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegistrationCommand implements Command {

    private static final DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Applicant applicant = new Applicant();
        applicant.setUsername(request.getParameter("username"));
        applicant.setPassword(BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(12)));
        applicant.setRole(Role.USER);
        applicant.setApplicantStatus(ApplicantStatus.ACTIVE);

        ApplicantProfile applicantProfile = new ApplicantProfile();
        applicantProfile.setEmail(request.getParameter("email"));
        applicantProfile.setFirstName(request.getParameter("firstName"));
        applicantProfile.setLastName(request.getParameter("lastName"));
        applicantProfile.setAddress(request.getParameter("address"));
        applicantProfile.setCity(request.getParameter("city"));
        applicantProfile.setRegion(request.getParameter("region"));
        applicantProfile.setSchool(request.getParameter("school"));
        applicantProfile.setPhoneNumber(request.getParameter("phoneNumber"));
        applicantProfile.setApplicant(applicant);

        try {
            daoFactory.getApplicantDAO().insertApplicant(applicant, applicantProfile);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "/WEB-INF/jsp/login.jsp";
    }
}
