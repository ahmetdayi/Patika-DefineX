package com.ahmetdayi.finalcase.service;

import com.ahmetdayi.finalcase.core.exception.ClientDoesntExistException;
import com.ahmetdayi.finalcase.core.exception.constant.Constant;
import com.ahmetdayi.finalcase.entity.Client;
import com.ahmetdayi.finalcase.entity.converter.ClientConverter;
import com.ahmetdayi.finalcase.entity.request.CreateClientRequest;
import com.ahmetdayi.finalcase.entity.request.UpdateClientRequest;
import com.ahmetdayi.finalcase.entity.response.CreateClientResponse;
import com.ahmetdayi.finalcase.entity.response.UpdateClientResponse;
import com.ahmetdayi.finalcase.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientConverter clientConverter;

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
        return clientConverter.convert(clientRepository.save(client));
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

    protected Client findById(UUID id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ClientDoesntExistException(Constant.CLIENT_DOESNT_EXIST));
    }

}
