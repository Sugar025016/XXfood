package com.xxxx.springsecuritydemo.entity;



import com.xxxx.springsecuritydemo.model.request.UserRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {
//    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="auto_increment")
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "user_id")
    private Integer id;

    @Email
    @Column(name = "account",length=128,nullable=false)
    private String account;

    @Column(name = "password",length=128,nullable=false)
    private String password;

    @Column(name = "cell_phone",length=15,nullable=false)
    private String cellPhone;

    @Column(name = "email_verification",nullable=false)
    private boolean emailVerification;

    @Column(name = "cell_phone_verification",nullable=false)
    private boolean cellPhoneVerification;

    @Column(name = "nickname",length=50,nullable=false)
    private String nickname;

    @Column(name = "role",nullable=false)
    private int role;

    public User() {
    }

    public User(UserRequest userRequest) {
        BeanUtils.copyProperties(userRequest,this);
    }

    //    public void set(UserRequest userRequest,Detail detail) {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        this.account = userRequest.getAccount();
//        this.password = encoder.encode(userRequest.getPassword());
////        this.password = userRequest.getPassword();
//        this.setDetail(detail);
//    }
//
//    public void updata(UserRequest userRequest,Detail detail) {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        this.account = userRequest.getAccount();
//        this.password = encoder.encode(userRequest.getPassword());
////        this.password = userRequest.getPassword();
//        this.setDetail(detail);
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", emailVerification=" + emailVerification +
                ", cellPhoneVerification=" + cellPhoneVerification +
                ", nickname='" + nickname + '\'' +
                ", role=" + role +
                '}';
    }


    //omit gets and sets
}

