package com.example.springframework.repository;

import com.example.springframework.dto.response.LoginDBResponse;
import com.example.springframework.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<Admin, UUID> {
    @Query("Select new com.example.springframework.dto.response.LoginDBResponse(email, 'ADMIN', password) from Admin where email = ?1"
            + " union "
            + "Select new com.example.springframework.dto.response.LoginDBResponse(email, 'EMPLOYEE', password) from Employee where email = ?1"
    )
    public Optional<LoginDBResponse> getUserRoleByEmail(String email);
}
