package com.ahmetdayi.finalcase.entity.converter;

import com.ahmetdayi.finalcase.entity.CreditScore;
import com.ahmetdayi.finalcase.entity.response.CreateCreditScoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CreditScoreConverter {
    public final ClientConverter clientConverter;
    
    public CreateCreditScoreResponse convert(CreditScore from){
        return new CreateCreditScoreResponse
                (
                        from.getId(),
                        from.getCreditScore(),
                        clientConverter.convertCreditScore_Client(from.getClient())
                );
    }
}
