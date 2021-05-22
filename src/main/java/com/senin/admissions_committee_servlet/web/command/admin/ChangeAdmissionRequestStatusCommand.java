package com.senin.admissions_committee_servlet.web.command.admin;

import com.senin.admissions_committee_servlet.entity.AdmissionRequestStatus;
import com.senin.admissions_committee_servlet.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ChangeAdmissionRequestStatusCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        AdmissionRequestStatus newAdmissionRequestStatus = AdmissionRequestStatus.valueOf(request.getParameter("admissionRequestStatus"));
        Long admissionRequestId = Long.valueOf(request.getParameter("id"));
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        try {
            daoFactory.getAdmissionRequestDAO().changeAdmissionRequestStatus(admissionRequestId,newAdmissionRequestStatus);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("facultyId",facultyId);
        return "/controller?command=showRequestsListOfFaculty";
    }
}
