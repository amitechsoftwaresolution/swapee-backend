FROM eclipse-temurin:11-jdk-alpine
WORKDIR /app
COPY target/swapee-backend-aws.jar swapee-backend-aws.jar
EXPOSE 8080
CMD ["java", "-jar", "swapee-backend-aws.jar"]