# Forum

## Descrição

API Rest simulando o forum da [alura](https://www.alura.com.br/), usando Kotlin com Spring boot

- Link para a formação: https://www.alura.com.br/formacao-kotlin-spring-boot
- Repositório do curso: https://github.com/alura-cursos/2217-kotlin-spring/tree/master

## Como usar

- Link para a aplicação (Heroku): https://curso-kotlin-rest-alura.herokuapp.com

### Fazer login

- Fazer uma requisição `POST` para `/login`, passando no corpo da requisição:

```json
{
    "username": "ana@email.com",
    "password": "123456"
}
```

- Pegar o token no header `Authorization`
  - O token tem a expiração em 1 minuto.

### Demais ações

| Método HTTP |       URL       |                    Ação                    |
|-------------|-----------------|--------------------------------------------|
|     GET     | `/topicos`      | Lista todos os tópicos de forma paginada   |
|     GET     | `/topicos/{id}` | Mostra os detalhes de determinado tópico   |
|     POST    | `/topicos`      | Cadastra um novo tópico                    |
|     PUT     | `/topicos`      | Atualiza os dados de um determinado tópico |
|     DELETE  | `/topicos/{id}` | Deleta um determinado tópico               |
|     GET     | `/relatorio`    | Faz uma listagem de tópicos por categoria  |

### Corpo das Requisições

- POST `/topicos`

```json
{
    "titulo": "string",
    "mensagem": "string",
    "idCurso": "number",
    "idAutor": "number"
}
```

- PUT  `/topicos`

```json
{
    "id": "number",
    "titulo": "string",
    "mensagem": "string"
}
```

## Componentes

- spring-validation
- spring-web
- spring-data-jpa
- spring-cache
- spring-security
- spring-boot-devtools
- h2
- flyway-core
- jjwt