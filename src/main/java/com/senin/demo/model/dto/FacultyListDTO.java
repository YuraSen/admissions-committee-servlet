package com.senin.demo.model.dto;


import com.senin.demo.model.entity.Faculty;

import java.util.List;

public class FacultyListDTO {

    int count;
    List<Faculty> facultyList;

    public FacultyListDTO(int count, List<Faculty> facultyList) {
        this.count = count;
        this.facultyList = facultyList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }
}
