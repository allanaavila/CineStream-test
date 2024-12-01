package com.cinestream.cinestream.cucumber.series;

import com.cinestream.cinestream.cucumber.restassured.RestAssuredUtil;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class SerieStepDefinition {

    private Response response;

    @Dado("série com título {string} está cadastrada no sistema")
    public void serieCadastradaNoSistema(String titulo) {
        String url = String.format("/api/series?titulo=%s", titulo);
        response = RestAssuredUtil.produces()
                .get(url);
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Quando("realizo uma requisicao para buscar o título da serie {string}")
    public void buscarSeriePorTitulo(String titulo) {
        response = RestAssuredUtil.produces()
                .get("/api/series?titulo=" + titulo);
    }

    @Entao("a série deve ser encontrada com status {int}")
    public void validarSeriesEncontradaComStatus(int status) {
        response.then().statusCode(status);
    }

    @E("o título retornado da serie deve ser {string}")
    public void validarTituloSerieRetornada(String tituloSerieEsperado) {
        List<TmdbSerie> seriesRetornadas = response.jsonPath().getList("results", TmdbSerie.class);
        Assertions.assertFalse(seriesRetornadas.isEmpty(), "A lista de resultados está vazia!");
        TmdbSerie primeiraSerie = seriesRetornadas.get(0);
        Assertions.assertEquals(tituloSerieEsperado, primeiraSerie.getName(), "O título retornado está incorreto.");
    }

}
