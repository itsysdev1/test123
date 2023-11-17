package com.app.project.model;

import com.app.project.entity.Dependent;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetEmpData implements Serializable {

    private String empId;
    private String empNIC;
    private String empName;
    private int empAge;
    private String empGender;
    private String empAddress;
    private int empSalary;
    private Dependent dependent;



}
