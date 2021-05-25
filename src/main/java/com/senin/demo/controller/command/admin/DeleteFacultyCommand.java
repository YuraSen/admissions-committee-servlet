package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteFacultyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        try {
            daoFactory.getFacultyDAO().deleteFaculty(Long.valueOf(request.getParameter("facultyId")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "/controller?command=adminWorkspace";

    }
}
