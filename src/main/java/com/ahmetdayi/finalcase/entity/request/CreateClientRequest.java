package com.ahmetdayi.finalcase.entity.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientRequest {

    @NotBlank

    private String nationalityId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private double monthlySalary;
    @NotBlank
    private String phoneNumber;

    private LocalDate birthDay;
    @NotNull
    private double guarantee;
}
