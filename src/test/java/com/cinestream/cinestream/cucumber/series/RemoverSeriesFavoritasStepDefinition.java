package com.cinestream.cinestream.cucumber.series;

import com.cinestream.cinestream.cucumber.restassured.RestAssuredUtil;
import com.cinestream.cinestream.cucumber.util.AuthHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

public class RemoverSeriesFavoritasStepDefinition {

    private Response response;

    @Dado("autenticado no sistema com credenciais ge")
    public void estouAutenticadoNoSistema() {
        AuthHelper.registrarEAutenticarUsuario();
    }

    @E("a série com ID {long} está atualmente nos meus favoritos")
    public void serieNosFavoritos(Long serieId) {
        Response adicionarResponse = RestAssuredUtil.produces()
                .body(serieId.toString())
                .post("/usuario/favorito/serie/adicionar");

        adicionarResponse.then().statusCode(HttpStatus.SC_ACCEPTED);
    }

    @E("a série com ID {long} não está nos meus favoritos")
    public void serieNaoNosFavoritos(Long serieId) {
        Response removerResponse = RestAssuredUtil.produces()
                .body(serieId.toString())
                .post("/usuario/favorito/serie/remover");

        removerResponse.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Quando("removo a série com ID {long} dos favoritos")
    public void removerSerieDosFavoritos(Long serieId) {
        response = RestAssuredUtil.produces()
                .body(serieId.toString())
                .post("/usuario/favorito/serie/remover");
    }

    @Entao("sistema remove deve retornar status {int}")
    public void validarStatusRet(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Entao("a mensagem  retornada  {string}")
    public void validarMensagem(String mensagemEsperada) {
        String mensagem = response.getBody().asString();
        Assertions.assertEquals(mensagemEsperada, mensagem, "A mensagem retornada está incorreta.");
    }
}
