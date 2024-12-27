package com.domain.springframework.service;

import com.domain.springframework.dto.request.AddEmployeeBody;
import com.domain.springframework.dto.request.UpdateEmployee;
import com.domain.springframework.exception.EmailAlreadyExistsException;
import com.domain.springframework.exception.EmployeeNotFoundException;
import com.domain.springframework.exception.OperationForbiddenException;
import com.domain.springframework.model.Employee;
import com.domain.springframework.repository.EmployeeRespository;
import com.domain.springframework.security.CurrentUserDetails;
import com.domain.springframework.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRespository employeeRespository;

    @Autowired
    public EmployeeService(EmployeeRespository employeeRespository) {
        this.employeeRespository = employeeRespository;
    }

    public String addEmployee(AddEmployeeBody employeeBody) {
        boolean exists = employeeRespository.existsEmployeeByEmail(employeeBody.getEmail());
        if (exists) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Employee employee = Employee.builder()
                .email(employeeBody.getEmail())
                .firstName(employeeBody.getFirstName())
                .lastName(employeeBody.getLastName())
                .address(employeeBody.getAddress())
                .phone(employeeBody.getPhone())
                .password(new BCryptPasswordEncoder().encode(employeeBody.getPassword()))
                .gender(employeeBody.getGender())
                .build();
        return employeeRespository.save(employee).getId().toString();
    }

    public String updateEmployee(UpdateEmployee updateEmployee, String id) {
        CurrentUserDetails currentUser = (CurrentUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Employee employee = employeeRespository.getEmployeeById(UUID.fromString(id));
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        if (currentUser.getRole().equals(Role.EMPLOYEE)){
            if (!employee.getEmail().equals(currentUser.getEmail())) {
                throw new OperationForbiddenException("Operation not allowed");
            }
        }
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setAddress(updateEmployee.getAddress());
        employee.setPhone(updateEmployee.getPhone());

        return employeeRespository.save(employee).getId().toString();
    }
}
