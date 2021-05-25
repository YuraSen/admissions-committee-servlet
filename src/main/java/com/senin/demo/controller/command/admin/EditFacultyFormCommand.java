package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.service.ApplicantService;
import com.senin.demo.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditFacultyFormCommand implements Command {
    private final FacultyService facultyService;

    public EditFacultyFormCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        Faculty faculty =  facultyService.findById(facultyId);
        request.setAttribute("faculty", faculty);
        return "WEB-INF\\jsp\\admin\\adminEditFaculty.jsp";
    }
}
