package com.senin.admissions_committee_servlet.web.command;

import com.senin.admissions_committee_servlet.entity.Applicant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllApplicantsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Applicant> applicantList = (List<Applicant>) daoFactory.getApplicantDAO().getAllApplicantTO();

        request.setAttribute("applicantList", applicantList);

        return "/WEB-INF/jsp/applicant.jsp";
    }
}
