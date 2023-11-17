package com.app.project.service.impl;

import com.app.project.entity.Dependent;
import com.app.project.entity.Employee;
import com.app.project.model.CommonResponse;
import com.app.project.repo.DepRepo;
import com.app.project.repo.EmpRepo;
import com.app.project.service.DependentService;
import com.app.project.util.ServiceHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class DependentServiceImpl implements DependentService {

    private final EmpRepo empRepo;
    private final DepRepo depRepo;

    @Autowired
    public DependentServiceImpl(EmpRepo empRepo, DepRepo depRepo){
        this.empRepo = empRepo;
        this.depRepo = depRepo;
    }

    @Override
    public CommonResponse getDependents() throws Exception{
        log.debug("START | getDependents:");
        CommonResponse commonResponse = new CommonResponse();

       try {
            List<Dependent> dependent = depRepo.findAll();
            if (dependent.isEmpty()) {
                commonResponse.setStatusCode("005");
                commonResponse.setStatusDescription("No Dependent Record Found");
            } else {
                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("Success");
            }
           commonResponse.setPayload(dependent);
       }catch (Exception e){
            log.error("Exception:{}",e.getMessage());
            log.error("Exception:{}",e.getClass());
            throw new Exception(e.getMessage());
        }
        log.debug("END | getDependents,commonResponse:{}", ServiceHelper.convertToJson(commonResponse));
        return commonResponse;
    }

    @Override
    public CommonResponse getDependentsByEmpId(String empID) throws Exception{
        log.debug("START | getEmpId,EmpID:{}",empID);
        CommonResponse commonResponse = new CommonResponse();

        try {
            Optional<Employee> optionalEmployee = empRepo.findById(empID);
            log.debug("dependent:{}",optionalEmployee);
            if (optionalEmployee.isPresent()) {
                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("Employee has Dependent");
                Employee employee = optionalEmployee.get();
                commonResponse.setPayload(employee.getDependent());

            } else {
                commonResponse.setStatusCode("005");
                commonResponse.setStatusDescription("No Dependent Record Found");
                commonResponse.setPayload(empID);
            }

        }catch (Exception e){
            log.error("Exception:{}",e.getMessage());
            log.error("Exception:{}",e.getClass());
            throw new Exception(e.getMessage());
        }
        log.debug("END | EmployeeDetails:{}", ServiceHelper.convertToJson(commonResponse));
        return commonResponse;
    }
}
