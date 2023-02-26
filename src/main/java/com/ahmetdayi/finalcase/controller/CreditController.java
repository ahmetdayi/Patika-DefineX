package com.ahmetdayi.finalcase.controller;

import com.ahmetdayi.finalcase.entity.response.CreateCreditResponse;
import com.ahmetdayi.finalcase.entity.response.CreditResponse;
import com.ahmetdayi.finalcase.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/credit")
@RestController
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/calculate")
    public ResponseEntity<CreateCreditResponse> calculate(@RequestParam("clientId") UUID clientId){
        return new ResponseEntity<>(creditService.calculate(clientId), HttpStatus.CREATED);
    }

    @PostMapping("/find")
    public ResponseEntity<CreditResponse> findByNationalityIdAndBirthday(
            @RequestParam("nationalityId") String nationailtyId,
            @RequestParam String birthDay)
    {
        return new ResponseEntity<>(creditService.findByNationalityIdAndBirthday(nationailtyId, birthDay),HttpStatus.OK);
    }
}
