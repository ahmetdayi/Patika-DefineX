package com.ahmetdayi.finalcase.repository;


import com.ahmetdayi.finalcase.entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CreditScoreRepository extends JpaRepository<CreditScore, UUID> {

    List<CreditScore> findByCreditScoreLessThan(double creditScore);
    List<CreditScore> findByCreditScoreGreaterThanEqualAndCreditScoreLessThan(
            double creditScore1,double creditScore2);
    List<CreditScore> findByCreditScoreEquals(double creditScore);

}
