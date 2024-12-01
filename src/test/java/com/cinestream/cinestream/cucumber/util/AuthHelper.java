package com.cinestream.cinestream.cucumber.util;

import com.cinestream.cinestream.cucumber.login.LoginRequest;
import com.cinestream.cinestream.cucumber.restassured.RestAssuredUtil;
import com.cinestream.cinestream.cucumber.usuario.UsuarioRequest;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;

public class AuthHelper {
    private static String authToken;

    public static String registrarEAutenticarUsuario() {
        UsuarioRequest usuario = criarUsuarioAleatorio();
        Response registroResponse = RestAssuredUtil.produces()
                .body(usuario)
                .post("/register");
        registroResponse.then().statusCode(HttpStatus.SC_CREATED);
        return autenticar(usuario.getEmail(), usuario.getSenha());
    }

    public static String autenticar(String email, String senha) {
        var loginRequest = new LoginRequest(email, senha);
        Response loginResponse = RestAssuredUtil.produces()
                .body(loginRequest)
                .post("/login");
        loginResponse.then().statusCode(HttpStatus.SC_OK);
        authToken = loginResponse.jsonPath().getString("token");
        RestAssuredUtil.setAuthHeader(authToken);
        return authToken;
    }

    private static UsuarioRequest criarUsuarioAleatorio() {
        UsuarioRequest usuario = new UsuarioRequest();
        usuario.setNome(RandomStringUtils.randomAlphabetic(10));
        usuario.setUsername(RandomStringUtils.randomAlphabetic(8));
        usuario.setEmail(RandomStringUtils.randomAlphabetic(10) + "@test.com");
        usuario.setSenha(RandomStringUtils.randomAlphabetic(12));
        return usuario;
    }

    public static String getAuthToken() {
        return authToken;
    }
}
