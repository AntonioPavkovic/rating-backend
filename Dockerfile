FROM openjdk:17-alpine
COPY target/rating-backend.jar rating-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","rating-backend.jar"]