package com.ahmetdayi.finalcase.service;

import com.ahmetdayi.finalcase.core.exception.BirthdayDoesntCorrectException;
import com.ahmetdayi.finalcase.core.exception.ClientDoesntExistException;
import com.ahmetdayi.finalcase.core.exception.NationalityIdDoesntCorrectException;
import com.ahmetdayi.finalcase.core.exception.PhoneNumberDoesntCorrectException;
import com.ahmetdayi.finalcase.core.exception.constant.Constant;
import com.ahmetdayi.finalcase.entity.Client;
import com.ahmetdayi.finalcase.entity.Credit;
import com.ahmetdayi.finalcase.entity.CreditResult;
import com.ahmetdayi.finalcase.entity.CreditScore;
import com.ahmetdayi.finalcase.entity.converter.ClientConverter;
import com.ahmetdayi.finalcase.entity.request.CreateClientRequest;
import com.ahmetdayi.finalcase.entity.request.UpdateClientRequest;
import com.ahmetdayi.finalcase.entity.response.CreateClientResponse;
import com.ahmetdayi.finalcase.entity.response.CreateCreditScoreResponse;
import com.ahmetdayi.finalcase.entity.response.UpdateClientResponse;
import com.ahmetdayi.finalcase.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final CreditScoreService creditScoreService;
    private final ClientConverter clientConverter;

    //region CRUD
    public CreateClientResponse create(CreateClientRequest request) {

        Client client = new Client
                (
                        request.getNationalityId(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getMonthlySalary(),
                        request.getPhoneNumber(),
                        request.getBirthDay(),
                        request.getGuarantee()
                );
        clientCheck(client);
        CreditScore creditScore = creditScoreService.create();
        client.setCreditScore(creditScore);
        System.out.println(creditScore);
        return clientConverter.convert(clientRepository.save(client));
    }

    private void clientCheck(Client client) {
        nationalityIdCheck(client.getNationalityId());
        phoneNumberCheck(client.getPhoneNumber());
        birthdayCheck(client.getBirthDay());
    }

    private void birthdayCheck(LocalDate birthDay) {
        if (birthDay.isAfter(LocalDate.now()) || birthDay.toString().equals(LocalDate.now().toString())){
            throw new BirthdayDoesntCorrectException(Constant.BIRTHDAY_DOESNT_CORRECT);
        }
    }

    private void phoneNumberCheck(String phoneNumber) {
        System.out.println(phoneNumber.length());
        if (phoneNumber.length() != 13
                || clientRepository.findByPhoneNumber(phoneNumber).isPresent()
                || !phoneNumber.contains("+")){
            throw new PhoneNumberDoesntCorrectException(Constant.PHONE_NUMBER_DOESNT_CORRECT);
        }
    }

    private void nationalityIdCheck(String nationalityId) {
        if(nationalityId.length() != 11 || clientRepository.findByNationalityId(nationalityId).isPresent()){
            throw new NationalityIdDoesntCorrectException(Constant.NATIONALITY_ID_DOESNT_CORRECT);
        }
    }

    public UpdateClientResponse update(UpdateClientRequest request) {
        Client client = findById(request.getId());
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setGuarantee(request.getGuarantee());
        client.setBirthDay(request.getBirthDay());
        client.setMonthlySalary(request.getMonthlySalary());
        client.setPhoneNumber(request.getPhoneNumber());

        return clientConverter.convertUpdate(clientRepository.save(client));
    }

    public void deleteById(UUID id) {
        clientRepository.deleteById(findById(id).getId());
    }

    //endregion CRUD

    //region Salary Calculate
    protected boolean compareSalaryLessThan(UUID id, double salary) {
        List<Client> clients = clientRepository.findByMonthlySalaryLessThan(salary);
        for (Client client :
                clients) {
            if (client.getId() == id) {
                return true;
            }
        }
        return false;
    }

    protected boolean compareSalaryGreaterThan(UUID id, double salary){
        List<Client> clients = clientRepository.findByMonthlySalaryIsGreaterThanEqual(salary);

        for (Client client :
                clients) {

            if (client.getId() == id) {
                return true;
            }
        }
        return false;
    }

    protected boolean compareSalaryGreaterThanEqualAndMonthlySalaryLessThan(UUID id, double salary1, double salary2) {
        List<Client> clients = clientRepository
                .findByMonthlySalaryGreaterThanEqualAndMonthlySalaryLessThan(salary1, salary2);

        for (Client client :
                clients) {

            if (client.getId() == id) {
                return true;
            }
        }
        return false;
    }
    //endregion Salary Calculate

    //region CreditScore Compare

    protected List<Client> findByCreditScoreLessThan(double creditScore){
        return clientRepository.findByCreditScore_CreditScoreLessThan(creditScore);
    }

    protected List<Client> findByCreditScoreGreaterThanEqualAndCreditScoreLessThan(
            double creditScore1,double creditScore2){
        return clientRepository.findByCreditScore_CreditScoreGreaterThanEqualAndCreditScore_CreditScoreLessThan(creditScore1, creditScore2);
    }

    protected List<Client> findByCreditScore_CreditScoreEquals(double creditScore){
        return clientRepository.findByCreditScore_CreditScoreGreaterThanEqual(creditScore);
    }

    //endregion CreditScore Compare


    protected Client findById(UUID id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ClientDoesntExistException(Constant.CLIENT_DOESNT_EXIST));
    }
    protected Client findByNationalityId(String nationalityId){
        return clientRepository.findByNationalityId(nationalityId).orElseThrow(
                ()-> new ClientDoesntExistException(Constant.CLIENT_DOESNT_EXIST));
    }

}
