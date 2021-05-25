package com.senin.demo.controller.command;

import com.senin.demo.controller.command.admin.*;
import com.senin.demo.controller.command.applicant.*;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.service.ApplicantService;
import com.senin.demo.service.FacultyService;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("login", new LoginCommand());
        commandMap.put("loginForm", new LoginFormCommand());
        commandMap.put("logout", new LogoutCommand());

        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("registrationForm", new RegistrationFormCommand());
        commandMap.put("blockUnblockFacultyRegistration", new BlockUnblockFacultyRegistrationCommand(new FacultyService()));

        commandMap.put("getApplicantRequestsList", new GetApplicantRequestsListCommand());
        commandMap.put("admissionRequests", new AllAdmissionRequests());
        commandMap.put("getSubmitRequestForm", new GetSubmitRequestFormCommand());
        commandMap.put("submitRequest", new SubmitRequestCommand());
        commandMap.put("deleteAdmissionRequest", new DeleteAdmissionRequestCommand());
        commandMap.put("changeAdmissionRequestStatus", new ChangeAdmissionRequestStatusCommand(new AdmissionRequestService()));

        commandMap.put("applicantList", new AllApplicantsCommand(new ApplicantService()));
        commandMap.put("editApplicant", new EditApplicantCommand(new ApplicantService()));
        commandMap.put("editApplicantForm", new EditApplicantFormCommand(new ApplicantService()));
        commandMap.put("editFacultyForm", new EditFacultyFormCommand(new FacultyService()));
        commandMap.put("deleteApplicant", new DeleteApplicantCommand(new ApplicantService()));

        commandMap.put("applicantProfileEdit", new ApplicantProfileEditCommand());
        commandMap.put("applicantProfile", new ApplicantProfileCommand());
        commandMap.put("updateApplicantProfile", new UpdateApplicantProfileCommand());
        commandMap.put("checkRequestFromFacultyReqList", new CheckRequestFromFacultyReqListCommand(new AdmissionRequestService()));


        commandMap.put("showRequestsListOfFaculty", new ShowRequestsListOfFacultyCommand(new FacultyService()));
        commandMap.put("facultiesList", new AllFacultiesCommand());
        commandMap.put("deleteFaculty", new DeleteFacultyCommand(new FacultyService()));
        commandMap.put("updateFaculty", new UpdateFacultyCommand(new FacultyService()));
        commandMap.put("createNewFaculty", new CreateNewFacultyCommand(new FacultyService()));
        commandMap.put("createNewFacultyForm", new CreateNewFacultyFormCommand());
        commandMap.put("getStatementOfFaculty", new GetStatementOfFacultyCommand(new FacultyService(),new AdmissionRequestService()));
        commandMap.put("finalizeStatementForFaculty", new FinalizeStatementForFacultyCommand(new FacultyService(),new AdmissionRequestService()));

        commandMap.put("adminWorkspace", new AdminWorkspaceCommand(new FacultyService()));
    }

    private CommandContainer() {

    }

    public static Command get(String commandName) {
        if (commandName == null || !commandMap.containsKey(commandName)) {
            return commandMap.get("noCommand");
        }
        return commandMap.get(commandName);
    }
}
