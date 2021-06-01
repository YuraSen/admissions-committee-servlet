package com.senin.demo.controller.command;

import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.model.entity.ApplicantProfile;
import com.senin.demo.model.entity.ApplicantStatus;
import com.senin.demo.model.entity.Role;
import com.senin.demo.service.ApplicantService;
import com.senin.demo.utils.validation.EmailValidator;
import com.senin.demo.utils.validation.FieldValidator;
import com.senin.demo.utils.validation.PhoneValidator;
import com.senin.demo.utils.validation.RegistrationValidator;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistrationCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(RegistrationCommand.class);
    private final ApplicantService applicantService;

    public RegistrationCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = (String) request.getSession().getAttribute("lang");
        String errorMessage = null;
        String forward = "WEB-INF\\jsp\\registration.jsp";

        FieldValidator fieldValidator = new FieldValidator();
        PhoneValidator phoneValidator = new PhoneValidator();
        EmailValidator emailValidator = new EmailValidator();

        RegistrationValidator registrationValidator =
                new RegistrationValidator(lang, fieldValidator, emailValidator, phoneValidator);
        Map<String, String> registrationParameters = request.getParameterMap().entrySet().stream()
                .filter(entry -> !("command".equals(entry.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, stringEntry -> request.getParameter(stringEntry.getKey())));

        Map<String, String> errors = registrationValidator.validateRegistration(registrationParameters);
        if (!errors.isEmpty()) {
            registrationParameters.entrySet().stream().forEach(c -> request.setAttribute(c.getKey(), c.getValue()));
            errors.entrySet().stream().forEach(entity -> request.setAttribute(entity.getKey(), entity.getValue()));
            return "WEB-INF/jsp/registration.jsp";
        }

        String fileName = null;
        try {
            fileName = applicantService.saveFile(request);
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        Applicant applicant = new Applicant();
        applicant.setUsername(registrationParameters.get("username"));
        applicant.setPassword(BCrypt.hashpw(registrationParameters.get("password"), BCrypt.gensalt(12)));
        applicant.setRole(Role.USER);
        applicant.setApplicantStatus(ApplicantStatus.ACTIVE);

        ApplicantProfile applicantProfile = new ApplicantProfile();
        applicantProfile.setEmail(registrationParameters.get("email"));
        applicantProfile.setFirstName(registrationParameters.get("firstName"));
        applicantProfile.setLastName(registrationParameters.get("lastName"));
        applicantProfile.setAddress(registrationParameters.get("address"));
        applicantProfile.setCity(registrationParameters.get("city"));
        applicantProfile.setRegion(registrationParameters.get("region"));
        applicantProfile.setSchool(registrationParameters.get("school"));
        applicantProfile.setPhoneNumber(registrationParameters.get("phoneNumber"));
        applicantProfile.setApplicant(applicant);

        try {
            applicantService.create(applicant, applicantProfile);
        } catch (DbProcessingException e) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Користувач з Ім'ям " + applicant.getUsername() + " вже існує";
            } else {
                errorMessage = "Username " + applicant.getUsername() + " already exists";
            }
            request.setAttribute("errorMessage", errorMessage);
            registrationParameters.entrySet().stream().forEach(c -> request.setAttribute(c.getKey(), c.getValue()));
            return forward;
        }

        try {
            forward = "";
            response.sendRedirect("/controller?command=loginForm");
        } catch (IOException e) {
            LOG.error("Bad Request!", e);
            errorMessage = "Bad request!";
            request.setAttribute("errorMessage", errorMessage);
        }

        return forward;
    }
}
