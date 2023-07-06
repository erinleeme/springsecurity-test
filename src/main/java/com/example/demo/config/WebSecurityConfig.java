package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*스프링 시큐리티 설정을 위한 빈 생성을 위한 어노테이션*/
@Configuration
/*모든 URL 요청은 스프링 시큐리티를 거치게 하는 어노테이션
-> 내부적으로 SpringSecurityFilterChain이 동작하여 모든 URL에 적용됨*/
@EnableWebSecurity
public class WebSecurityConfig {

    /*스프링 시큐리티 체인 빈 생성*/
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /*필터 설정*/
        //httpSecurity.


        return null;
    }
}
