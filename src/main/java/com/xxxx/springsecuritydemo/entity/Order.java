package com.xxxx.springsecuritydemo.entity;//package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author: Order
 * @date: 2021/4/10
 * @description:
 */
//@Getter
//@Setter
//@Entity
//@Table(name = "order")
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="auto_increment")
//    @GenericGenerator(name = "auto_increment", strategy = "native")
//    @Column(name="order_id")
//    private Integer orderId;
//
//    @Column(name = "address")
//    private String address;
//
//    @A(name = "address")
//    private User user;
//
//    @Column(name = "creat_date")
//    private Timestamp creatDate;
//
//
//
//    }
