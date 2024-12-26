package com.domain.springframework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class EmployeeLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "start_date", nullable = false)
    @NonNull
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @NonNull
    private Date endDate;

    @Column(name = "reason", nullable = false)
    @NonNull
    private String reason;

    @Column(name = "approved", nullable = false)
    @NonNull
    private boolean approved;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
