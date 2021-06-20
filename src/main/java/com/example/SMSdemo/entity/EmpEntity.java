package com.example.SMSdemo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "EmpTable")
public class EmpEntity {
    @Id
    private String phone;

    @Column
    private String token;

    @Column
    private String otp;

    @Column
    private  String key;
}
