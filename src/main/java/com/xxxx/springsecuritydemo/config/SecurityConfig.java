package com.xxxx.springsecuritydemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xxxx.springsecuritydemo.enums.Rcode;
import com.xxxx.springsecuritydemo.handler.AppsAuthenticationSuccessHandler;
import com.xxxx.springsecuritydemo.handler.MyAccessDeniedHandler;
import com.xxxx.springsecuritydemo.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.io.PrintWriter;

/**
 * @author: SecurityConfig
 * @date: 2020/10/9
 * @description:
 */

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
//public class SecurityConfig {
//    @Configuration
//    @Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //        @Bean
//    PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
    @Autowired
    CustomProvider provider;
    @Autowired
    VerifyCodeFilter verifyCodeFilter;
    @Autowired
    AppsAuthenticationSuccessHandler appsAuthenticationSuccessHandler;
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(provider);  //設定驗證器
        auth.eraseCredentials(false);
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//
//        //放行竟態資源
//        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
//
//    }

    @Bean
        //設定權限大小
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");//注意要空格
        return hierarchy;
    }

    //密碼加密器
    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**", "/resources/**", "/static/**", "/webjars/**").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                //hasRole角色設定，注意在 CustomProvider 裡面設定角色要加ROLE_，grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_admin"));
//                .antMatchers("/user/**").hasRole("user")
                //hasRole角色設定，注意在 CustomProvider 裡面設定角色要加ROLE_，grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_admin"));
                .antMatchers("/user/**").access("hasRole('user')")
                .and()

                //可用formData和Params登入
                .formLogin()
                //登入頁面注意登入後，未定義時預設是login
//                .loginPage("/api/showLogin")
                //登入街口 ， 資料用 POST form-data 接，注意登入後再登入會出現 "status": 404, "error": "Not Found" ，未定義時預設 = .loginPage()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                //自定義接值 密碼名稱 ，未定義時預設是 password
                .passwordParameter("password")

                .successHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(new Response(Rcode.Success)));
                    out.flush();
                    out.close();
                })
                .failureHandler((req, resp, exception) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write(new ObjectMapper().writeValueAsString(new Response(Rcode.Account_Or_Password_Incorrect)));
                            out.flush();
                            out.close();
                        }
                ).and()
                .rememberMe()
                .rememberMeCookieName("remember-me")//表單參數名稱，預設remember-me
                .rememberMeParameter("remember-me")//連嵐氣儲存cookie名，預設remember-me
                .tokenRepository(persistentTokenRepository())
                // 有效时间：单位s
                .tokenValiditySeconds(60)
//                .userDetailsService(userDetailsService)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))//登出路徑和登出方法
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(new Response(Rcode.Success)));
                    out.flush();
                    out.close();
                })
                .invalidateHttpSession(true)//登出後清除session ，預設是true
                .clearAuthentication(true)//登出後清除身份驗證 ，預設是true
                .permitAll()//允許所有請求
                .and()

                //設定慧登入時呼叫API重新定向
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(new Response(Rcode.Error)));
                    out.flush();
                    out.close();
                });
        //設定 CsrfTokenRepository
//        http.csrf().
//        http.csrf().disable();
        //設定慧登入時呼叫API重新定向

        http.csrf().ignoringAntMatchers("/login");
        http.csrf().ignoringAntMatchers("/logout");
        //前後端分離將csrf(X-XSRF-TOKEN或XSRF-TOKEN)存在cookie
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        //將csrf(X-XSRF-TOKEN或XSRF-TOKEN)存在from data
        //http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository());
    }

    //跨域設定，controller那邊也要設定
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.addAllowedOrigin("http://localhost:8080");
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", cors);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter filter = new LoginFilter("/api/login");
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean("objectMapper")
    public ObjectMapper myMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }


}
