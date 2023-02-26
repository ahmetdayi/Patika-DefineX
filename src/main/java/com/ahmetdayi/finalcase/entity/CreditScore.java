package com.ahmetdayi.finalcase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditScore {
    //kullanıcının kredı skorunun tutuldugu sınıf

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double creditScore;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "creditScore")
    private Client client;

    public CreditScore(double creditScore) {
        this.creditScore = creditScore;

    }
}
