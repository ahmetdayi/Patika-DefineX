package com.ahmetdayi.finalcase.service;

import com.ahmetdayi.finalcase.core.exception.ClientDoesntExistException;
import com.ahmetdayi.finalcase.core.exception.CreditDoesntExistException;
import com.ahmetdayi.finalcase.core.exception.CreditScoreCalculateException;
import com.ahmetdayi.finalcase.core.exception.constant.Constant;
import com.ahmetdayi.finalcase.entity.Client;
import com.ahmetdayi.finalcase.entity.Credit;
import com.ahmetdayi.finalcase.entity.CreditResult;
import com.ahmetdayi.finalcase.entity.Sms;
import com.ahmetdayi.finalcase.entity.converter.CreditConverter;
import com.ahmetdayi.finalcase.entity.response.CreateCreditResponse;
import com.ahmetdayi.finalcase.entity.response.CreditResponse;
import com.ahmetdayi.finalcase.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final ClientService clientService;

    private final SmsService smsService;
    private final CreditConverter creditConverter;

    //region Credit Calculate
    public CreateCreditResponse create(UUID clientId, double creditLimit, CreditResult creditResult) {
        Client client = clientService.findById(clientId);
        Credit credit = new Credit
                (
                        creditLimit,
                        creditResult,
                        client
                );
        return creditConverter.convert(creditRepository.save(credit));
    }
    public CreateCreditResponse calculate(UUID clientId){


        if (creditScoreLessThan500(clientId) != null){
            return creditScoreLessThan500(clientId);
        } else if (creditScoreGreaterThan500AndLessThan1000(clientId) != null) {
            return creditScoreGreaterThan500AndLessThan1000(clientId);
        } else if (creditScore_CreditScoreEquals(clientId) != null) {
            return creditScore_CreditScoreEquals(clientId);
        }else{
            throw new CreditScoreCalculateException(Constant.CREDIT_SCORE_DOESNT_CALCULATE);
        }

    }

    //500den kucuk
    private CreateCreditResponse creditScoreLessThan500(UUID clientId) {
        List<Client> clients = clientService.findByCreditScoreLessThan(500);
        for (Client client :
                clients) {
            if (client.getId().toString().equals(clientId.toString())){
                CreateCreditResponse createCreditResponse = create(client.getId(), 0, CreditResult.REJECTION);
                sendingSms(client);
                return createCreditResponse;
            }
        }
        return null;
    }

    //500 ıle 1000 arasında
    private CreateCreditResponse creditScoreGreaterThanEqualAndCreditScoreLessThan(double creditLimit) {
        List<Client> clients = clientService
                .findByCreditScoreGreaterThanEqualAndCreditScoreLessThan(
                        500, 1000);
        for (Client client :
                clients) {
               return create(client.getId(), creditLimit, CreditResult.APPROVAL);
        }
        return null;
    }

    private CreateCreditResponse creditScoreGreaterThan500AndLessThan1000(UUID clientId) {
        double creditScore;
        List<Client> clients = clientService
                .findByCreditScoreGreaterThanEqualAndCreditScoreLessThan(
                        500, 1000);
        for (Client client :
                clients) {
            if (client.getId().toString().equals(clientId.toString()) ){
                if (clientService.compareSalaryLessThan(client.getId(), 5000)) {
                    creditScore = 10000 + (client.getGuarantee() / 10);
                    CreateCreditResponse createCreditResponse
                            = creditScoreGreaterThanEqualAndCreditScoreLessThan(creditScore);
                    sendingSms(client);
                    return createCreditResponse;
                } else if (clientService.compareSalaryGreaterThanEqualAndMonthlySalaryLessThan(
                        client.getId(), 5000, 10000)) {
                    creditScore = 20000 + (client.getGuarantee() / 5);
                    CreateCreditResponse createCreditResponse
                            = creditScoreGreaterThanEqualAndCreditScoreLessThan(creditScore);
                    sendingSms(client);
                    return createCreditResponse;
                } else if (clientService.compareSalaryGreaterThan(client.getId(), 10000)) {
                    creditScore = (client.getMonthlySalary() * 2) + (client.getGuarantee() / 4);
                    CreateCreditResponse createCreditResponse
                            = creditScoreGreaterThanEqualAndCreditScoreLessThan(creditScore);
                    sendingSms(client);
                    return createCreditResponse;
                } else {
                    throw new CreditScoreCalculateException(Constant.CREDIT_SCORE_DOESNT_CALCULATE);
                }
            }

        }
        return null;
    }

    //! 1000 den buyuk
    private CreateCreditResponse creditScore_CreditScoreEquals(UUID clientId) {
        List<Client> clients = clientService.findByCreditScore_CreditScoreEquals(1000);
        double creditLimit;
        for (Client client :
                clients) {
            if (client.getId().toString().equals(clientId.toString()) ){
                creditLimit = client.getMonthlySalary() * 4 + (client.getGuarantee() / 2);
                CreateCreditResponse createCreditResponse = create(client.getId(), creditLimit, CreditResult.APPROVAL);
                sendingSms(client);
                return createCreditResponse;
            }
        }
        return null;
    }
//endregion Credit Calculate

    public CreditResponse findByNationalityIdAndBirthday(String nationalityId, String birthday){
        Client client = clientService.findByNationalityId(nationalityId);
        if (client.getBirthDay().toString().equals(birthday)){
            return creditConverter.convertCreditResponse(client.getCredits().get(client.getCredits().size()-1));
        }
        throw new ClientDoesntExistException(Constant.CLIENT_DOESNT_EXIST);
    }
    private void sendingSms(Client client) {
        Sms sms = new Sms(
                client.getPhoneNumber(),
                (client.getNationalityId()
                        +"\n"
                        + client.getCredits().get(client.getCredits().size()-1).getCreditResult()
                        +"\n"
                        + client.getCredits().get(client.getCredits().size()-1).getCreditLimit()
                )
        );
        smsService.SendMessage(sms);
    }
}
