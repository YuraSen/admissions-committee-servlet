package com.senin.demo.controller.command;

import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.entity.AdmissionRequest;
import com.senin.demo.service.AdmissionRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllAdmissionRequests implements Command {
    static final Logger LOG = LoggerFactory.getLogger(AllAdmissionRequests.class);

    public AllAdmissionRequests(AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
    }

    private final AdmissionRequestService admissionRequestService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<AdmissionRequest> admissionRequests;
        try {
            admissionRequests = admissionRequestService.findAll();
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while searching for admission requests : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        request.setAttribute("admissionRequests", admissionRequests);

        return "/WEB-INF/jsp/admissionRequests.jsp";
    }
}
