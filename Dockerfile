# Utilise une image OpenJDK 21 comme base
FROM openjdk:21-jdk-slim

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copie le fichier JAR de l'application dans le conteneur
COPY target/parkingmanager-0.1.1-SNAPSHOT.jar app.jar

# Expose le port sur lequel l'application s'exécute
EXPOSE 8080

# Commande pour exécuter l'application
CMD ["java", "-jar", "-Dspring.profiles.active=local", "app.jar"]