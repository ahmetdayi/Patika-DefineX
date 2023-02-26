package com.ahmetdayi.finalcase.service;

import com.ahmetdayi.finalcase.entity.CreditScore;

import com.ahmetdayi.finalcase.repository.CreditScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class CreditScoreService {

    private final CreditScoreRepository creditScoreRepository;


    public CreditScore create (){

        Random random = new Random();
        double randomCreditScore = random.nextInt(1000);

        CreditScore creditScore = new CreditScore(
                randomCreditScore
        );
        return creditScoreRepository.save(creditScore);
    }



}
