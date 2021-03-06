package by.softarex.collectdata.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
//@Configuration
@CrossOrigin(origins = "http://localhost:3000/")
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

        String deletePreflight = request.getHeader("Access-Control-Request-Method");
        if (deletePreflight != null) {
            response.setStatus(200);
            return;
        }

        String loginURI = request.getContextPath() + "/login";
        String logoutURI = request.getContextPath() + "/logout";
        String registrationURI = request.getContextPath() + "/registration";

        boolean loggedIn = session != null && session.getAttribute("authenticated") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean logoutRequest = request.getRequestURI().equals(logoutURI);
        boolean registrationRequest = request.getRequestURI().equals(registrationURI);


        if (registrationRequest || logoutRequest || loggedIn || loginRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(401, "Unauthorized");
        }

    }
}
