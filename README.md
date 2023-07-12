# springsecurity-test
스프링시큐리티 테스트

# Spring Security 적용

## 1. build.gradle 설정

스프링 시큐리티 의존성 추가 
<br>
스프링 시큐리티 6.1.1 버전 사용
[Spring Security 6.1.1 GA, Current](https://spring.io/projects/spring-security#learn)

```java
implementation 'org.springframework.security:spring-security-test'
implementation "org.springframework.boot:spring-boot-starter-security"
```

## 2. 스프링 시큐리티 필터 적용
위에서 말한 스프링 시큐리티 필터를 목적에 맞게 설정을 해줘야한다.
- 로그인, 마이페이지, 관리자 페이지와 같이 회원 권한을 확인해야하는 요청인 경우만 인증 적용
- 그 외에 메인페이지 접속 등과 같이 권한 확인이 필요없는 요청인 경우에는 인증 적용 안되게 설정

## 3. 로그인 구현 방식
### Session - sesseion branch 확인
### JWT - [개념정리중](https://github.com/f-lab-edu/Daily/issues/44)https://github.com/f-lab-edu/Daily/issues/44
