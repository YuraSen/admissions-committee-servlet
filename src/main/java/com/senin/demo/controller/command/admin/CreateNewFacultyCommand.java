package com.senin.demo.controller.command.admin;

import com.senin.demo.model.entity.Faculty;
import com.senin.demo.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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