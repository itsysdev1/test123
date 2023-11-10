package com.app.project.service;

import com.app.project.model.CommonResponse;
import com.app.project.model.GetEmpData;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeService {

    public CommonResponse getEmployeeById(String employee_ID) throws Exception;

    public CommonResponse getEmployee() throws Exception;

    public CommonResponse createEmployee(GetEmpData getEmpData) throws Exception;

    public CommonResponse deleteEmployee(String employee_ID) throws Exception;

    public CommonResponse updateEmployee(String employee_ID, GetEmpData getEmpData) throws Exception;


}
