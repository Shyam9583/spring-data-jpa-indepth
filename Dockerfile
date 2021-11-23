FROM maven:3.8.3-openjdk-11-slim AS build
RUN mkdir /project
WORKDIR /project
COPY pom.xml .
RUN mvn -e -B dependency:go-offline
COPY src ./src
RUN mvn clean -e -B package -DskipTests

FROM eclipse-temurin:11.0.13_8-jre-alpine
RUN apk add dumb-init
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /project/target/data-jpa-app.jar /app/java-application.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
EXPOSE 8080
ENTRYPOINT ["dumb-init", "java", "-jar", "java-application.jar"]