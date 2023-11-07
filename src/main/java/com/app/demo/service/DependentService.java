package com.app.demo.service;

import com.app.demo.model.CommonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface DependentService {

    public CommonResponse getDependents() throws Exception;
}
