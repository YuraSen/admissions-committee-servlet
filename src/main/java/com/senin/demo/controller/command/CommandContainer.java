package com.senin.demo.controller.command;

import com.senin.demo.controller.command.admin.AdminWorkspaceCommand;
import com.senin.demo.controller.command.applicant.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("facultiesList", new AllFacultiesCommand());
        commandMap.put("applicantList", new AllApplicantsCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("admissionRequests", new AllAdmissionRequests());
        commandMap.put("loginForm", new LoginFormCommand());
        commandMap.put("submitRequest", new SubmitRequestCommand());
        commandMap.put("registrationForm", new RegistrationFormCommand());
        commandMap.put("adminWorkspace", new AdminWorkspaceCommand());
        commandMap.put("applicantProfile", new ApplicantProfileCommand());
        commandMap.put("applicantProfileEdit", new ApplicantProfileEditCommand());
        commandMap.put("updateApplicantProfile", new UpdateApplicantProfileCommand());
        commandMap.put("getSubmitRequestForm", new GetSubmitRequestFormCommand());
    }

    private CommandContainer() {

    }

    public static Command get(String commandName) {
        if (commandName == null || !commandMap.containsKey(commandName)) {
            // log.trace("Command not found, name --> " + commandName);
            return commandMap.get("noCommand");
        }
        return commandMap.get(commandName);
    }
}
