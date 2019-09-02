FROM adoptopenjdk/openjdk11:alpine-slim
ADD ["build/libs/ms-listener-1.0-SNAPSHOT.jar", "app.jar"]
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar -Dspring.profiles.active=docker /app.jar" ]
