package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.service.ApplicantService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditApplicantFormCommand implements Command {
    private final ApplicantService applicantService;

    public EditApplicantFormCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long applicantId = Long.valueOf(request.getParameter("applicantId"));
        Applicant applicant = applicantService.findById(applicantId);
        request.setAttribute("applicant", applicant);

        return "WEB-INF\\jsp\\admin\\adminEditApplicant.jsp";
    }
}
