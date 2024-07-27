# Etapa de construção
FROM ubuntu:latest AS build

# Atualize e instale dependências
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Defina o diretório de trabalho
WORKDIR /app

# Copie os arquivos do projeto para o contêiner
COPY . .

# Execute o comando Maven para construir o JAR (Ignorando testes)
RUN mvn clean install -DskipTests

# Verifique o conteúdo do diretório target para confirmar o JAR
RUN ls -la /app/target/

# Etapa final
FROM openjdk:17-jdk-slim

# Exponha a porta 8080
EXPOSE 8080

# Copie o JAR da etapa de construção para a imagem final
COPY --from=build /app/target/sistema1-0.0.1-SNAPSHOT.jar app.jar

# Defina o ponto de entrada para o contêiner
ENTRYPOINT ["java", "-jar", "app.jar"]
