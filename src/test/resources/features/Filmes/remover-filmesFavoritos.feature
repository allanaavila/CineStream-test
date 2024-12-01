# language: pt
Funcionalidade: Remover filmes favoritos
  Como um usuário autenticado
  Quero remover filmes da minha lista de favoritos
  Para manter minha lista atualizada com meus gostos atuais

  Cenário: Remover filme dos favoritos com sucesso
    Dado estou autenticado no sistema com credenciais
    E um filme com ID 603 está nos meus favoritos
    Quando realizo uma requisicao para remover o filme 603 dos favoritos
    Então o sistema retorna status 202

  Cenário: Remover filme não presente na lista de favoritos
    Dado estou autenticado no sistema com credenciais
    Quando realizo uma requisicao para remover o filme 604 dos favoritos
    Então o sistema retorna status 404
