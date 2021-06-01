package com.senin.demo.controller.command.applicant;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.entity.AdmissionRequest;
import com.senin.demo.model.entity.AdmissionRequestStatus;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.service.FacultyService;
import com.senin.demo.utils.validation.AdmissionRequestValidator;
import com.senin.demo.utils.validation.FieldValidator;
import com.senin.demo.utils.validation.GradeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public class SubmitRequestCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(SubmitRequestCommand.class);
    private final AdmissionRequestService admissionRequestService;
    private final FacultyService facultyService;

    public SubmitRequestCommand(AdmissionRequestService admissionRequestService, FacultyService facultyService) {
        this.admissionRequestService = admissionRequestService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        String lang = (String) request.getSession().getAttribute("lang");
        String facultyId = request.getParameter("facultyId");
        String errorMessage = null;


        Map<String, String> admissionRequestParameters = request.getParameterMap().entrySet().stream()
                .filter(entry -> !("command".equals(entry.getKey())))
                .filter(entry -> !("facultyId".equals(entry.getKey())))
                .filter(entry -> !("applicantId".equals(entry.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, stringEntry -> request.getParameter(stringEntry.getKey())));
        FieldValidator fieldValidator = new FieldValidator();
        GradeValidator gradeValidator = new GradeValidator();

        AdmissionRequestValidator admissionRequestValidator = new AdmissionRequestValidator(lang, fieldValidator, gradeValidator);
        Map<String, String> errors = admissionRequestValidator.validateAdmissionRequest(admissionRequestParameters);

        if (!errors.isEmpty()) {
            request.setAttribute("faculty", facultyService.findById(Long.valueOf(facultyId)));
            request.setAttribute("applicant", request.getSession().getAttribute("applicant"));
            admissionRequestParameters.entrySet().stream().forEach(c -> request.setAttribute(c.getKey(), c.getValue()));
            errors.entrySet().stream().forEach(entity -> request.setAttribute(entity.getKey(), entity.getValue()));
            return "WEB-INF/jsp/applicant/applicant-submit-request-form.jsp";

        }


        AdmissionRequest admissionRequest = new AdmissionRequest();
        admissionRequest.setFacultyId(Long.valueOf(request.getParameter("facultyId")));
        admissionRequest.setApplicantId(Long.valueOf(request.getParameter("applicantId")));
        admissionRequest.setRequiredSubject1Grade(Integer.parseInt(admissionRequestParameters.get("requiredSubject1Grade")));
        admissionRequest.setRequiredSubject2Grade(Integer.parseInt(admissionRequestParameters.get("requiredSubject2Grade")));
        admissionRequest.setRequiredSubject3Grade(Integer.parseInt(admissionRequestParameters.get("requiredSubject3Grade")));
        admissionRequest.setAdmissionRequestStatus(AdmissionRequestStatus.NEW);


        try {
            admissionRequestService.create(admissionRequest);
        } catch (DbProcessingException e) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Ви вже подали заявку на цей факультет";
            } else {
                errorMessage = "You have  already sent request to this faculty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return "/controller?command=getSubmitRequestForm&facultyId=" + facultyId;
        }


        try {
            response.sendRedirect("/controller?command=getApplicantRequestsList");
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        return "";
    }
}
