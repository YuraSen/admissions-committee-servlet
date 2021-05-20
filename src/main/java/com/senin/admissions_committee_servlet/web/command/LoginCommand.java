package com.senin.admissions_committee_servlet.web.command;

import com.senin.admissions_committee_servlet.entity.Applicant;
import com.senin.admissions_committee_servlet.entity.ApplicantProfile;
import com.senin.admissions_committee_servlet.entity.Role;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String errorMessage = null;
        String forward = "WEB-INF\\jsp\\errorPage.jsp";
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        Applicant applicant = daoFactory.getApplicantDAO().findApplicantByUsername(username);
        try {
            ApplicantProfile applicantProfile = daoFactory.getApplicantDAO().getApplicantProfile(applicant)
                    .orElseThrow(Exception::new);
            applicant.setApplicantProfile(applicantProfile);
        } catch (Exception e) {
            errorMessage = "Applicant Profile not found!";
        }

        if (applicant == null || !BCrypt.checkpw(password, applicant.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
        } else {
            Role applicantRole = applicant.getRole();

            if (applicantRole == Role.ADMIN)
                forward = "/controller?command=adminWorkspace";

            if (applicantRole == Role.USER)
                forward = "/controller?command=facultiesList";
            ;

            session.setAttribute("applicant", applicant);
            session.setAttribute("applicantRole", applicantRole);

            log.info("Applicant " + applicant + " logged as " + applicantRole.getName());

        }
        return forward;
    }
}
