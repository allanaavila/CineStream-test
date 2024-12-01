# language: pt
Funcionalidade: Adicionar séries favoritas
  Como um usuário autenticado
  Quero adicionar séries à minha lista de favoritas
  Para organizar melhor os conteúdos que gosto

  Cenário: Adicionar série já nos favoritos
    Dado autenticado no sistema com credenciais geradas aleatoriamente
    E uma série com ID 101 já está nos meus favoritos
    Quando realizo uma requisicao para adicionar a série 101 aos favoritos
    Então sistema retorna status 208

  Cenário: Adicionar série favorita com sucesso
    Dado autenticado no sistema com credenciais geradas aleatoriamente
    Quando realizo uma requisicao para adicionar a série 101 aos favoritos
    Então sistema retorna status 202
