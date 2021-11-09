package com.xxxx.springsecuritydemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Cart
 * @date: 2021/10/4
 * @description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "auto_increment")
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "cart_id")
    private Integer id;

    @Column(name = "dty")
    private int qty;

    @Column(name = "add_date")
    private Date addDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;


    public Cart(Product product, User user, int qty) {
        this.product = product;
        this.user = user;
        this.qty = qty;
        this.addDate = new Date();

    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", qty=" + qty +
                ", addDate=" + addDate +
                ", user=" + user.getId() +
                ", product=" + product +
                '}';
    }
}
