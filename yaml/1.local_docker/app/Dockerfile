# 백업용 파일. root경로의 yml파일을 사용한다.
FROM openjdk:8-jre

COPY target/deploy-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]