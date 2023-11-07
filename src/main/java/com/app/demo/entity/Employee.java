package com.app.demo.entity;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "Employee_Nipun")
public class Employee implements Serializable {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String empId;

    @Column(name = "EMPLOYEE_NIC")
    private String empNIC;

    @Column(name = "EMPLOYEE_NAME")
    private String empName;

    @Column(name = "EMPLOYEE_AGE")
    private int empAge;

    @Column(name = "EMPLOYEE_GENDER")
    private String empGender;

    @Column(name = "EMPLOYEE_ADDRESS")
    private String empAddress;

    @Column(name = "EMPLOYEE_SALARY")
    private int empSalary;

   /*@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Dependent> dependents;*/
}
