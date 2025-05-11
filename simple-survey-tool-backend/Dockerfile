FROM eclipse-temurin:24-jdk-alpine as build
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:24-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:mariadb://mariadb:3306/survey_db
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=password

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]