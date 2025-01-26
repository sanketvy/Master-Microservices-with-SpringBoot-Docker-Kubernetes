package com.learning.mocktesting.service;

import com.learning.mocktesting.models.Employee;
import com.learning.mocktesting.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {

        System.out.println("SaveEmployee Called");

        return employeeRepository.save(employee);
    }

}
