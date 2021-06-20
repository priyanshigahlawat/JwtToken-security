package com.example.SMSdemo.service;

import com.example.SMSdemo.entity.EmpEntity;
import com.example.SMSdemo.repository.EmployeeRepository;
import com.example.SMSdemo.request.TokenRequest;
import com.example.SMSdemo.response.PortalResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;

@Service
public class VerifyToken {

    @Autowired
    EmployeeRepository repo;

    public PortalResponse verifyToken(@RequestBody TokenRequest req){
        PortalResponse portalResponse = new PortalResponse();
        Optional<EmpEntity> empEntity = repo.findById(req.getTo());

        try{
            Claims claim= Jwts.parser()
                    .setSigningKey(empEntity.get().getKey())
                    .parseClaimsJws(empEntity.get().getToken())
                    .getBody();

            System.out.println(claim.getId() + "   " + claim.getExpiration());

            if(claim.getExpiration().before(new Date(System.currentTimeMillis()))){
                portalResponse.setMessage("invalid user");
                portalResponse.setStatusCode("202");
            }
            else if(empEntity.get().getToken().equals(req.getToken())){
                portalResponse.setMessage("valid user");
                portalResponse.setStatusCode("200");
            } else {
                portalResponse.setMessage("invalid user");
                portalResponse.setStatusCode("202");
            }
        } catch (SignatureException ex){
            portalResponse.setMessage("invalid user");
            portalResponse.setStatusCode("202");
        } catch (ExpiredJwtException ex){
            portalResponse.setMessage("invalid user");
            portalResponse.setStatusCode("202");
        }
        return portalResponse;
    }
}
