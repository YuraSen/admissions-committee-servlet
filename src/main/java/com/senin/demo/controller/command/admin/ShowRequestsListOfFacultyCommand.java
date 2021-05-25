package com.senin.demo.controller.command.admin;


import com.senin.demo.controller.command.Command;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRequestsListOfFacultyCommand implements Command {
    private final FacultyService facultyService;

    public ShowRequestsListOfFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty = facultyService.findById(facultyId);

        request.setAttribute("faculty", faculty);
        return "/WEB-INF/jsp/admin/admin-faculty-requests.jsp";
    }
}
