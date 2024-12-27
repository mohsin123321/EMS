package com.domain.springframework.controller;

import com.domain.springframework.dto.request.AddEmployeeBody;
import com.domain.springframework.dto.request.UpdateEmployee;
import com.domain.springframework.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasRole(@Role.ADMIN)")
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<String> addEmployee(@Valid @RequestBody AddEmployeeBody addEmployeeBody) {
        // Add employee to database
        String id = employeeService.addEmployee(addEmployeeBody);

        // Return the id of the added employee
        return ResponseEntity.ok().body(id);
    }

    /**
     * Update an employee in the database
     * @param updateEmployee a request body containing updated employee details
     * @param id the id of the employee to update
     * @return a response entity containing the id of the updated employee
     */
    @Operation(summary = "Update Employee", description = "Update an employee in the database by the admin")
    @PreAuthorize("hasRole(@Role.ADMIN) or hasRole(@Role.EMPLOYEE)")
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateEdmployee(@Valid @RequestBody UpdateEmployee updateEmployee, @PathVariable String id) {

        // Add employee to database
        String updatedId = employeeService.updateEmployee(updateEmployee, id);

        // Return the id of the added employee
        return ResponseEntity.ok().body(updatedId);
    }
}
