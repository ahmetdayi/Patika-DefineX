package com.ahmetdayi.finalcase.repository;

import com.ahmetdayi.finalcase.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByMonthlySalaryGreaterThanEqualAndMonthlySalaryLessThan(double salary1,double salary2);
    List<Client> findByMonthlySalaryLessThan(double salary);
    List<Client> findByMonthlySalaryIsGreaterThanEqual(double salary);

    List<Client> findByCreditScore_CreditScoreLessThan(double creditScore);
    List<Client> findByCreditScore_CreditScoreGreaterThanEqualAndCreditScore_CreditScoreLessThan(
            double creditScore1,double creditScore2);
    List<Client> findByCreditScore_CreditScoreGreaterThanEqual(double creditScore);

    Optional<Client> findByNationalityId(String nationalityId);

    Optional<Client> findByPhoneNumber(String phoneNumber);


}
