package com.app.demo.repo;

import com.app.demo.entity.Dependent;
import com.app.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepRepo extends JpaRepository<Dependent,String> {

  // List<Dependent> findByEmpID(String empId);


}
