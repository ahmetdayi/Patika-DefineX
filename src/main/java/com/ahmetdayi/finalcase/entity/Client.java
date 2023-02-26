package com.ahmetdayi.finalcase.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true,nullable = false)
    private String nationalityId;

    private String firstName;

    private String lastName;

    private double monthlySalary;
    @Column(unique = true,nullable = false)
    private String phoneNumber;

    private LocalDate birthDay;

    private double guarantee;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn
    private CreditScore creditScore;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "client",cascade = CascadeType.ALL)
    private List<Credit> credits;

    public Client(String nationalityId, String firstName, String lastName, double monthlySalary, String phoneNumber, LocalDate birthDay, double guarantee) {
        this.nationalityId = nationalityId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.monthlySalary = monthlySalary;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.guarantee = guarantee;
    }
}
