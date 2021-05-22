package com.senin.admissions_committee_servlet.web.command.applicant;


import com.senin.admissions_committee_servlet.entity.AdmissionRequest;
import com.senin.admissions_committee_servlet.entity.Applicant;
import com.senin.admissions_committee_servlet.web.command.Command;

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
