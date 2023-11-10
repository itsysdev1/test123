package com.app.project.service.impl;

import com.app.project.entity.Employee;
import com.app.project.model.CommonResponse;
import com.app.project.model.GetEmpData;
import com.app.project.repo.DepRepo;
import com.app.project.repo.EmpRepo;
import com.app.project.service.EmployeeService;
import com.app.project.util.SequenceGenerator;
import com.app.project.util.ServiceHelper;
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
        log.debug("START | getEmployee:");
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
        log.debug("END | getEmployee,CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
        return commonResponse;
    }

    @Override
    public CommonResponse getEmployeeById(String employee_ID) throws Exception{
        log.debug("START | getEmployeeById,getEmpData:{}",employee_ID);
        CommonResponse commonResponse = new CommonResponse();

        try {

            Optional<Employee> employee = empRepo.findById(employee_ID);
            if (employee.isPresent()) {
                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("Success");
                commonResponse.setPayload(employee.get());

            } else {
                commonResponse.setStatusCode("005");
                commonResponse.setStatusDescription("No Employee Record Found");
                commonResponse.setPayload(employee_ID);
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
    public CommonResponse deleteEmployee(String employee_ID) throws Exception{
        log.debug("START | deleteEmployee,getEmpData:{}",employee_ID);
        CommonResponse commonResponse = new CommonResponse();

        try {

            Optional<Employee> employee = empRepo.findById(employee_ID);
            if (employee.isPresent()) {
                empRepo.deleteById(employee_ID);
                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("Employee Deleted Successfully");
                commonResponse.setPayload(employee.get());

            } else {
                commonResponse.setStatusCode("005");
                commonResponse.setStatusDescription("No Employee Record Found");
                commonResponse.setPayload(employee_ID);                               // see
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
    public CommonResponse updateEmployee(String employee_ID, GetEmpData getEmpData) throws Exception{
        log.debug("START | updateEmployee,getEmpData:{}{}",employee_ID, getEmpData);

        CommonResponse commonResponse = new CommonResponse();
        Employee employee = new Employee();
        try {
            Optional<Employee> isEmployee = empRepo.findById(employee_ID);

            if(isEmployee.isPresent()) {
                employee.setEmpId(employee_ID);
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


}
