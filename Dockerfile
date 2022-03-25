FROM openjdk:17-alpine

WORKDIR /home/app

COPY pom.xml mvnw ./
COPY src src
COPY .mvn .mvn

RUN ./mvnw install -DskipTests

RUN cp target/rating-backend.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
