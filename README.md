# Sistema de delivery de comida com Java e Spring Boot
<img  align="center" alt="Thallyta-Js" height="300" width="400" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" />

### Projeto em Andamento!

<b>Este projeto foi criado com Java 17 e Spring Framework 2.7.3</b>

# Sobre o projeto

Este projeto consiste em uma aplicação Backend com Spring Boot para criar um sistema de delivery de comida completo com todos os recursos que o Spring disponibiliza.
O intuito deste projeto é servir de repositório base para consultas futuras de implementação de recursos do framework.

# Tecnologias utilizadas


### Backend

- Java
- Spring Boot
- Spring Data JPA
- MySql
- jUnit 5
- Mockito
- Swagger
- OpenApi 3
- SpringFox

### Bibliotecas e plugins utilizados

- Lombok
- Squiggly (JsonFilter)
- RestAssured

# Iniciando o projeto

Para iniciar a aplicação execute a seguinte ação na pasta algafood

```shell script
mvn spring-boot:run
```

Instale as dependências do maven com o comando:

```shell script
mvn clean install
```

Consultar documentação da Api:

```shell script
http://localhost:8080/swagger-ui/index.html#/
http://localhost:8080/v3/api-docs
```

## Branches de referência - Desenvolvimento

- master -> Projeto completo
- feat/crud-basic -> Implementação de um CRUD da forma mais básica.
- feat/crud-service -> Implementação do CRUD com a camada de serviço.
- feat/crud-repository-jpa -> Implementação de recursos do jpa e padrão repository.
- feat/migrations-flyway -> Implementação do flyway para criar as migrações.
- feat/custom-exception-handle -> Implementação de tratamendo e modelagem de erros da API.
- feat/include-patterns -> Inclusão de DTO's e Mappers nos CRUD's para aplicação de boas práticas.
- feat/advanced-api -> Modelagem avançada de API's usando sub-recursos para relacionamento, granularidade de recursos e conceitos abstratos e não-CRUD.
- feat/search-reports-projections -> Implementação de pesquisas, relatorios e projeções no sistema.
- feat/upload-download-files -> Implementação de download e upload de arquivos com AWS S3 e armazenamento local.
- feat/send-transactional-email -> Implementação de envio de emails transacionais com AWS SES.
- feat/api-client-js-java -> Implementação de cache e ptimizações gerais para consumo do cliente.
- feat/implements-api-docs -> Documentação da Api com Swagger OpenApi 3

## Branches de referência - Testes
- feat/unitary-tests -> Implementação de testes unitários com jUnit 5 e Mockito
- feat/api-tests -> Implementação de testes de integração e api com banco de dados de teste

# Autor
<b>Thallyta Macedo Carvalho de Castro</b>

Linkedin: https://www.linkedin.com/in/thallyta-castro/

Medium: https://medium.com/@thallyta-castro-cv

email: contato@thallytacastro.com.br
