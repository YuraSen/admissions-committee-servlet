package com.senin.demo.controller.command.admin;

import com.senin.demo.model.entity.Faculty;
import com.senin.demo.controller.command.Command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditFacultyFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long facultyId =Long.valueOf( request.getParameter("facultyId"));

        Faculty faculty = daoFactory.getFacultyDAO().findFaculty(facultyId);

        request.setAttribute("action","edit");
        request.setAttribute("faculty",faculty);
        return "WEB-INF\\jsp\\admin\\adminCreateEditFacultyFrom.jsp";
    }
}