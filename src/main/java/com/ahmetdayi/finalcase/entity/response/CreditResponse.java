package com.ahmetdayi.finalcase.entity.response;

import com.ahmetdayi.finalcase.entity.CreditResult;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditResponse {

    @NotNull
    private UUID id;
    @NotNull
    private double creditLimit;

    @NotNull
    private CreditResult creditResult;

    @NotNull
    private CreditScore_ClientResponse client;
}
