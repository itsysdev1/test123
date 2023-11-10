package com.app.project.repo;

import com.app.project.entity.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepRepo extends JpaRepository<Dependent,String> {

  // List<Dependent> findByEmpID(String empId);


}
