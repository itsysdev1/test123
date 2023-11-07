package com.app.demo.endpoints;

import com.app.demo.model.CommonResponse;
import com.app.demo.model.GetEmpData;
import com.app.demo.service.EmployeeService;
import com.app.demo.util.ServiceHelper;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("api/v1")
public class EmployeeEndPoints {

    @Autowired
    private EmployeeService employeeService;

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

    @GetMapping("/getEmpById")
    public ResponseEntity<CommonResponse> getEmployeeById(@RequestBody GetEmpData getEmpData){
        ThreadContext.put("req_id", ServiceHelper.getUUID());
        log.debug("getEmpData:{}",ServiceHelper.convertToJson(getEmpData));

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = employeeService.getEmployeeById(getEmpData);
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.ok().body(commonResponse);
        }catch (Exception e){
            commonResponse.setPayload(getEmpData);
            commonResponse.setStatusCode("002");
            commonResponse.setStatusDescription("General Error");
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.internalServerError().body(commonResponse);
        }
        finally {
            ThreadContext.clearMap();
        }
    }

    @PostMapping("/createEmp")
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

    @PostMapping("/deleteEmp")
    public ResponseEntity<CommonResponse> deleteEmployee(@RequestBody GetEmpData getEmpData){
        ThreadContext.put("req_id", ServiceHelper.getUUID());
        log.debug("getEmpData:{}",ServiceHelper.convertToJson(getEmpData.getEmpId()));

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = employeeService.deleteEmployee(getEmpData);
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

    @PostMapping("/updateEmp")
    public ResponseEntity<CommonResponse> updateEmployee(@RequestBody GetEmpData getEmpData){
        ThreadContext.put("req_id", ServiceHelper.getUUID());
        log.debug("getEmpData:{}",ServiceHelper.convertToJson(getEmpData.getEmpId()));

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = employeeService.updateEmployee(getEmpData);
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

    @GetMapping("/getDependentsByID/{emp_ID}")
    public ResponseEntity<CommonResponse> getDependentsByEmpId(@PathVariable("emp_ID") String empId){
        ThreadContext.put("req_id", ServiceHelper.getUUID());
        log.debug("EmpID:{}",ServiceHelper.convertToJson(empId));

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = employeeService.getDependentsByEmpId(empId);
            return ResponseEntity.ok().body(commonResponse);
        }catch (Exception e){
            commonResponse.setStatusCode("002");
            commonResponse.setStatusDescription("General Error");
            commonResponse.setPayload(empId);
            return ResponseEntity.internalServerError().body(commonResponse);
        } finally {
            ThreadContext.clearMap();
        }
    }


}
