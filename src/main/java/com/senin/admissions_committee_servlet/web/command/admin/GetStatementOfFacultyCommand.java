package com.senin.admissions_committee_servlet.web.command.admin;

import com.senin.admissions_committee_servlet.entity.AdmissionRequest;
import com.senin.admissions_committee_servlet.entity.AdmissionRequestStatus;
import com.senin.admissions_committee_servlet.entity.Faculty;
import com.senin.admissions_committee_servlet.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GetStatementOfFacultyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty = daoFactory.getFacultyDAO().findFaculty(facultyId);

        List<AdmissionRequest> admissionRequestsList= getSortedListOfRequestForFaculty(faculty);

        request.setAttribute("admissionRequestsList", admissionRequestsList);
        request.setAttribute("facultyId", facultyId);

        return "/WEB-INF/jsp/admin/statementOfFaculty.jsp";
    }



    protected List<AdmissionRequest> getSortedListOfRequestForFaculty(Faculty faculty) {
        return faculty.getAdmissionRequestList()
                .stream()
                .filter(x -> x.getAdmissionRequestStatus() == AdmissionRequestStatus.APPROVED)
                .sorted(
                        Comparator.comparingInt(AdmissionRequest::getSumOfGrades).reversed()
                                .thenComparing(AdmissionRequest::getCreationDateTime))
                .limit(faculty.getTotalCapacity())
                .collect(Collectors.toList());
    }
}
