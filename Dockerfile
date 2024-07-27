# Etapa de construção
FROM ubuntu:latest AS build

# Atualize e instale dependências
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y


# Copie os arquivos do projeto para o contêiner
COPY . .

# Execute o comando Maven para construir o JAR
RUN apt-get install maven -y
RUN mvn clean install

# Etapa final
FROM openjdk:17-jdk-slim

# Exponha a porta 8080
EXPOSE 8080

# Copie o JAR da etapa de construção para a imagem final
COPY --from=build /target/deploy_render-1.0.0.jar app.jar

# Defina o ponto de entrada para o contêiner
ENTRYPOINT ["java", "-jar", "app.jar"]
