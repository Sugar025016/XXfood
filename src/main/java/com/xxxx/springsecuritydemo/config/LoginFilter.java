package com.xxxx.springsecuritydemo.config;

/**
 * @author: LoginFilter
 * @date: 2021/10/9
 * @description:
 */
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class LoginFilter extends AbstractAuthenticationProcessingFilter { //攔截登入url

    private ObjectMapper mapper = new ObjectMapper();

    public LoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
//        this.setAuthenticationSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
////            LoginResponse response = new LoginResponse(true, authentication.getPrincipal(), getXsfToken(httpServletRequest, httpServletResponse));
//            httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
//            httpServletResponse.getWriter().println(mapper.writeValueAsString(response));
//            HttpSession session = httpServletRequest.getSession();
//            session.setMaxInactiveInterval(-1);
//        });
//        this.setAuthenticationFailureHandler((httpServletRequest, httpServletResponse, e) -> {
////            CommonResponse response = new CommonResponse(AuthenticationErrorCode.LOGIN_FAILED, e.getMessage());
//            httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
//            httpServletResponse.setStatus(400);
//            httpServletResponse.getWriter().println(mapper.writeValueAsString(response));
//        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException {
        if (httpServletRequest.getMethod().equals("POST") && httpServletRequest.getContentType().startsWith("application/json")) {
            JsonNode root = mapper.readTree(httpServletRequest.getInputStream());
            return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(root.get("account").asText(), root.get("password").asText()));
        } else {
            throw new BadCredentialsException("method not support");
        }
    }
    //取得Token
    private String getXsfToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String result = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("XSRF-TOKEN")) {
                    result = cookie.getValue();
                    break;
                }
            }
        }
        if (result == null) {
            Collection<String> headers = httpServletResponse.getHeaders("Set-Cookie");
            for (String header : headers) {
                if (header.contains("XSRF-TOKEN")) {
                    result = header.substring(header.indexOf("=") + 1, header.indexOf(";"));
                    break;
                }
            }
        }
        return result;
    }
}
