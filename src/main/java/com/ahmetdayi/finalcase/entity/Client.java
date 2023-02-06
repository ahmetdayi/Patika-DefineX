package com.ahmetdayi.finalcase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nationalityId;

    private String firstName;

    private String lastName;

    private double monthlySalary;

    private String phoneNumber;

    private LocalDate birthDay;

    private BigDecimal guarantee;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "client")
    private CreditScore creditScore;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "client",cascade = CascadeType.ALL)
    private List<Credit> credits;


}
