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

public class AdicionarSeriesFavoritasStepDefinition {

    private Response response;

    @Dado("autenticado no sistema com credenciais geradas aleatoriamente")
    public void estouAutenticadoComCredenciaisRemove() {
        AuthHelper.registrarEAutenticarUsuario();
    }

    @E("uma série com ID {long} já está nos meus favoritos")
    public void serieJaNosFavoritos(Long serieId) {
        Response adicionarResponse = RestAssuredUtil.produces()
                .body(serieId.toString())
                .post("/usuario/favorito/serie/adicionar");

        adicionarResponse.then().statusCode(HttpStatus.SC_ACCEPTED);
    }

    @Quando("realizo uma requisicao para adicionar a série {long} aos favoritos")
    public void adicionarSerieAosFavoritos(Long serieId) {
        response = RestAssuredUtil.produces()
                .body(serieId.toString())
                .post("/usuario/favorito/serie/adicionar");
    }

    @Entao("sistema retorna status {int}")
    public void validarStatusRetornadoRemove(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Entao("a mensagem retornada deve ser {string}")
    public void validarMensagemDeResposta(String mensagemEsperada) {
        String mensagem = response.getBody().asString();
        Assertions.assertEquals(mensagemEsperada, mensagem, "A mensagem retornada está incorreta.");
    }
}
