package com.domain.springframework.model;

import com.domain.springframework.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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


