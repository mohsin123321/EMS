package com.example.springframework.repository;

import com.example.springframework.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, UUID> {
    boolean existsEmployeeByEmail(String email);
}
