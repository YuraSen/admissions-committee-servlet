package com.senin.demo.controller.command;

import com.senin.demo.listener.SessionListener;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.model.entity.ApplicantProfile;
import com.senin.demo.model.entity.ApplicantStatus;
import com.senin.demo.model.entity.Role;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("lang");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String errorMessage = null;
        String forward = "WEB-INF\\jsp\\login.jsp";
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            if (Objects.nonNull(lang) && lang.equals("uk")) {
                errorMessage = "Логін/Пароль не можуть буди пустими";

            } else {
                errorMessage = "Login/password cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        Applicant applicant = null;
        try {
            applicant = daoFactory.getApplicantDAO().findApplicantByUsername(username).orElseThrow(Exception::new);
        } catch (Exception e) {
            errorMessage = "Applicant not found!";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        ApplicantProfile applicantProfile = null;
        try {
            applicantProfile = daoFactory.getApplicantDAO().getApplicantProfile(applicant).orElseThrow(Exception::new);
        } catch (Exception e) {
            errorMessage = "Applicant Profile not found";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        applicant.setApplicantProfile(applicantProfile);

        if (!BCrypt.checkpw(password, applicant.getPassword())) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Користувача з таким логіном/паролем неіснує!";
            } else {
                errorMessage = "Cannot find user with such login/password!";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }


        if (applicant.getApplicantStatus() == ApplicantStatus.BLOCKED) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Користувач заблокований, зверніться до адміністратора";
            } else {
                errorMessage = "User blocked, contact admin";
            }

            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        if (SessionListener.getApplicantsInSessions().containsKey(applicant.getUsername())) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Користувач вже у программі";
            } else {
                errorMessage = "User already inside";
            }

            request.setAttribute("errorMessage", errorMessage);
            return forward;
        } else {
            SessionListener.getApplicantsInSessions().put(applicant.getUsername(), session.getId());
        }


        Role applicantRole = applicant.getRole();


        if (applicantRole == Role.ADMIN) {
            try {
                forward = "";
                response.sendRedirect("/controller?command=adminWorkspace");
            } catch (IOException e) {
                LOG.error("Bad Request!", e);
                errorMessage = "Bad Request!";
                request.setAttribute("errorMessage", errorMessage);
            }
        }


        if (applicantRole == Role.USER) {
            try {
                forward = "";
                response.sendRedirect("/controller?command=facultiesList");
            } catch (IOException e) {
                LOG.error("Bad Request!", e);
                errorMessage = "Bad Request!";
                request.setAttribute("errorMessage", errorMessage);
            }

        }

        session.setAttribute("applicant", applicant);
        session.setAttribute("applicantRole", applicantRole);

        LOG.info("Applicant {} logged as {}", applicant.getUsername(), applicantRole.getName());

        return forward;
    }
}
