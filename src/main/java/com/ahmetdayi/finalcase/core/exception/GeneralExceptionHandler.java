package com.ahmetdayi.finalcase.core.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(BirthdayDoesntCorrectException.class)
    public ResponseEntity<?> birthdayDoesntCorrectExceptionHandler(BirthdayDoesntCorrectException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NationalityIdDoesntCorrectException.class)
    public ResponseEntity<?> NationalityIdDoesntCorrectExceptionHandler(NationalityIdDoesntCorrectException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PhoneNumberDoesntCorrectException.class)
    public ResponseEntity<?> PhoneNumberDoesntCorrectExceptionHandler(PhoneNumberDoesntCorrectException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SmsException.class)
    public ResponseEntity<?>SmsExceptionHandler(SmsException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ClientDoesntExistException.class)
    public ResponseEntity<?> clientDoesntExistExceptionHandler(ClientDoesntExistException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CreditDoesntExistException.class)
    public ResponseEntity<?> creditDoesntExistExceptionHandler(CreditDoesntExistException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }




}
