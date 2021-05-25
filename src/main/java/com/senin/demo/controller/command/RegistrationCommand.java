package com.senin.demo.controller.command;

import com.senin.demo.model.DAO.DAOFactory;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.model.entity.ApplicantProfile;
import com.senin.demo.model.entity.ApplicantStatus;
import com.senin.demo.model.entity.Role;
import com.senin.demo.utils.validation.EmailValidator;
import com.senin.demo.utils.validation.FieldValidator;
import com.senin.demo.utils.validation.PhoneValidator;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand implements Command {

    static final Logger LOG = LoggerFactory.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = (String) request.getSession().getAttribute("lang");
        String errorMessage = null;
        String forward = "WEB-INF\\jsp\\registration.jsp";
        FieldValidator fieldValidator = new FieldValidator();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (fieldValidator.validate(username) || fieldValidator.validate(password)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Логін/Пароль не можуть буди пустими";
            } else {
                errorMessage = "Login/password cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        String email = request.getParameter("email");
        if (fieldValidator.validate(email)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поштова скринька не може буди пустою";
            } else {
                errorMessage = "Login/password cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(email)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поштова скринька не дійсна";
            } else {
                errorMessage = "Email is not valid";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;

        }


        String firstName = request.getParameter("firstName");
        if (fieldValidator.validate(firstName)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Ім'я не може буди пустим";
            } else {
                errorMessage = "First Name cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        String lastName = request.getParameter("lastName");
        if (fieldValidator.validate(lastName)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Призвіще не може буди пустим";
            } else {
                errorMessage = "Last Name cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        String address = request.getParameter("address");
        if (fieldValidator.validate(address)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Адреса не може буди пустим";
            } else {
                errorMessage = "address cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        String city = request.getParameter("city");
        if (fieldValidator.validate(city)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Місто не може буди пустим";
            } else {
                errorMessage = "City cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        String region = request.getParameter("region");
        if (fieldValidator.validate(region)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Область не може буди пустим";
            } else {
                errorMessage = "Region cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        String school = request.getParameter("school");
        if (fieldValidator.validate(school)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Школа не може буди пустим";
            } else {
                errorMessage = "School cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        PhoneValidator phoneValidator = new PhoneValidator();
        String phoneNumber = request.getParameter("phoneNumber");
        if (fieldValidator.validate(phoneNumber)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Номер не може буди пустим";
            } else {
                errorMessage = "Number cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        if (!phoneValidator.validate(phoneNumber)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Номер не відповідае формату ххх-ххх-хххх";
            } else {
                errorMessage = "Wrong number format xxx-xxx-xxxx";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }


        Applicant applicant = new Applicant();
        applicant.setUsername(username);
        applicant.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
        applicant.setRole(Role.USER);
        applicant.setApplicantStatus(ApplicantStatus.ACTIVE);


        ApplicantProfile applicantProfile = new ApplicantProfile();
        applicantProfile.setEmail(email);
        applicantProfile.setFirstName(firstName);
        applicantProfile.setLastName(lastName);
        applicantProfile.setAddress(address);
        applicantProfile.setCity(city);
        applicantProfile.setRegion(region);
        applicantProfile.setSchool(school);
        applicantProfile.setPhoneNumber(phoneNumber);
        applicantProfile.setApplicant(applicant);


        try {
            daoFactory.getApplicantDAO().insertApplicant(applicant, applicantProfile);
        } catch (SQLException throwables) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Користувач з Ім'ям " + applicant.getUsername() + " вже існує";
            } else {
                errorMessage = "Username " + applicant.getUsername() + " already exists";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;

        }

        try {
            response.sendRedirect("/WEB-INF/jsp/login.jsp");
        } catch (IOException e) {
            LOG.error("Bad Request!",e);
            errorMessage="Bad request!";
            request.setAttribute("errorMessage", errorMessage);
        }


        return forward;
    }
}
