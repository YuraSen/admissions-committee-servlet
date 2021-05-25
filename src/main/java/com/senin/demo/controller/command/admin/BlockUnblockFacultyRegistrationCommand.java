package com.senin.demo.controller.command.admin;


import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUnblockFacultyRegistrationCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(BlockUnblockFacultyRegistrationCommand.class);
    private FacultyService facultyService;

    public BlockUnblockFacultyRegistrationCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        try {
            facultyService.changeAdmissionOpenStatus(action, facultyId);
            LOG.info("Admission {} for faculty with id: {}", action, facultyId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while changing faculty admissions status: {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        response.sendRedirect("/controller?command=adminWorkspace");
        return "";
    }
}
