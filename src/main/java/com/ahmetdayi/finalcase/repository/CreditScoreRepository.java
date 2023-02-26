package com.ahmetdayi.finalcase.repository;


import com.ahmetdayi.finalcase.entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditScoreRepository extends JpaRepository<CreditScore, UUID> {

}
