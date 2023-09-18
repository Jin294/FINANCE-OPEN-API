package com.ssafy.iNine.OAuth.common.config;

import com.ssafy.iNine.OAuth.common.authentication.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomAuthenticationProvider customAuthenticationProvider;
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
    // 커스텀 인증 : 어떤 사용자인지 확인하는 메소드 커스텀
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/oauth/login")
                .permitAll()
                .and()
                .httpBasic();
//        http
//                //csrf 허용 안함
//                .csrf().disable()
//                .headers().disable()
//                .cors().and()
//                //로그인과 회원가입에는 모두 허용 그 외에 모든 요청에 대해서 토큰 검사한다.
//                .authorizeRequests()
//                .antMatchers("/oauth/authorize").authenticated()
//                .antMatchers("/oauth/token").permitAll()
//                .antMatchers("/oauth/token/**").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
////                .loginPage("/user/login")
////                .defaultSuccessUrl("/user/auth")
//                .permitAll()
////                .formLogin().permitAll()
//                .and()
//                .httpBasic();
    }
}