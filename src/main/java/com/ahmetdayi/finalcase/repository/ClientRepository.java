package com.ahmetdayi.finalcase.repository;

import com.ahmetdayi.finalcase.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
