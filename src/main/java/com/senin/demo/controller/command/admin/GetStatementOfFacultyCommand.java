package com.senin.demo.controller.command.admin;

import com.senin.demo.model.entity.AdmissionRequest;
import com.senin.demo.model.entity.AdmissionRequestStatus;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.controller.command.Command;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GetStatementOfFacultyCommand implements Command {
    private final FacultyService facultyService;
    private final AdmissionRequestService admissionRequestService;

    public GetStatementOfFacultyCommand(FacultyService facultyService, AdmissionRequestService admissionRequestService) {
        this.facultyService = facultyService;
        this.admissionRequestService = admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty = facultyService.findById(facultyId);

        List<AdmissionRequest> admissionRequestsList= admissionRequestService.getSortedListOfRequestForFaculty(faculty);

        request.setAttribute("admissionRequestsList", admissionRequestsList);
        request.setAttribute("facultyId", facultyId);

        return "/WEB-INF/jsp/admin/statementOfFaculty.jsp";
    }

}
