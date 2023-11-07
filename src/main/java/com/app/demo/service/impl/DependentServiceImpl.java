package com.app.demo.service.impl;

import com.app.demo.entity.Dependent;
import com.app.demo.model.CommonResponse;
import com.app.demo.repo.DepRepo;
import com.app.demo.service.DependentService;
import com.app.demo.util.ServiceHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class DependentServiceImpl implements DependentService {
    @Autowired DepRepo depRepo;

    @Override
    public CommonResponse getDependents() throws Exception{
        log.debug("START | getDependents:");
        CommonResponse commonResponse = new CommonResponse();

       try {
            List<Dependent> dependent = depRepo.findAll();
            if (dependent.isEmpty()) {
                commonResponse.setStatusCode("005");
                commonResponse.setStatusDescription("No Dependent Record Found");
                commonResponse.setPayload(dependent);
            } else {
                commonResponse.setStatusCode("000");
                commonResponse.setStatusDescription("Success");
                commonResponse.setPayload(dependent);
            }
        }catch (Exception e){
            log.error("Exception:{}",e.getMessage());
            log.error("Exception:{}",e.getClass());
            throw new Exception(e.getMessage());
        }
        log.debug("END | getDependents,commonResponse:{}", ServiceHelper.convertToJson(commonResponse));
        return commonResponse;
    }
}
