package com.learning.mocktesting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.mocktesting.models.Employee;
import com.learning.mocktesting.service.EmployeeService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(EmployeeController.class)  // Add the controller class here
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // has to be used in case of @WebMvcTest
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getEmployee() throws Exception {
        // Create a mock employee
        Employee employee = new Employee();
        employee.setEmail("sanketv@gmail.com");
        employee.setFirstName("Sanket");
        employee.setLastName("Vyawahare");

        // Mock the service method to return the mock employee
//        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class)))
//                .willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class)))
                .willReturn(employee);
        // Perform the POST request and verify the response
        var response = mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")  // Ensure the path matches
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // Assert the expected status and response content
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is("Sanket")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(employee.getEmail())));
        // use $.size() to match size if the output json is list type
    }

}
