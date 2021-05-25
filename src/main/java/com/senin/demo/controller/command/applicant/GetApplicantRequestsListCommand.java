package com.senin.demo.controller.command.applicant;


import com.senin.demo.model.entity.AdmissionRequest;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetApplicantRequestsListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Applicant applicant = (Applicant) request.getSession().getAttribute("applicant");
        List<AdmissionRequest> admissionRequestList =
                daoFactory.getAdmissionRequestDAO().selectAdmissionRequestsForApplicantWithId(applicant.getId());

        request.setAttribute("requestsList", admissionRequestList);
        return "/WEB-INF/jsp/applicant/applicant-requests.jsp";
    }
}
