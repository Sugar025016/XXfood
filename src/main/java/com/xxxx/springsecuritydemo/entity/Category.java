package com.xxxx.springsecuritydemo.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author: Product
 * @date: 2021/4/3
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="auto_increment")
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name="category_id")
    private Integer categoryId;

    @Column(name="name",length =255)
    private String categoryName;

//    @Column(name="remark",length =255)
//    private String remark;

    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<Product> productList;


}
