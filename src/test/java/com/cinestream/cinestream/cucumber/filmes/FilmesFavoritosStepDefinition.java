package com.cinestream.cinestream.cucumber.filmes;

import com.cinestream.cinestream.cucumber.helper.AuthHelper;
import com.cinestream.cinestream.cucumber.restassured.RestAssuredUtil;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

public class FilmesFavoritosStepDefinition {

    private Response response;

    @Dado("estou autenticado no sistema com credenciais geradas aleatoriamente")
    public void estouAutenticadoComCredenciaisAleatorias() {
        AuthHelper.registrarEAutenticarUsuario();
    }

    @E("um filme com ID {long} já está nos meus favoritos")
    public void filmeJaNosFavoritos(Long filmeId) {
        // Adiciona o filme aos favoritos para garantir o estado inicial
        Response adicionarResponse = RestAssuredUtil.produces()
                .body(filmeId.toString())
                .post("/usuario/favorito/filme/adicionar");

        adicionarResponse.then().statusCode(HttpStatus.SC_ACCEPTED);
    }



    @Quando("realizo uma requisicao para adicionar o filme {long} aos favoritos")
    public void adicionarFilmeAosFavoritos(Long filmeId) {
        response = RestAssuredUtil.produces()
                .body(filmeId.toString())
                .post("/usuario/favorito/filme/adicionar");
    }

    @Entao("o sistema deve retornar status {int}")
    public void validarStatusRetornado(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Entao("a mensagem retornada deve ser {string}")
    public void validarMensagemDeResposta(String mensagemEsperada) {
        String mensagem = response.getBody().asString();
        Assertions.assertEquals(mensagemEsperada, mensagem, "A mensagem retornada está incorreta.");
    }
}
