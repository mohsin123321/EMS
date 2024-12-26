package com.domain.springframework.service;

import com.domain.springframework.dto.request.AddEmployeeBody;
import com.domain.springframework.exception.EmailAlreadyExistsException;
import com.domain.springframework.model.Employee;
import com.domain.springframework.repository.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Employee employee = new Employee();
        employee.setFirstName(employeeBody.getFirstName());
        employee.setLastName(employeeBody.getLastName());
        employee.setEmail(employeeBody.getEmail());
        employee.setAddress(employeeBody.getAddress());
        employee.setPhone(employeeBody.getPhone());
        employee.setPassword(employeeBody.getPassword());

        return employeeRespository.save(employee).getId().toString();
    }

}
