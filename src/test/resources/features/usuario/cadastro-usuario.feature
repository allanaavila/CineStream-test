#language: pt
  Funcionalidade: Cadastro de usuários
    Quero cadastrar novos usuários
    Para que eles possam acessar o sistema

    Cenário: Cadastro de um novo usuário
      Dado usuario nao cadastrado
      Quando realizo o cadastro do usuario
      Entao usuario foi cadastrado com sucesso

    Cenário: Cadastro de um usuário já existente
      Dado usuario ja cadastrado
      Quando realizo o cadastro do usuario
      Entao resposta da requisicao de usuario tenha status igual a 400