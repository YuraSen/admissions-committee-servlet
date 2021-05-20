package com.senin.admissions_committee_servlet.web.command;

import com.senin.admissions_committee_servlet.entity.Applicant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSubmitRequestFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Applicant applicant = (Applicant) session.getAttribute("applicant");

        request.setAttribute("applicant",applicant );
        request.setAttribute("facultyId",request.getParameter("facultyId") );
        request.setAttribute("facultyName",request.getParameter("facultyName") );

        return "/WEB-INF/jsp/applicant/applicant-submit-request-form.jsp";
    }
}
