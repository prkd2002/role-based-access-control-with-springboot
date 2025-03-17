# REST API mit Spring Boot, JWT und rollenbasierter Zugriffskontrolle

## Projektübersicht
Dieses Projekt implementiert eine REST API mit Spring Boot, die JSON Web Tokens (JWT) zur Authentifizierung und rollenbasierte Zugriffskontrolle verwendet.

## Voraussetzungen
- Java 11 oder höher
- Maven 3.6.3 oder höher
- Spring Boot 2.5.4 oder höher

## Installation
1. Klone das Repository:
   ```bash
   git clone https://github.com/dein-repo/rest-api-spring-boot-jwt.git](https://github.com/prkd2002/role-based-access-control-with-springboot.git 
### Navigiere in das Projektverzeichnis:
cd role-based-access-control-with-springboot
### Baue das Projekt:
mvn clean install
### Konfiguration
Konfiguriere die application.properties Datei:

spring.datasource.url=jdbc:mysql://localhost:5412/demo
spring.datasource.username=dein-benutzername
spring.datasource.password=dein-passwort
jwt.secret=dein-geheimes-schlüssel
## Endpunkte
### Authentifizierung
POST /api/auth/login: Authentifiziert den Benutzer und gibt ein JWT zurück.
Anfrage:
{
  "username": "benutzername",
  "password": "passwort"
}
Antwort:
{
  "token": "jwt-token"
}

### Rollenbasierte Zugriffskontrolle
ROLE_USER: Zugriff auf allgemeine Endpunkte.
ROLE_ADMIN: Zugriff auf administrative Endpunkte.
### Sicherheit
Die Sicherheit wird durch Spring Security und JWT gewährleistet. Jeder Endpunkt ist durch rollenbasierte Zugriffskontrolle geschützt.
