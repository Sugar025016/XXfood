package com.xxxx.springsecuritydemo.service.Impl;


import com.mysql.cj.util.LogUtils;
import com.xxxx.springsecuritydemo.entity.User;
import com.xxxx.springsecuritydemo.enums.Rcode;
import com.xxxx.springsecuritydemo.model.Response;
import com.xxxx.springsecuritydemo.model.request.UserRequest;
import com.xxxx.springsecuritydemo.model.response.UserResponse;
import com.xxxx.springsecuritydemo.repository.IUserRepository;
import com.xxxx.springsecuritydemo.service.IEmailService;
import com.xxxx.springsecuritydemo.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IEmailService emailService;
    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    public UserResponse get(int id) {
//        logger.info("UserService_GetUser");
//        UserResponse userResponse = new UserResponse();
//        String Cid = ContextHolderHandler.getName();
//        System.out.println("account:" + Cid);
//        Optional<User> byAccount = iUserRepository.findByAccount(Cid);
//        if (byAccount.isPresent()) {
//            User user = byAccount.get();
//            if (user.getId() == id) {
//                userResponse.setUser(user);
//            } else {
//                throw new IllegalArgumentException("輸入錯誤");
//            }
//        } else {
//            throw new IllegalArgumentException("帳號有誤請從新登入");
//        }


        return null;
    }



    public Response add(UserRequest userRequest) {
        logger.info("UserService_addUser");
//        if (byAccount.isPresent())
//            throw new IllegalArgumentException("你已經登入");
        System.out.println("進入了UserService的add方法");

        if (iUserRepository.existsByAccount(userRequest.getAccount())) {
            return new Response(Rcode.Account_Duplicate);
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user = new User(userRequest);
        System.out.println(userRequest.getAccount());
        System.out.println("detail");

        iUserRepository.save(user);
        UserDetails userDetails = commaSeparatedStringToAuthorityList(user.getAccount(), user.getPassword());
        if(userDetails==null){
            return new Response(Rcode.Error);
        }
        emailService.sendSimpleMessage("sugar025016@gmail.com","123","test");

        return new Response(Rcode.Success);
    }

    @Override
    public User findByAccount(String name, String password) {


        User user = null;
        Optional<User> userOptional = iUserRepository.findByAccount(name);

//        HttpSession session;
//          session.setAttribute("user", user);


//        if (account != "") {
        if (userOptional.isPresent()) {
            user = userOptional.get();
//                if (!encoder.matches(password, user.getPassword())) {
            if(passwordEncoder.matches(user.getPassword(),password)){
                throw new AuthenticationServiceException(String.format("please check account or password"));
            }
        } else {
            throw new BadCredentialsException("please check account or password");

        }

//        }
//        String passwordAuth = "123456";
//        String encodePwd = encoder.encode(passwordAuth);
//        if(password == null || !encoder.matches(password,encodePwd)){
//            logger.info(String.format("user %s Not found ", password));
//            throw new AuthenticationServiceException(String.format("login fail account = %s ", password));
//        }


        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        //在這裡設定角色(Role)，注意要加上 ROLE_，設定權限(Authority)不用
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_admin"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_user"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_api"));

        if(user.getRole()==1){

            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_admin"));
        }



        return user;
    }

    @Override
    public User getUser(String account) {

//        iUserRepository.findByAccount(account);
        return null;
    }

    public String updata(UserRequest userRequest) {
        logger.info("UserService_updataUser");

        return null;
    }

    public Boolean account(String account) {

        return iUserRepository.existsByAccount(account);
    }

    public UserDetails commaSeparatedStringToAuthorityList(String username, String password){

        return new org.springframework.security.core.userdetails.User(username,password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_user,user"));

    }

}

