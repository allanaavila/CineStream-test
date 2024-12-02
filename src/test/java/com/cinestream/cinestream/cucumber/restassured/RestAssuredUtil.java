package com.cinestream.cinestream.cucumber.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;


public class RestAssuredUtil {
    private static String authToken; // Variável para armazenar o token

    public static RequestSpecification produces() {
        RequestSpecification request = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        if (authToken != null && !authToken.isEmpty()) {
            request.header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken); // Adiciona o cabeçalho de autenticação
        }

        return request;
    }

    public static void setAuthHeader(String token) {
        authToken = token;
    }
}
