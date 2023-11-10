package com.app.project.repo;

import com.app.project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends JpaRepository<Employee, String>{
    @Procedure("SEQ_PROCEDURE")
    int sequence();



}
