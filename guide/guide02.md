# 2. MySQL 이미지&컨테이너 생성

## 2.1 특정경로에 아래 파일들을 생성한다.

특정경로/Dockerfile

```Dockerfile
FROM mysql:5.7

COPY etc/mysql/mysql.conf.d/mysqld.cnf /etc/mysql/conf.d/
COPY etc/mysql/conf.d/mysql.cnf /etc/mysql/conf.d/
# DDL/DML파일이 있을경우 같이 COPY하면 될것같다..

CMD ["mysqld"]
```

특정경로/docker-compose.yml

```yml
version: "3"
services:
  mysql:
    image: [도커ID]/mysql:[버전] # ex) seolnavy/mysql:0.0.1
    ports:
      - 3306:3306
    environment:
      MYSQL_ROOT_PASSWORD: root!
      MYSQL_DATABASE: study
      MYSQL_USER: user
      MYSQL_PASSWORD: password!
```

특정경로/etc/mysql/mysql.conf.d/mysqld.cnf

```
[mysqld]
character-set-server = utf8mb4
collation-server = utf8mb4_general_ci
pid-file	= /var/run/mysqld/mysqld.pid
socket		= /var/run/mysqld/mysqld.sock
datadir		= /var/lib/mysqlassorted security risks
symbolic-links=0
```

특정경로/etc/mysql/conf.d/mysql.cnf

```
[mysql]
default-character-set=utf8mb4

[client]
default-character-set=utf8mb4

```

※ DDL/DML 파일 미리 생성해둔 후 옮겨놓기.

## 2.2 도커 이미지 생성 및 실행

```
# Dockerfile 생성경로 이동
cd [경로]

# Dockerfile을 바탕으로 이미지 생성
docker image build -t [도커ID]/mysql:[태그] .

# 이미지를 DockerHub에 push
docker image push [도커ID]/mysql:[태그]

# 생성된 MySQL 이미지로 컨테이너 실행.(컴포즈)
docker-compose up


※ 백그라운드로 컨테이너 실행(컴포즈)
docker-compose up -d

※ 컨테이너 종료(컴포즈)
docker-compose down

※ 컴포즈 컨테이너 접속
docker-compose run [서비스명] bash
docker-compose run mysql bash
```

### ※ 컨테이너 접속하여 옮겨놓은 DDL/DML 파일을 실행하여 기초데이터 생성. (예제에선 생략)

### ※ 더 공부해야할 사항

- sercet 적용하여 host,user,password등 암호화 진행
