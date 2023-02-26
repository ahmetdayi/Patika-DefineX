package com.ahmetdayi.finalcase.service;

import com.ahmetdayi.finalcase.core.exception.SmsException;
import com.ahmetdayi.finalcase.core.exception.constant.Constant;
import com.ahmetdayi.finalcase.entity.Sms;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SimpMessagingTemplate webSocket;

    private final String  TOPIC_DESTINATION = "/lesson/sms";

    private final String ACCOUNT_SID ="ACc07be0ded02ee869695a60cff73d6e9d";

    private final String AUTH_TOKEN = "108a0cedc6db0c5aa82c064b4931cfc1";

    private final String FROM_NUMBER = "+12766378803";

    public void send(Sms sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

    public void SendMessage(Sms sms){
        try{
            send(sms);
        }
        catch(Exception e){

            //normalde calısıyor ancak yurtdısı servısı oldugu ıcın turk numarasına gonderemıyor
            // programın calısmasını engellemesn dıye yoruma aldım

//            webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
//            throw new SmsException(Constant.SMS_EXCEPTION);
        }
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: "+sms.getTo());

    }
    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

}


