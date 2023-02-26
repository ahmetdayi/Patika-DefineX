package com.ahmetdayi.finalcase.repository;


import com.ahmetdayi.finalcase.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {
}
