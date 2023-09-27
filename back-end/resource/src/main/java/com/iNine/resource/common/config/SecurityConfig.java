package com.iNine.resource.common.config;

import com.iNine.resource.common.filter.ApiTokenFilter;
import com.iNine.resource.domain.serviceprovider.repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final ServiceProviderRepository serviceProviderRepository;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                //csrf 허용 안함
//                .csrf().disable()
//                .headers().disable()
//                .cors().and()
//                //세션 사용 안함
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/invest/**").permitAll()
//                .antMatchers("/api/mydata/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                //form login 안함
//                .formLogin().disable()
//                .addFilterBefore(new ApiTokenFilter(serviceProviderRepository), UsernamePasswordAuthenticationFilter.class);
//
//    }
}
