package com.senin.admissions_committee_servlet.web.command.admin;

import com.senin.admissions_committee_servlet.entity.Faculty;
import com.senin.admissions_committee_servlet.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.senin.admissions_committee_servlet.web.command.Command.daoFactory;

public class CreateNewFacultyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Faculty faculty = new Faculty();
        faculty.setName(request.getParameter("name"));
        faculty.setDescription(request.getParameter("description"));
        faculty.setBudgetCapacity(Integer.parseInt(request.getParameter("budgetCapacity")));
        faculty.setTotalCapacity(Integer.parseInt(request.getParameter("totalCapacity")));
        faculty.setRequiredSubject1(request.getParameter("requiredSubject1"));
        faculty.setRequiredSubject2(request.getParameter("requiredSubject2"));
        faculty.setRequiredSubject3(request.getParameter("requiredSubject3"));
        faculty.setAdmissionOpen(true);
        daoFactory.getFacultyDAO().createFaculty(faculty);

        return "/controller?command=adminWorkspace";
    }
}
