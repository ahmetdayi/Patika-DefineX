package com.ahmetdayi.finalcase.repository;


import com.ahmetdayi.finalcase.entity.CreditResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditResultRepository extends JpaRepository<CreditResult, UUID> {
}
