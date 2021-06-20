package com.example.SMSdemo.service;

import com.example.SMSdemo.controller.PortalController;
import com.example.SMSdemo.entity.EmpEntity;
import com.example.SMSdemo.repository.EmployeeRepository;
import com.example.SMSdemo.request.OtpRequest;
import com.example.SMSdemo.response.PortalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class VerifyOtp {

    @Autowired
    EmployeeRepository repo;

    public PortalResponse verify(@RequestBody OtpRequest req){
//        try{

            PortalResponse portalResponse = new PortalResponse();
            Optional<EmpEntity> empEntity = repo.findById(req.getTo());

            if(empEntity.get().getOtp().equals(req.getOtp())){
                portalResponse.setMessage("login successfull");
                portalResponse.setStatusCode("200");
            } else {
                portalResponse.setMessage("OTP invalid");
                portalResponse.setStatusCode("202");
            }

            return portalResponse;

//        } catch (Exception e){}
//        return null;
    }
}
