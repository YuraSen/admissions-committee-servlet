package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.service.ApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllApplicantsCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(AllApplicantsCommand.class);
    private final ApplicantService applicantService;

    public AllApplicantsCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Applicant> applicantList = null;
        try {
            applicantList = applicantService.findAll();
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while getting applicant list: {}",e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        request.setAttribute("applicantList", applicantList);

        return "/WEB-INF/jsp/applicant.jsp";
    }
}
