package com.learning.mocktesting.repository;

import com.learning.mocktesting.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {


    public Optional<Employee> findByEmail(String email);


}
