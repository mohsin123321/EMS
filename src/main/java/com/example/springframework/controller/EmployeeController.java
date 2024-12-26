package com.example.springframework.controller;

import com.example.springframework.dto.request.AddEmployeeBody;
import com.example.springframework.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${service.endpoint}")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * Add an employee to the database
     * @param addEmployeeBody a request body containing employee details
     * @return a response entity containing the id of the added employee
     */
    @Operation(summary = "Add Employee", description = "Add an employee to the database by the admin")
    @PreAuthorize("hasRole(Role.ADMIN)")
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<String> addEmployee(@Valid @RequestBody AddEmployeeBody addEmployeeBody) {
        // Add employee to database
        String id = employeeService.addEmployee(addEmployeeBody);

        // Return the id of the added employee
        return ResponseEntity.ok().body(id);
    }
}
