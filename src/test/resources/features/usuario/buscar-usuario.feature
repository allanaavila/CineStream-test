# language: pt
Funcionalidade: Busca de usuarios
  Quero buscar usuarios pelo email
  Para verificar se estão cadastrados

  Cenário: Buscar usuário com email válido
    Dado usuario ja cadastrado
    Quando realizo uma requisicao para buscar email do usuario cadastrado
    Entao o usuario deve ser encontrado com status 200

  Cenário: Buscar usuário com email inválido
    Dado nenhum usuario com esse email
    Quando realizo uma requisicao para buscar email invalido
    Entao resposta da requisicao de usuario tenha status igual 404
