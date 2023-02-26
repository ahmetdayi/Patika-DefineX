package com.ahmetdayi.finalcase.entity.converter;

import com.ahmetdayi.finalcase.entity.Credit;
import com.ahmetdayi.finalcase.entity.response.CreateCreditResponse;
import com.ahmetdayi.finalcase.entity.response.CreditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CreditConverter {

    private final ClientConverter clientConverter;

    public CreateCreditResponse convert(Credit from){
        return new CreateCreditResponse
                (
                        from.getId(),
                        from.getCreditLimit(),
                        from.getCreditResult(),
                        clientConverter.convertCreditScore_Client(from.getClient())
                );
    }
    public CreditResponse convertCreditResponse(Credit from){
        return new CreditResponse
                (
                        from.getId(),
                        from.getCreditLimit(),
                        from.getCreditResult(),
                        clientConverter.convertCreditScore_Client(from.getClient())
                );
    }

}
