# language: pt
Funcionalidade: Busca de filmes
  Como um usuário do sistema
  Quero buscar filmes pelo título
  Para verificar se eles estão cadastrados no sistema

  Cenário: Buscar filme com título válido
    Dado filme com título "Matrix" está cadastrado no sistema
    Quando realizo uma requisicao para buscar o título do filme "Matrix"
    Entao o filme deve ser encontrado com status 200
    E o título retornado do filme deve ser "Matrix"