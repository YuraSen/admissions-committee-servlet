package com.senin.demo.controller.command.applicant;

import com.senin.demo.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteAdmissionRequestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long admissionRequestId = Long.valueOf(request.getParameter("admissionRequestId"));

        try {
            daoFactory.getAdmissionRequestDAO().deleteAdmissionRequest(admissionRequestId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("/controller?command=getApplicantRequestsList");
        return "";
    }
}
