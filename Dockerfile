# Utilise une image OpenJDK 21 comme base
FROM openjdk:21-jdk-slim

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copie le fichier JAR de l'application dans le conteneur
COPY target/*.jar app.jar

# Expose le port sur lequel l'application s'exécute
EXPOSE 8080

# Commande pour exécuter l'application
CMD ["java", "-jar", "app.jar"]