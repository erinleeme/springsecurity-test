# springsecurity-test
스프링시큐리티 테스트

# Spring Security 적용

## 1. build.gradle 설정

스프링 시큐리티 의존성 추가
스프링 시큐리티 3.1.0 버전 사용


```java
/*Spring Security 적용*/
implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.1.RELEASE'
implementation 'org.springframework.security:spring-security-test'
implementation group: 'org.springframework.boot', name: 'spring-boot-starter-security', version: '3.1.0'
```

## 2. 스프링 시큐리티 필터 적용

위에서 말한 스프링 시큐리티 필터를 목적에 맞게 설정을 해줘야한다.
- 로그인, 마이페이지, 관리자 페이지와 같이 회원 권한을 확인해야하는 요청인 경우만 인증 적용
- 그 외에 메인페이지 접속 등과 같이 권한 확인이 필요없는 요청인 경우에는 인증 적용 안되게 설정
- 아이디로 먼저 해당 Member가 있는지 확인 <br>
  **여기서 스프링 시큐리티에서 제공하는 UserDetailsService를 꼭 사용해야하나?**
  
  <br>
- 비밀번호를 받아 비밀번호는 스프링 시큐리티 암호화 알고리즘으로 Hash값으로 변환
- 변환된 비밀번호와 받은 아이디를 DB로 보내어 일치하는지 확인
