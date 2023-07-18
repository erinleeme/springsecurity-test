# springsecurity-test
스프링 시큐리티 테스트 레퍼지토리입니다. <br>
스프링 시큐리티로 회원가입 및 로그인을 구현하기 위함이 목적입니다.
## 기본 환경
- SpringBoot 3.1.1
- MyBatis
- H2DB
- Gradle
- JDK 17
## Spring Security 적용
- 회원가입
- 로그인 : Session, JWT
## build.gradle 설정
[스프링 시큐리티 6.1.1 버전 사용](https://spring.io/projects/spring-security#learn)
[H2 Database](https://github.com/erinleeme/springsecurity-test/wiki/H2-Database)

```java
/*DB*/
implementation "org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2"
implementation group: 'com.h2database', name: 'h2', version: '2.1.214'

/*Spring Security 적용*/
implementation 'org.springframework.security:spring-security-test'
implementation "org.springframework.boot:spring-boot-starter-security"

/*Valid*/
implementation group: 'org.springframework.boot', name: 'spring-boot-starter-validation', version: '3.1.0'
```
## 회원가입 API
[회원가입 구현](https://github.com/erinleeme/springsecurity-test/wiki/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-API)

## 로그인 API
Session : https://github.com/erinleeme/springsecurity-test/tree/session <br>
JWT : https://github.com/erinleeme/springsecurity-test/tree/security-token
