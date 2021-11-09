package com.xxxx.springsecuritydemo.config;

import com.xxxx.springsecuritydemo.entity.User;
import com.xxxx.springsecuritydemo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
@Transactional
public class CustomProvider implements AuthenticationProvider {

    private static Logger logger = Logger.getLogger(CustomProvider.class.getName());

    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    IUserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String account = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        String name = authentication.getName();
        Class<? extends Authentication> aClass = authentication.getClass();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Object details = authentication.getDetails();

//        org.springframework.security.core.userdetails.User user1=(org.springframework.security.core.userdetails.User) authentication.getPrincipal();
//
//        System.out.println("user"+user1);username

        System.out.println("username"+account);
        System.out.println("password"+password);
        System.out.println("remember-me");
        System.out.println("name"+name);
        System.out.println("aClass"+aClass);
        System.out.println("authorities"+authorities);
        System.out.println("details"+details);
        System.out.println(account);
        System.out.println(password);

        User user = null;
        Optional<User> userOptional = userRepository.findByAccount(account);

//        HttpSession session;
//          session.setAttribute("user", user);
//        if (account != "") {
            if (userOptional.isPresent()) {
                user = userOptional.get();
//                if (!encoder.matches(password, user.getPassword())) {
                if(!passwordEncoder.matches(password, user.getPassword()))
//                    if(!passwordEncoder.matches(password,user.getPassword())){
                    throw new AuthenticationServiceException(String.format("please check account or password"));
//                }
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
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_user"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_api"));


        if(user.getRole()==1){

            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_name"));
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_admin"));
        }else {

            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_name"));
        }

        System.out.println("authenticate password：" + password);
        return new UsernamePasswordAuthenticationToken(user, password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("supports");
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
