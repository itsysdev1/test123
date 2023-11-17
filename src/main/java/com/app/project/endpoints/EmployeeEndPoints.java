package com.app.project.endpoints;

import com.app.project.model.CommonResponse;
import com.app.project.model.GetEmpData;
import com.app.project.service.EmployeeService;
import com.app.project.util.ServiceHelper;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("api/v1")
public class EmployeeEndPoints {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeEndPoints(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<CommonResponse> getEmployee(){
        ThreadContext.put("req_id", ServiceHelper.getUUID());
        log.debug("Employees:");

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = employeeService.getEmployee();
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.ok().body(commonResponse);
        }catch (Exception e){
            commonResponse.setPayload(commonResponse);
            commonResponse.setStatusCode("002");
            commonResponse.setStatusDescription("General Error");
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.internalServerError().body(commonResponse);
        }
        finally {
            ThreadContext.clearMap();
        }
    }

    @GetMapping("/employee/{emp_ID}")
    public ResponseEntity<CommonResponse> getEmployeeById(@PathVariable String emp_ID ){
        ThreadContext.put("req_id", ServiceHelper.getUUID());
        log.debug("getEmpData:{}",ServiceHelper.convertToJson(emp_ID));

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = employeeService.getEmployeeById(emp_ID);
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.ok().body(commonResponse);
        }catch (Exception e){
            commonResponse.setPayload(e);
            commonResponse.setStatusCode("002");
            commonResponse.setStatusDescription("General Error");
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.internalServerError().body(commonResponse);
        }
        finally {
            ThreadContext.clearMap();
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<CommonResponse> createEmployee(@RequestBody GetEmpData getEmpData){
        ThreadContext.put("req_id", ServiceHelper.getUUID());
        log.debug("getEmpData:{}",ServiceHelper.convertToJson(getEmpData));

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = employeeService.createEmployee(getEmpData);
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.ok().body(commonResponse);
        }catch (Exception e){
            commonResponse.setStatusCode("002");
            commonResponse.setStatusDescription("General Error");
            commonResponse.setPayload(getEmpData);
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.internalServerError().body(commonResponse);
        } finally {
            ThreadContext.clearMap();
        }
    }

    @PostMapping("/employee/delete/{emp_ID}")
    public ResponseEntity<CommonResponse> deleteEmployee(@PathVariable String emp_ID){
        ThreadContext.put("req_id", ServiceHelper.getUUID());
        log.debug("getEmpData:{}",ServiceHelper.convertToJson(emp_ID));

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = employeeService.deleteEmployee(emp_ID);
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.ok().body(commonResponse);
        }catch (Exception e){
            commonResponse.setStatusCode("002");
            commonResponse.setStatusDescription("General Error");
            commonResponse.setPayload(emp_ID);
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.internalServerError().body(commonResponse);
        } finally {
            ThreadContext.clearMap();
        }
    }

    @PostMapping("/employee/{emp_ID}")
    public ResponseEntity<CommonResponse> updateEmployee(@PathVariable String emp_ID, @RequestBody GetEmpData empData){
        ThreadContext.put("req_id", ServiceHelper.getUUID());
        log.debug("getEmpID:{} getEmpData:{}",ServiceHelper.convertToJson(emp_ID),ServiceHelper.convertToJson(empData));

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = employeeService.updateEmployee(emp_ID, empData);
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.ok().body(commonResponse);
        }catch (Exception e){
            commonResponse.setStatusCode("002");
            commonResponse.setStatusDescription("General Error");
            commonResponse.setPayload(emp_ID);
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.internalServerError().body(commonResponse);
        } finally {
            ThreadContext.clearMap();
        }
    }




}
