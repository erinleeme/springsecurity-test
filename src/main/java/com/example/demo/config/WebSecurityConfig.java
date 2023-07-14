package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*스프링 시큐리티 설정을 위한 빈 생성을 위한 어노테이션*/
@Configuration
/*모든 URL 요청은 스프링 시큐리티를 거치게 하는 어노테이션
-> 내부적으로 SpringSecurityFilterChain이 동작하여 모든 URL에 적용됨*/
@EnableWebSecurity
public class WebSecurityConfig {

    /*암호화에 사용될 Bcrypt 빈 생성*/
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*스프링 시큐리티 체인 빈 생성 - 요청 URL 설정*/
    /*인증 단계 필터 설정*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/").permitAll()
                        /*admin 페이지와 로그인 페이지 접속 시 로그인 화면이 뜨도록 설정 */
                        .requestMatchers("/admin/**").authenticated()
                        .anyRequest().permitAll());
        return httpSecurity.build();
    }
}
