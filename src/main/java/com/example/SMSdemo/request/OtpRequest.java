package com.example.SMSdemo.request;

import lombok.Data;

@Data
public class OtpRequest {
    private String to;
    private String otp;
}
