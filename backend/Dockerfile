FROM gradle:8.4-jdk21-alpine AS build

WORKDIR /app

COPY . .

RUN gradle clean build -x test --no-daemon


FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY --from=build /app/build/libs/nts-board-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
