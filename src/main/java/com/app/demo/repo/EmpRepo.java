package com.app.demo.repo;

import com.app.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpRepo extends JpaRepository<Employee, String>{
    @Procedure("SEQ_PROCEDURE")
    int sequence();



}
