package com.app.demo.service;

import com.app.demo.model.CommonResponse;
import com.app.demo.model.GetEmpData;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeService {

    public CommonResponse getEmployeeById(GetEmpData getEmpData) throws Exception;

    public CommonResponse getEmployee() throws Exception;

    public CommonResponse createEmployee(GetEmpData getEmpData) throws Exception;

    public CommonResponse deleteEmployee(GetEmpData getEmpData) throws Exception;

    public CommonResponse updateEmployee(GetEmpData getEmpData) throws Exception;

    public CommonResponse getDependentsByEmpId (String EmpId) throws Exception;
}
