package com.senin.admissions_committee_servlet.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();

        return "index.jsp";

    }
}
