package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(DeleteFacultyCommand.class);
    private final FacultyService facultyService;

    public DeleteFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        try {
            facultyService.delete(facultyId);
            LOG.info("Faculty with id: {} deleted", facultyId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while delete faculty : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        try {
            response.sendRedirect("/controller?command=adminWorkspace");
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        return "";
    }
}
