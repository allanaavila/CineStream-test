package com.cinestream.cinestream.cucumber.filmes;

import com.cinestream.cinestream.cucumber.helper.AuthHelper;
import com.cinestream.cinestream.cucumber.restassured.RestAssuredUtil;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

public class RemoverFilmesFavoritosStepDefinition {

    private Response response;

    @Dado("estou autenticado no sistema com credenciais")
    public void estouAutenticadoComCredenciaisAleatorias() {
        AuthHelper.registrarEAutenticarUsuario();
    }

    @E("um filme com ID {long} está nos meus favoritos")
    public void filmeJaNosFavoritos(Long filmeId) {
        // Adiciona o filme aos favoritos para garantir o estado inicial
        Response adicionarResponse = RestAssuredUtil.produces()
                .body(filmeId.toString())
                .post("/usuario/favorito/filme/adicionar");

        adicionarResponse.then().statusCode(HttpStatus.SC_ACCEPTED);
    }


    @Quando("realizo uma requisicao para remover o filme {long} dos favoritos")
    public void removerFilmeDosFavoritos(Long filmeId) {
        response = RestAssuredUtil.produces()
                .body(filmeId.toString())
                .post("/usuario/favorito/filme/remover");
    }

    @Entao("o sistema retorna status {int}")
    public void validarStatusRetornado(int statusCode) {
        response.then().statusCode(statusCode);
    }

}
