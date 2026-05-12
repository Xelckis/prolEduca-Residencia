# Stage 1: Build com Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e os arquivos de código
COPY pom.xml .
COPY src ./src

# Faz o build do projeto e gera o jar (SNAPSHOT)
RUN mvn clean package -DskipTests

# Stage 2: Rodar a aplicação
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Instalar certificados CA
# RUN apk add --no-cache ca-certificates && update-ca-certificates

# Copiar o certificado .p12 para dentro do container
COPY certs/homologacao-98758-prol-edupass.p12 ./certs/homologacao-98758-prol-edupass.p12

# Copia o jar gerado no estágio de build
COPY --from=build /app/target/prol-educa-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-Djavax.net.ssl.trustStore=/usr/lib/jvm/temurin-17-jdk/lib/security/cacerts", "-Djavax.net.ssl.trustStorePassword=changeit", "-jar", "app.jar"]