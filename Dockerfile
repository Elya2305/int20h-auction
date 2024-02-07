FROM eclipse-temurin:17.0.10_7-jdk-focal as builder

RUN mkdir /source
WORKDIR /source
COPY . /source

RUN ./gradlew --no-daemon bootJar && cp build/libs/user-profile.jar ./service.jar
RUN rm -rf /root/.gradle

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "service.jar"]
