package com.ahmetdayi.finalcase.controller;

import com.ahmetdayi.finalcase.entity.request.CreateClientRequest;
import com.ahmetdayi.finalcase.entity.request.UpdateClientRequest;
import com.ahmetdayi.finalcase.entity.response.CreateClientResponse;
import com.ahmetdayi.finalcase.entity.response.UpdateClientResponse;
import com.ahmetdayi.finalcase.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @PostMapping("/create")
    public ResponseEntity<CreateClientResponse> create(@RequestBody CreateClientRequest request) {
        return new ResponseEntity<>(clientService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateClientResponse> update(@RequestBody UpdateClientRequest request) {
        return new ResponseEntity<>(clientService.update(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
