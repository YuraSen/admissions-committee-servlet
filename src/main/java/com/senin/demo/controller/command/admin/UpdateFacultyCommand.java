package com.senin.demo.controller.command.admin;

import com.senin.demo.model.entity.Faculty;
import com.senin.demo.controller.command.Command;
import com.senin.demo.service.FacultyService;
import com.senin.demo.utils.util.FacultyDTOMapper;
import com.senin.demo.utils.validation.FacultyValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateFacultyCommand implements Command {
    FacultyService facultyService;

    public UpdateFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = (String) request.getSession().getAttribute("lang");

        Map<String, String> facultyParameters = request.getParameterMap().entrySet().stream()
                .filter(entry -> !("command".equals(entry.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, stringEntry -> request.getParameter(stringEntry.getKey())));


        FacultyValidator facultyValidator = new FacultyValidator(lang);
        Map<String, String> errors = facultyValidator.validateFaculty(facultyParameters);
        if (!errors.isEmpty()) {
            facultyParameters.entrySet().stream().forEach(c -> request.setAttribute(c.getKey(), c.getValue()));
            errors.entrySet().stream().forEach(entity -> request.setAttribute(entity.getKey(), entity.getValue()));
            return "WEB-INF/jsp/admin/adminEditFaculty.jsp";
        }
        FacultyDTOMapper facultyDTOMapper = new FacultyDTOMapper();
        Faculty faculty = facultyDTOMapper.getFaculty(facultyParameters);
        faculty.setId(Long.parseLong(facultyParameters.get("facultyId")));
        facultyService.update(faculty);

        return "/controller?command=adminWorkspace";
    }
}
