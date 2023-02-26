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

    private BigDecimal creditScore;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn
    private Client client;

    public CreditScore(BigDecimal creditScore,Client client) {
        this.creditScore = creditScore;
        this.client=client;
    }
}
