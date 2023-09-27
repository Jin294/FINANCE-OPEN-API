package com.iNine.resource.common.filter;

import com.iNine.resource.common.entity.ServiceProvider;
import com.iNine.resource.common.exception.CommonException;
import com.iNine.resource.common.exception.ExceptionType;
import com.iNine.resource.domain.serviceprovider.repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

//@RequiredArgsConstructor
@Slf4j
public class ApiTokenFilter implements Filter {
    private static final String[] checklist = {"/api/exchange/**"};
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authorizationToken = httpServletRequest.getHeader("Authorization");
        String requestURI = httpServletRequest.getRequestURI();

        log.info("api token filter begin");
        log.info("token:{}", authorizationToken);
        if (authorizationToken != null && authorizationToken.startsWith("123456 ")) {
            String token = authorizationToken.substring(7);
            log.info("token");
            Optional<ServiceProvider> serviceProvider = serviceProviderRepository.selectByApiToken(token);
            if(serviceProvider.isEmpty()) {
                log.info("invalid token");
                throw new CommonException(ExceptionType.JWT_TOKEN_INVALID);
            }
        }
        else {
            log.info("no token");
            throw new CommonException(ExceptionType.JWT_TOKEN_INVALID);
        }
        chain.doFilter(servletRequest, response);
    }

    private boolean checkPath(String requestURI) {
        log.info("check path:{}", PatternMatchUtils.simpleMatch(checklist, requestURI));
        return PatternMatchUtils.simpleMatch(checklist, requestURI);
    }

}
