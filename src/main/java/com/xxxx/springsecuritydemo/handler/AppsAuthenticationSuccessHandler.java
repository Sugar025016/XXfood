package com.xxxx.springsecuritydemo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author: AppsAuthenticationSuccessHandler
 * @date: 2020/10/13
 * @description:
 */
@Component
public class AppsAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//    @Autowired
//    SystemService sysService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            // 將 Object 轉換成 Map
            ObjectMapper oMapper = new ObjectMapper();
            Map<String, String> authDetail = oMapper.convertValue(authentication.getDetails(), Map.class);

            session.setAttribute("authDetail","*************************");
            System.out.println("*******************"+authDetail);
//            Whois whois = sysService.setWhoIsOnline( authDetail , authentication.getName());
        }
//        if (authentication.isAuthenticated()) {
//            response.sendRedirect("/auth/home");
//        } else {
            response.sendRedirect("/admin/product");
//        }
    }
}
