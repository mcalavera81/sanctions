FROM openjdk:8-jdk-slim as build
ENV APP_HOME=/app/
WORKDIR $APP_HOME
COPY src $APP_HOME/src
COPY data $APP_HOME/data
COPY gradle $APP_HOME/gradle
COPY gradlew build.gradle.kts settings.gradle.kts $APP_HOME
RUN ./gradlew clean build


FROM openjdk:8-jre-slim
ENV APP_HOME=/app/
WORKDIR $APP_HOME
COPY --from=build /app/build/libs/sanctions-1.0.jar $APP_HOME
COPY --from=build /app/data $APP_HOME/data
ENTRYPOINT ["java", "-jar", "sanctions-1.0.jar"]