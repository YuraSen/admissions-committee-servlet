package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinalizeStatementForFacultyCommand implements Command {
    private final FacultyService facultyService;
    private final AdmissionRequestService admissionRequestService;

    public FinalizeStatementForFacultyCommand(FacultyService facultyService, AdmissionRequestService admissionRequestService) {
        this.facultyService = facultyService;
        this.admissionRequestService = admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = "block";
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty = facultyService.findById(facultyId);
        facultyService.changeAdmissionOpenStatus(action, facultyId);

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=" + "report");

        return "WEB-INF\\jsp\\admin\\filePage.jsp";

    }
}
