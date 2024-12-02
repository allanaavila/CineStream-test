# CineStream - Testes Automatizados com Cucumber

## Descrição

CineStream é uma aplicação web desenvolvida com Java Spring Boot que permite aos usuários gerenciar seus filmes e séries favoritos. Este repositório contém os testes automatizados da aplicação, utilizando Cucumber para a validação de cenários de comportamento (BDD - Behavior Driven Development) e RestAssured para testes de API.


**Repositório de Projeto Principal:** [CineStream](https://github.com/LucasAlec/CineStreamTA?tab=readme-ov-file)

## A aplicação permite que os usuários realizem ações como:

Os testes implementados verificam os seguintes cenários principais da aplicação **CineStream**:

- **Cadastro de Usuário**
- **Autenticação de Usuário**
- **Gerenciamento de Filmes e Séries Favoritos**
- **Busca de Filmes e Séries**
- **Exibição de Detalhes de Filmes e Séries**

## Testes de API com RestAssured
Os testes de API são feitos utilizando RestAssured para garantir que os endpoints da aplicação estejam funcionando corretamente.

**Endpoints Testados:**
- **POST /login: Realiza o login e retorna um token JWT.**
- **GET /user/favorites: Retorna a lista de filmes e séries favoritos do usuário autenticado.**
- **POST /api/filmes/favorito: Adiciona um filme aos favoritos.**
- **POST /api/series/favorita: Adiciona uma série aos favoritos.**

## Tecnologias Utilizadas
- **Cucumber: Para a definição de cenários de comportamento (BDD).**
- **RestAssured: Para testes de API**
- **JUnit: Framework para testes automatizados.**
- **Mockito: Para mocking de dependências.**
- **Spring Boot: Framework principal da aplicação.**
- **PostgreSQL: Banco de dados em produção.**
- **JWT: Autenticação via tokens JSON Web Token.**


## Como Executar os Testes

### Requisitos

- **Java 17 ou superior**
- **Maven**
- **Docker (se necessário para rodar o banco de dados ou outras dependências)**

### Passos

1. **Clone o Repositório:**

   ```bash
   git clone https://github.com/SeuUsuario/CineStream-test.git
   cd CineStream-test

2. **Instale as dependências do projeto:**

   ```bash
    mvn install

3. **Execute os testes:**

   ```bash
   mvn test
````
````

## Alunos

| <img src="https://avatars.githubusercontent.com/u/61765668?v=4" width="100"> | <img src="https://avatars.githubusercontent.com/u/89415462?v=4" width="100"> | <img src="https://avatars.githubusercontent.com/u/114600184?v=4" width="100"> |
|------------------------------------------------------------------------------|------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| [**Allana Ávila**](https://github.com/allanaavila)                           | [**Lucas Alec**](https://github.com/LucasAlec)                              | [**Marina Guimarães**](https://github.com/marinagv95)                           |
