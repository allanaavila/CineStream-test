# language: pt
Funcionalidade: Busca de séries
  Como um usuár400io do sistema
  Quero buscar séries pelo título
  Para verificar se elas estão cadastradas no sistema

  Cenário: Buscar série com título válido
    Dado série com título "The Walking Dead" está cadastrada no sistema
    Quando realizo uma requisicao para buscar o título da serie "The Walking Dead"
    Entao a série deve ser encontrada com status 200
    E o título retornado da serie deve ser "The Walking Dead"