package com.app.demo.endpoints;

import com.app.demo.model.CommonResponse;
import com.app.demo.service.DependentService;
import com.app.demo.util.ServiceHelper;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Log4j2
@RestController
@RequestMapping("api/v1")
public class DependentEndPoints {

    @Autowired
    private DependentService dependentService;

    @GetMapping("/dependents")
    public ResponseEntity<CommonResponse> getDependents(){
        ThreadContext.put("req_id", ServiceHelper.getUUID());
        log.debug("getDependents:");

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = dependentService.getDependents();
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.ok().body(commonResponse);
        }catch (Exception e){
            commonResponse.setStatusCode("002");
            commonResponse.setStatusDescription("General Error");
            commonResponse.setPayload(commonResponse);
            log.debug("CommonResponse:{}",ServiceHelper.convertToJson(commonResponse));
            return ResponseEntity.internalServerError().body(commonResponse);
        }
        finally {
            ThreadContext.clearMap();
        }

    }

}
