# language: pt
Funcionalidade: Login de usuários
  Como um usuário registrado no sistema
  Quero fazer login no sistema
  Para acessar funcionalidades protegidas

  Cenário: Login com credenciais válidas
    Dado um usuário gerado com email e senha aleatórios
    E o usuário foi cadastrado no sistema
    Quando realizo login com as credenciais do usuário
    Então o login deve ser realizado com sucesso
    E o token de autenticação deve ser gerado

  Cenário: Login com credenciais inválidas
    Quando realizo login com credenciais inválidas
    Então a requisição deve retornar status 401
    E a mensagem deve ser "Email ou senha incorretos"