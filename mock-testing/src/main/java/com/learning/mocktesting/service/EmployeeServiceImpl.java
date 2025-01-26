package com.learning.mocktesting.service;

import com.learning.mocktesting.models.Employee;
import com.learning.mocktesting.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepository employeeRepository;

    EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository =employeeRepository;
    }
    @Override
    public Employee saveEmployee(Employee employee) {

        System.out.println("SaveEmployee Called");

        return employeeRepository.save(employee);
    }

}
