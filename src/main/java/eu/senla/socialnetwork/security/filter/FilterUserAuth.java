package eu.senla.socialnetwork.security.filter;

import eu.senla.socialnetwork.service.impl.TokenService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class FilterUserAuth extends GenericFilterBean {

    private final TokenService tokenService;

    public FilterUserAuth(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(tokenService.getAuthentication(servletRequest).orElse(null));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
