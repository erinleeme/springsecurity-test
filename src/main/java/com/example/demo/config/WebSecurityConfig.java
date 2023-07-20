package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
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
    /*기본 필터 설정*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf((csrf)->csrf.ignoringRequestMatchers("/h2-console/**") /*h2 db를 사용하기 위해 csrf가 적용되지 않게 해당 url을 disable 시켜야한다.*/
                        .disable())
                .httpBasic(AbstractHttpConfigurer::disable); /*httpBasic의 낮은 수준의 보안 문제로 비활성화*/

        httpSecurity.authorizeHttpRequests((request) -> request
                        /*admin 페이지와 로그인 페이지 접속 시 로그인 화면이 뜨도록 설정 */
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/member").hasRole("USER")
                .anyRequest().permitAll())
                .formLogin((form) -> form
                .loginProcessingUrl("/login") /*로그인 Form Action URL*/
                .successHandler(new AuthenticationSuccessHandler() {
                    /*로그인 성공했을 때*/
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
                        log.info("authentication : " + authentication.getName());
                        response.sendRedirect("/");
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    /*로그인 실패했을 때*/
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
                        log.info("exception : " + exception.getMessage());
                        response.sendRedirect("/login");
                    }
                })
                .permitAll());;
                
        /*Session 설정*/
        httpSecurity.sessionManagement((session) -> session
                /*SpringSecurity Session 정책 SessionCreationPolicy
                * ALWAYS : 항상 세션 생성
                * IF_REQUIRED : 필요할 때(Default)
                * NEVER : 새로 생성하지 않고 기존에 있는 Session 사용
                * STATELESS : 새로 생성하지도 않고 기존에 있는 Session도 사용하지 않음 -> Session을 사용하지 않는 경우 선택
                * */
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) /*세션 생성 정책*/
                .invalidSessionUrl("/login")); /*세션 만료 시 리다이렉트 될 URL*/

        return httpSecurity.build();
    }
}
