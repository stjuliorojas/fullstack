# JDK
FROM eclipse-temurin:21-jdk-alpine

# directorio de trabajo interno
WORKDIR /app

# copiar el archivo jar al contenedor
COPY target/app-aut-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
