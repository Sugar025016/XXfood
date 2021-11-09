package com.xxxx.springsecuritydemo.entity;//package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: OrderDetail
 * @date: 2021/4/10
 * @description:
 */
//@Getter
//@Setter
//@Entity
//@Table(name = "order_detail")
//public class OrderDetail {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="auto_increment")
//    @GenericGenerator(name = "auto_increment", strategy = "native")
//    @Column(name="order_detail_id")
//    private Integer orderDetailId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name="order_id")
//    private Order order;
//
//    @OneToMany(mappedBy="product", cascade={CascadeType.PERSIST, CascadeType.MERGE})
//    @Column(name = "product_id")
//    private List<Product> productList;
//
//    @Column(name = "quantity")
//    private int quantity;
//
//    @Column(name="price",nullable =false )
//    private int price;
//
//    @Column(name="origin_price",nullable =false )
//    private int originPrice;
//}
