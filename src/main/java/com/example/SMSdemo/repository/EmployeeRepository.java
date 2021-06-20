package com.example.SMSdemo.repository;

import com.example.SMSdemo.entity.EmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmpEntity,String> {
    public EmpEntity findByOtp(String otp);
}

