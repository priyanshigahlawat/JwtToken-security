package com.example.SMSdemo.response;

import lombok.Data;

@Data
public class PortalResponse {
    private String message;
    private String statusCode;
    private String token;
}
