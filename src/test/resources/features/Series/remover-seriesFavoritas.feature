# language: pt
Funcionalidade: Remover séries favoritas
  Como um usuário autenticado
  Quero remover séries da minha lista de favoritos
  Para manter minha lista atualizada com os conteúdos que gosto

  Cenário: Remover série dos favoritos com sucesso
    Dado autenticado no sistema com credenciais ge
    E a série com ID 701 está atualmente nos meus favoritos
    Quando removo a série com ID 701 dos favoritos
    Então sistema remove deve retornar status 202

  Cenário: Remover série não presente na lista de favoritos
    Dado autenticado no sistema com credenciais ge
    E a série com ID 702 não está nos meus favoritos
    Quando removo a série com ID 702 dos favoritos
    Então sistema remove deve retornar status 404
