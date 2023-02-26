package com.ahmetdayi.finalcase.entity.converter;

import com.ahmetdayi.finalcase.entity.Client;
import com.ahmetdayi.finalcase.entity.response.CreateClientResponse;
import com.ahmetdayi.finalcase.entity.response.UpdateClientResponse;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConverter {

    public CreateClientResponse convert(Client from){
        return new CreateClientResponse
                (
                        from.getId(),
                        from.getNationalityId(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getMonthlySalary(),
                        from.getPhoneNumber(),
                        from.getBirthDay(),
                        from.getGuarantee()
                );
    }

    public UpdateClientResponse convertUpdate(Client from){
        return new UpdateClientResponse
                (
                        from.getId(),
                        from.getNationalityId(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getMonthlySalary(),
                        from.getPhoneNumber(),
                        from.getBirthDay(),
                        from.getGuarantee()
                );
    }
}
