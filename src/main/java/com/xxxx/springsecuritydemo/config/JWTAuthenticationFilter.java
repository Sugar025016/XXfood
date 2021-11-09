//package com.xxxx.springsecuritydemo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author: JWTAuthenticationFilter
// * @date: 2021/10/14
// * @description:
// */
//@Component
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//    @Autowired
//    private JWTService jwtService;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain chain) throws ServletException, IOException {
//        // TODO
//    }
//}
