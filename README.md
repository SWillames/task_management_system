# Task Management System

## Descrição
O **Task Management System** é uma aplicação desenvolvida em Java utilizando Spring Boot. A aplicação permite aos usuários gerenciar tarefas, criando listas e itens de tarefas. Ela fornece uma API RESTful para interagir com essas entidades, facilitando a integração com outros sistemas.

## Funcionalidades
- **CRUD Completo**: Permite criar, visualizar, atualizar e excluir listas e itens de tarefas.
- **Camadas de Serviço e Repositório**: A lógica de negócio é abstraída em serviços, e a persistência de dados é gerenciada por repositórios.
- **Conversão de Entidades para DTOs**: Usa `ModelMapper` para converter entidades em DTOs e vice-versa.
- **Testes Automatizados**: Inclui testes unitários e de integração utilizando `Mockito` e `Fixture Factory`.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Lombok**
- **ModelMapper**
- **Fixture Factory**
- **Mockito**

## Estrutura do Projeto
- **ItemEntity**: Entidade que representa um item de tarefa.
- **ListEntity**: Entidade que representa uma lista de tarefas.
- **ItemDTO**: DTO usado para transferência de dados de itens.
- **ListDTO**: DTO usado para transferência de dados de listas.
- **ItemRepository**: Interface para operações de persistência de itens.
- **ListRepository**: Interface para operações de persistência de listas.
- **ItemServiceImpl**: Implementação do serviço de gerenciamento de itens.
- **ListServiceImpl**: Implementação do serviço de gerenciamento de listas.
- **Controllers**: Exposição das APIs REST para gerenciamento de listas e itens.
- **Tests**: Testes dos serviços e controladores.

## Requisitos
- **Java 17** ou superior
- **Maven** ou **Gradle**
- **Banco de Dados MySQL**

## Configuração e Execução

1. **Clone o Repositório:**
   ```bash
   git clone https://github.com/SWillames/task-management-system.git
   cd task-management-system
   ```

2. **Configure o Banco de Dados:**
   O arquivo application.yml, já está configurado para o usar o container que será baixado do docker-compose, então é só rodar o comando abaixo.
   ```bash
     docker-compose up -d
   ```
   Esse é a configuração para a aplicação se conectar com a imagem que será baixada
   ```yml
    datasource:
      url: jdbc:postgresql://localhost:5433/task_management
      username: admin
      password: admin
   ```

3. **Construa o Projeto:**
   ```bash
   mvn clean install
   ```

4. **Execute a Aplicação:**
   ```bash
   mvn spring-boot:run
   ```

## Endpoints da API

### Listas
- **GET /api/lists**: Retorna todas as listas de tarefas.
- **POST /api/lists**: Cria uma nova lista de tarefas.
- **GET /api/lists/{id}**: Retorna uma lista de tarefas pelo ID.
- **PUT /api/lists/{id}**: Atualiza uma lista de tarefas existente.
- **DELETE /api/lists/{id}**: Exclui uma lista de tarefas.

### Itens
- **GET /api/items**: Retorna todos os itens de tarefas.
- **POST /api/items**: Cria um novo item de tarefa.
- **GET /api/items/{id}**: Retorna um item de tarefa pelo ID.
- **PUT /api/items/{id}**: Atualiza um item de tarefa existente.
- **DELETE /api/items/{id}**: Exclui um item de tarefa.

## Executando Testes

Para rodar os testes unitários e de integração:

```bash
mvn test
```

## Contribuindo
Contribuições são bem-vindas! Sinta-se à vontade para abrir um issue ou enviar um pull request.

## Licença
Este projeto é licenciado sob a Licença MIT.
