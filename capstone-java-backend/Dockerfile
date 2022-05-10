# 1. from the "build inside" approach
FROM eclipse-temurin:17-jdk-alpine as builder
RUN apk update
RUN apk add maven
WORKDIR /home/app
COPY . .
RUN mvn package -DskipTests -f pom.xml

# 2. Restart with a fresh JRE Alpine image.
FROM eclipse-temurin:17-jre-alpine

# 3. Continue with application deployment.
EXPOSE 8080/tcp
WORKDIR /home/app
COPY --from=builder /home/app/target/chatapp-1.0.jar app.jar
CMD ["java", "-jar", "./app.jar"]
