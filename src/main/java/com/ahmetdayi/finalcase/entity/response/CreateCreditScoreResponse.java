package com.ahmetdayi.finalcase.entity.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditScoreResponse {

    @NotNull
    private UUID id;
    @NotNull
    private double creditScore;

    @NotNull
    private CreditScore_ClientResponse client;
}
