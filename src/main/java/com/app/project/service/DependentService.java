package com.app.project.service;

import com.app.project.model.CommonResponse;
import org.springframework.stereotype.Component;

@Component
public interface DependentService {

    public CommonResponse getDependents() throws Exception;

    public CommonResponse getDependentsByEmpId (String EmpId) throws Exception;
}
