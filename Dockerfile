FROM maven:3.6.3-jdk-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

FROM tomcat:9
COPY --from=build /app/target/cinema.war /usr/local/tomcat/webapps
