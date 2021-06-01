package com.senin.demo.controller.command.applicant;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.service.AdmissionRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteAdmissionRequestCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(DeleteAdmissionRequestCommand.class);
    private final AdmissionRequestService admissionRequestService;

    public DeleteAdmissionRequestCommand(AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long admissionRequestId = Long.valueOf(request.getParameter("admissionRequestId"));

        try {
            admissionRequestService.delete(admissionRequestId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while deleting request : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
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
