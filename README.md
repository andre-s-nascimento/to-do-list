<h1 align="center">
  TODO List
</h1>

API para gerenciar tarefas (CRUD) que faz parte [desse desafio](https://github.com/simplify-liferay/desafio-junior-backend-simplify) para pessoas desenvolvedoras backend júnior, que se candidatam para a Simplify.

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/)
- [Mysql](https://dev.mysql.com/downloads/)

## Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/to-do-list-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [httpie](https://httpie.io):

- Criar Tarefa 
```
$ http POST :8080/todos nome="Tarefa #1" descricao="Descrição Tarefa #1" prioridade=1

[
  {
    "descricao": "Descrição Tarefa #1",
    "id": 1,
    "nome": "Tarefa #1",
    "prioridade": 1,
    "realizado": false
  }
]
```

- Listar Tarefas
```
$ http GET :8080/todos

[
  {
    "descricao": "Descrição Tarefa #1",
    "id": 1,
    "nome": "Tarefa #1",
    "prioridade": 1,
    "realizado": false
  }
]
```

- Atualizar Tarefa
```
$ http PUT :8080/todos/1 nome="Tarefa #1 Up" descricao="Descrição Tarefa #1 Up" prioridade=2

[
  {
    "descricao": "Descrição Tarefa #1 Up",
    "id": 1,
    "nome": "Tarefa #1 Up",
    "prioridade": 2,
    "realizado": false
  }
]
```

- Remover Tarefa
```
http DELETE :8080/todos/1

[ ]
```