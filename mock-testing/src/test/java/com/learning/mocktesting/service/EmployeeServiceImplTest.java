package com.learning.mocktesting.service;

import com.learning.mocktesting.models.Employee;
import com.learning.mocktesting.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    void saveEmployee() {

        Employee employee = new Employee();

        employee.setEmail("sanketv@gmail.com");
        employee.setFirstName("Sanket");
        employee.setLastName("Vyawahare");

        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        BDDMockito.willDoNothing().given(employeeRepository).deleteById(1L);
        Employee newEmployee = employeeService.saveEmployee(employee);
        assertNotNull(newEmployee, "Object is Null");

    }
}