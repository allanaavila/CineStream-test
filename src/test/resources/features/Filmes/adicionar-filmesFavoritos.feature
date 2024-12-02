# language: pt
Funcionalidade: Adicionar filmes favoritos
  Como um usuário autenticado
  Quero adicionar filmes à minha lista de favoritos
  Para organizar melhor os conteúdos que gosto

  Cenário: Adicionar filme já nos favoritos
    Dado estou autenticado no sistema com credenciais geradas aleatoriamente
    E um filme com ID 603 já está nos meus favoritos
    Quando realizo uma requisicao para adicionar o filme 603 aos favoritos
    Então o sistema deve retornar status 208

  Cenário: Adicionar filme favorito com sucesso
    Dado estou autenticado no sistema com credenciais geradas aleatoriamente
    Quando realizo uma requisicao para adicionar o filme 603 aos favoritos
    Então o sistema deve retornar status 202
