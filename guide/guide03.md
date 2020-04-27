# 3. SpringBoot 어플리케이션 생성

※ 설정 및 소스는 자유롭게...Mysql과 연동만 시킬꺼니까..

※ 환경변수 (DB관련만 5개를 설정했으며, 개발서버/운영서버까지 생각했을때 더 많은 환경변수가 필요할 것으로 판단됨)

- MYSQL_HOST
- MYSQL_PORT
- MYSQL_DATABASE
- MYSQL_USER
- MYSQL_PASSWORD

## 3.1 프로젝트 생성

https://start.spring.io/

설정옵션

- Project: Maven Project
- Language: Java
- Spring Boot 버전: 릴리즈 아무거나..
- Project Metadata: 역시 아무거나..
- Dependencies
  - Spring Web
  - Spring Data JPA
  - MySQL Driver
  - Thymeleaf
  - Spring Boot DevTools
  - Lombok

## 3.2 소스

프로젝트/src/main/resources/application.yml

```yml
spring:
  datasource:
    #    url: jdbc:mysql://localhost:3306/study?useUnicode=yes&characterEncoding=UTF-8
    url: jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}?useUnicode=yes&characterEncoding=UTF-8
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${MYSQL_USER}
    password: ${MYSQL_PASSWORD}

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        format_sql: true
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect

  devtools:
    livereload:
      enabled: true

logging:
  level:
    org.springframework: debug
    org.hibernate.SQL: debug
  #org.hibernate.type: trace
```

java, html소스 생략

## 3.3 SpringBoot jar파일 생성

```
# 메이븐으로 jar패키지 생성(IDE or 커맨드입력)
mvn package

# target폴더에 jar파일 생성되었는지 확인.
```

## 3.4 Docker image생성

### 3.4.1 프로젝트경로에 Dockerfile생성

```DockerFile
FROM openjdk:8-jre

COPY target/deploy-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 3.4.2 Docker image생성

```
docker image build -t [계정명]/[프로젝트명]:[태그] .
```

### 3.4.3 DockerHub에 생성된 image push.

```
docker image push [계정명]/[프로젝트명]:[태그]
```

### 3.4.4 docker-compose.yml파일 생성.(로컬 테스트용)

```yml
version: "3"
services:
  spring-app:
    image: [계정명]/[프로젝트명]:[태그]
    ports:
      - 8080:8080
    environment:
      MYSQL_HOST: host.docker.internal # [MacOS]컨테이너에서 호스트에 접근시 사용.
      MYSQL_PORT: 3306
      MYSQL_DATABASE: study
      MYSQL_USER: seolys
      MYSQL_PASSWORD: seolys!
```

### 3.4.3 실행(테스트)

```
# 실행 (서버가 잘뜨는지 보기위해 -d옵션을 주지않는다)
docker-compose up

# 중지
docker-compose down
```

### ※ 더 공부해야할 사항

- SpringBoot profile도 관리하도록 하여, 로컬/개발/운영시 서로 다른 yml파일을 읽어들일 수 있도록 처리 필요.
- 컨테이너 이미지를 mvn(or gradle) package할때, DB와 관련된 jUnit Test는 어떻게 할것인가?