package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.CanNotFindRequestById;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.entity.AdmissionRequest;
import com.senin.demo.service.AdmissionRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckRequestFromFacultyReqListCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(CheckRequestFromFacultyReqListCommand.class);
    private final AdmissionRequestService admissionRequestService;

    public CheckRequestFromFacultyReqListCommand(AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long admissionRequestId = Long.valueOf(request.getParameter("requestId"));
        AdmissionRequest admissionRequest;
        try {
            admissionRequest = admissionRequestService.findById(admissionRequestId);

        } catch (DbProcessingException e) {
            LOG.error("Error occurred while checking for admission request : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        } catch (CanNotFindRequestById ex) {
            LOG.error("Can not find request: {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        request.setAttribute("admissionRequest", admissionRequest);

        return "WEB-INF\\jsp\\admin\\checkRequestFromFacultyRequestList.jsp";
    }
}
