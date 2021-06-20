package com.example.SMSdemo.controller;

import com.example.SMSdemo.request.OtpRequest;
import com.example.SMSdemo.request.SmsRequest;
import com.example.SMSdemo.request.TokenRequest;
import com.example.SMSdemo.response.PortalResponse;
import com.example.SMSdemo.service.SmsService;
import com.example.SMSdemo.service.VerifyOtp;
import com.example.SMSdemo.service.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmplyeeController {

    @Autowired
    SmsService service;

    @Autowired
    VerifyOtp otpService;

    @Autowired
    VerifyToken tokenService;

    @PostMapping("/sendSms")
    public PortalResponse sendSMS(@RequestBody SmsRequest req){
        return service.sendSMS(req);
    }

    @PostMapping("/verifyOtp")
    public PortalResponse verifyOtp(@RequestBody OtpRequest req){
        return otpService.verify(req);
    }

    @PostMapping("/verifyToken")
    public PortalResponse verifyToken(@RequestBody TokenRequest req){
        return tokenService.verifyToken(req);
    }
}
