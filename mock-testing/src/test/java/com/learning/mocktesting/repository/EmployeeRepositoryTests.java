package com.learning.mocktesting.repository;


import com.learning.mocktesting.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EmployeeRepositoryTests {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void createUser_testCase(){

        Employee employee = new Employee();
        employee.setFirstName("Sanket");
        employee.setLastName("Vyawahare");
        employee.setEmail("srvyawahare18@gmail.com");

        Employee savedEmp = employeeRepository.save(employee);

        Assertions.assertEquals(1, savedEmp.getId(), "Employee Saved with - " + savedEmp.getId());
        Assertions.assertNotNull(savedEmp);
    }
}
