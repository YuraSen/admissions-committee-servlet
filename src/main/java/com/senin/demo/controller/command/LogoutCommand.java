package com.senin.demo.controller.command;

import com.senin.demo.listener.SessionListener;
import com.senin.demo.model.entity.Applicant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            Applicant applicant = (Applicant) session.getAttribute("applicant");
            SessionListener.getApplicantsInSessions().remove(applicant.getUsername());
            session.invalidate();
        }

        return "index.jsp";

    }
}
