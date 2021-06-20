package com.example.SMSdemo.service;

import com.example.SMSdemo.entity.EmpEntity;
import com.example.SMSdemo.repository.EmployeeRepository;
import com.example.SMSdemo.request.SmsRequest;
import com.example.SMSdemo.response.PortalResponse;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Optional;

@Service
public class SmsService {

    @Autowired
    EmployeeRepository repo;

    public PortalResponse sendSMS(@RequestBody SmsRequest req){

        try{
            PortalResponse portalResponse = new PortalResponse();
            Optional<EmpEntity> userEntity1 = repo.findById(req.getTo());

            if(userEntity1.isPresent()){
                String str = "";
                String val = "1234567890";
                for(int i = 0; i < 4; i++){
                    int z = (int)(Math.random() * val.length());
                    str = str + z;
                }

                String token= Jwts.builder()
                        .setId(req.getTo())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis()+1000*100))
                        .signWith(SignatureAlgorithm.HS256,str)
                        .compact();
                userEntity1.get().setToken(token);
                userEntity1.get().setKey(str);
                portalResponse.setToken(token);


                String otp = "";
                for(int i = 0; i < 4; i++){
                    int z = (int)(Math.random() * val.length());
                    otp = otp + z;
                }
                userEntity1.get().setOtp(otp);
                repo.save(userEntity1.get());

                Twilio.init("ACd68044b083af93eb0d4218e3dcc8f2eb","3ec9afe28e12025d2401b14cccdd42b0");
                Message.creator(new PhoneNumber(req.getTo()),new PhoneNumber("+13142793269"), otp).create();
                portalResponse.setMessage("Otp send successfully");
                portalResponse.setStatusCode("200");

            } else {
                portalResponse.setMessage("Record not found");
                portalResponse.setStatusCode("202");
            }

            return portalResponse;

        } catch (Exception e){}
        return null;
    }
}
