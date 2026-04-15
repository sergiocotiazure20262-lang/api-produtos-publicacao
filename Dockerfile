# =========================
# Etapa 1 - Build da aplicação
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Diretório de trabalho
WORKDIR /app

# Copiar arquivos do projeto
COPY pom.xml .
COPY src ./src

# Gerar o JAR (skip testes para acelerar build)
RUN mvn clean package -DskipTests

# =========================
# Etapa 2 - Imagem final
# =========================
FROM eclipse-temurin:21-jdk-jammy

# Diretório da aplicação
WORKDIR /app

# Copiar o JAR gerado da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expor a porta padrão do Spring Boot
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]