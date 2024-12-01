package com.cinestream.cinestream.cucumber.filmes;

import com.cinestream.cinestream.cucumber.restassured.RestAssuredUtil;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class FilmeStepDefinition {
    private Response response;

    @Dado("filme com título {string} está cadastrado no sistema")
    public void filmeCadastradoNoSistema(String titulo) {
        String url = String.format("/api/filmes?titulo=%s", titulo);
        response = RestAssuredUtil.produces()
                .get(url);

        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Quando("realizo uma requisicao para buscar o título do filme {string}")
    public void buscarFilmePorTitulo(String titulo) {
        response = RestAssuredUtil.produces()
                .get("/api/filmes?titulo=" + titulo);
    }

    @Entao("o filme deve ser encontrado com status {int}")
    public void validarFilmeEncontradoComStatus(int status) {
        response.then().statusCode(status);
    }

    @E("o título retornado do filme deve ser {string}")
    public void validarTituloFilmeRetornado(String tituloEsperado) {
        List<TmdbFilme> filmesRetornados = response.jsonPath().getList("results", TmdbFilme.class);
        Assertions.assertFalse(filmesRetornados.isEmpty(), "A lista de resultados está vazia!");
        TmdbFilme primeiroFilme = filmesRetornados.get(0);
        Assertions.assertEquals(tituloEsperado, primeiroFilme.getTitle(), "O título retornado está incorreto.");
    }
}
