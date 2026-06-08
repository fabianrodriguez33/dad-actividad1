FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia el JAR generado por Maven
COPY target/*.jar app.jar

# Copia el config-repo dentro del contenedor
COPY ../config-repo /app/config-repo

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.cloud.config.server.native.search-locations=file:/app/config-repo"]
