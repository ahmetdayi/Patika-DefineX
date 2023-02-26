package com.ahmetdayi.finalcase.service;

import com.ahmetdayi.finalcase.entity.CreditScore;
import com.ahmetdayi.finalcase.entity.converter.CreditScoreConverter;
import com.ahmetdayi.finalcase.entity.response.CreateCreditScoreResponse;
import com.ahmetdayi.finalcase.repository.CreditScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditScoreService {

    private final CreditScoreRepository creditScoreRepository;

    private final ClientService clientService;

    private final CreditScoreConverter creditScoreConverter;

    public CreateCreditScoreResponse create (UUID clientId){

        Random random = new Random();
        BigDecimal randomCreditScore = BigDecimal.valueOf(random.nextInt(1000));

        CreditScore creditScore = new CreditScore(
                randomCreditScore,
                clientService.findById(clientId)
        );
        return creditScoreConverter.convert(creditScoreRepository.save(creditScore));
    }


}
