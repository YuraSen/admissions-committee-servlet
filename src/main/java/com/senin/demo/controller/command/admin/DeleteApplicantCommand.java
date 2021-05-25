package com.senin.demo.controller.command.admin;


import com.senin.demo.controller.command.Command;
import com.senin.demo.service.ApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteApplicantCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(DeleteApplicantCommand.class);
    private final ApplicantService applicantService;

    public DeleteApplicantCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long applicantId = Long.valueOf(request.getParameter("applicantId"));

        applicantService.delete(applicantId);
        LOG.info("applicant with id: {} deleted", applicantId);

        response.sendRedirect("/controller?command=applicantList");

        return "";
    }
}
