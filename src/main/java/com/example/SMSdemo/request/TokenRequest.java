package com.example.SMSdemo.request;

import lombok.Data;

@Data
public class TokenRequest {
    private String to;
    private String token;
}
