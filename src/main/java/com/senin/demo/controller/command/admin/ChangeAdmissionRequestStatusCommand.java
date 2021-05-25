package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.model.entity.AdmissionRequestStatus;
import com.senin.demo.service.AdmissionRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChangeAdmissionRequestStatusCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(ChangeAdmissionRequestStatusCommand.class);

    private final AdmissionRequestService admissionRequestService;

    public ChangeAdmissionRequestStatusCommand(AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AdmissionRequestStatus newAdmissionRequestStatus = AdmissionRequestStatus.valueOf(request.getParameter("admissionRequestStatus"));
        Long admissionRequestId = Long.valueOf(request.getParameter("id"));
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        try {
            daoFactory.getAdmissionRequestDAO().changeAdmissionRequestStatus(admissionRequestId, newAdmissionRequestStatus);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("facultyId", facultyId);
        response.sendRedirect("/controller?command=showRequestsListOfFaculty&facultyId=" + facultyId);
        return "";
    }
}
