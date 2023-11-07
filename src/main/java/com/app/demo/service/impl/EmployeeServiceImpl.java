package com.app.demo.service.impl;

import com.app.demo.entity.Dependent;
import com.app.demo.entity.Employee;
import com.app.demo.model.CommonResponse;
import com.app.demo.model.GetEmpData;
import com.app.demo.repo.DepRepo;
import com.app.demo.repo.EmpRepo;
import com.app.demo.service.EmployeeService;
import com.app.demo.util.SequenceGenerator;
import com.app.demo.util.ServiceHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private DepRepo depRepo;

    @Override
    public CommonResponse getEmployee() throws Exception{
        log.debug("START | getEmployees:");
        CommonResponse commonResponse = new CommonResponse();

        try {
        List<Employee> employee = empRepo.findAll();
        if (!employee.isEmpty()) {
            commonResponse.setStatusCode("000");
            commonResponse.setStatusDescription("Success");
            commonResponse.setPayload(employee);

        } else {
            commonResponse.setStatusCode("005");
            commonResponse.setStatusDescription("No Employee Record Found");
            commonResponse.setPayload(employee);
        }

    }catch (Exception e){
            log.error("Exception:{}",e.getMessage());
            log.error("Exception:{}",e.getClass());
            throw new Exception(e.getMessage());
    }
        log.debug("END | CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
        return commonResponse;
    }

    @Override
    public CommonResponse getEmployeeById(GetEmpData getEmpData) throws Exception{
        log.debug("START | getEmployeeById,getEmpData:{}",getEmpData.getEmpId());
        CommonResponse commonResponse = new CommonResponse();

        try {

            Optional<Employee> employee = empRepo.findById(getEmpData. getEmpId());
            if (employee.isPresent()) {
                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("Success");
                commonResponse.setPayload(employee.get());

            } else {
                commonResponse.setStatusCode("005");
                commonResponse.setStatusDescription("No Employee Record Found");
                commonResponse.setPayload(getEmpData);
            }

        }catch (Exception e){
            log.error("Exception:{}",e.getMessage());
            log.error("Exception:{}",e.getClass());
            throw new Exception(e.getMessage());
        }
        log.debug("END | getEmployeeById,commonResponse:{}", ServiceHelper.convertToJson(commonResponse));
        return commonResponse;
    }

    @Override
    public CommonResponse createEmployee(GetEmpData getEmpData) throws Exception{
        log.debug("START | createEmployee,getEmpData:{}",getEmpData);

        CommonResponse commonResponse = new CommonResponse();

        try {
            Employee employee = new Employee();
            employee.setEmpId(String.valueOf(SequenceGenerator.getEmpSequence(empRepo.sequence())));
            employee.setEmpNIC(getEmpData.getEmpNIC());
            employee.setEmpName(getEmpData.getEmpName());
            employee.setEmpAge(getEmpData.getEmpAge());
            employee.setEmpGender(getEmpData.getEmpGender());
            employee.setEmpAddress(getEmpData.getEmpAddress());
            employee.setEmpSalary(getEmpData.getEmpSalary());

            Employee dbEmployee = empRepo.save(employee);

                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("Employee Created Successfully");
                commonResponse.setPayload(dbEmployee);

        }catch (Exception e){
            log.error("Exception:{}",e.getMessage());
            log.error("Exception:{}",e.getClass());
            throw new Exception(e.getMessage());
        }
        log.debug("END | createEmployee,commonResponse:{}", ServiceHelper.convertToJson(commonResponse));
        return commonResponse;
    }

    @Override
    public CommonResponse deleteEmployee(GetEmpData getEmpData) throws Exception{
        log.debug("START | deleteEmployee,getEmpData:{}",getEmpData.getEmpId());
        CommonResponse commonResponse = new CommonResponse();

        try {

            Optional<Employee> employee = empRepo.findById(getEmpData. getEmpId());
            if (employee.isPresent()) {
                empRepo.deleteById(getEmpData.getEmpId());
                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("Employee Deleted Successfully");
                commonResponse.setPayload(employee.get());

            } else {
                commonResponse.setStatusCode("005");
                commonResponse.setStatusDescription("No Employee Record Found");
                commonResponse.setPayload(getEmpData);                               // see
            }

        }catch (Exception e){
            log.error("Exception:{}",e.getMessage());
            log.error("Exception:{}",e.getClass());
            throw new Exception(e.getMessage());
        }
        log.debug("END | getEmpData,commonResponse:{}", ServiceHelper.convertToJson(commonResponse));
        return commonResponse;
    }

    @Override
    public CommonResponse updateEmployee(GetEmpData getEmpData) throws Exception{
        log.debug("START | updateEmployee,getEmpData:{}",getEmpData.getEmpId());

        CommonResponse commonResponse = new CommonResponse();
        Employee employee = new Employee();
        try {
            Optional<Employee> isEmployee = empRepo.findById(getEmpData. getEmpId());
            if(isEmployee.isPresent()) {

                employee.setEmpId(getEmpData.getEmpId());
                employee.setEmpNIC(getEmpData.getEmpNIC());
                employee.setEmpName(getEmpData.getEmpName());
                employee.setEmpAge(getEmpData.getEmpAge());
                employee.setEmpGender(getEmpData.getEmpGender());
                employee.setEmpAddress(getEmpData.getEmpAddress());
                employee.setEmpSalary(getEmpData.getEmpSalary());

                Employee dbEmployee = empRepo.save(employee);
                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("Employee Updated Successfully");
                commonResponse.setPayload(dbEmployee);
            }else {
                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("No Employee Record Found");
                commonResponse.setPayload(employee);
            }
        }catch (Exception e){
            log.error("Exception:{}",e.getMessage());
            log.error("Exception:{}",e.getClass());
            throw new Exception(e.getMessage());
        }
        log.debug("END | updateEmployee,commonResponse:{}", ServiceHelper.convertToJson(commonResponse));
        return commonResponse;
    }

    @Override
    public CommonResponse getDependentsByEmpId(String empID){
        log.debug("START | getEmpId,EmpID:{}",empID);
        CommonResponse commonResponse = new CommonResponse();

        try {

            /*List<Dependent> dependent = depRepo.findByEmpID(empID);
            if (dependent.isPresent()) {
                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("Employee Successfully");
                commonResponse.setPayload(dependent);
/*
            } else {
                commonResponse.setStatusCode("005");
                commonResponse.setStatusDescription("No Employee Record Found");
                commonResponse.setPayload(empID);
            }*/

        }catch (Exception e){
            log.debug("Exception:{}",e.getMessage());
            log.debug("Exception:{}",e.getMessage());
        }
        log.debug("END | EmployeeDetails:{}", ServiceHelper.convertToJson(commonResponse));
        return commonResponse;
    }
}
