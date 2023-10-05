FROM ubuntu:latest
LABEL authors="jo791"

ENTRYPOINT ["top", "-b"]

# openjdk11를 베이스로 이미지를 생성한다.
FROM openjdk:11
# JAR_FILE에 jar 빌드 파일이 있는 경로를 저장한다
ARG JAR_FILE=build/libs/*.jar
# jar 빌드 파일을 도커 컨테이너 안 app.jar 이름으로 복사
COPY ${JAR_FILE} app.jar
# 컨테이너가 실행 될 때 실행되는 명령어
# 컨테이너가 실행되면 jar 파일을 실행한다.
ENTRYPOINT ["java","-jar","/app.jar"]