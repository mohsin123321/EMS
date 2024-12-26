package com.example.springframework.model;

import com.example.springframework.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(name = "phone")
    @NonNull
    private String phone;

    @Column(name = "address")
    @NonNull
    private String address;

    @Column(name = "password")
    @NonNull
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    @NonNull
    private Gender gender;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeLeave> leaves;
}


