package com.ssafy.iNine.Document.common.config;

import com.ssafy.iNine.Document.common.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //csrf 허용 안함
                .csrf().disable()
                .headers().disable()
                .cors().and()
                //세션 사용 안함
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //로그인과 회원가입에는 모두 허용 그 외에 모든 요청에 대해서 토큰 검사한다.
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/docs/service").permitAll()
                .antMatchers(HttpMethod.POST,"/docs/service/login").permitAll()
                .antMatchers("/docs/api").permitAll()
                .antMatchers("/docs/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //form login 안함
                .formLogin().disable()
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
