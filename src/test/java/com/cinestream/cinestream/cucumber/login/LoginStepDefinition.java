package com.cinestream.cinestream.cucumber.login;

import com.cinestream.cinestream.cucumber.restassured.RestAssuredUtil;
import com.cinestream.cinestream.cucumber.usuario.UsuarioRequest;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

public class LoginStepDefinition {

    private Response response;
    private UsuarioRequest usuario;
    private String authToken;

    @Dado("um usuário gerado com email e senha aleatórios")
    public void gerarUsuarioAleatorio() {
        usuario = new UsuarioRequest();
        usuario.setNome(RandomStringUtils.randomAlphabetic(10));
        usuario.setUsername(RandomStringUtils.randomAlphabetic(8));
        usuario.setEmail(RandomStringUtils.randomAlphabetic(10) + "@test.com");
        usuario.setSenha(RandomStringUtils.randomAlphabetic(12));
    }

    @Dado("o usuário foi cadastrado no sistema")
    public void usuarioFoiCadastrado() {
        response = RestAssuredUtil.produces()
                .body(usuario)
                .post("/register");
        response.then().statusCode(HttpStatus.SC_CREATED);
    }

    @Quando("realizo login com as credenciais do usuário")
    public void realizoLoginComCredenciais() {
        var loginRequest = new LoginRequest(usuario.getEmail(), usuario.getSenha());
        response = RestAssuredUtil.produces()
                .body(loginRequest)
                .post("/login");
    }

    @Quando("realizo login com credenciais inválidas")
    public void realizoLoginComCredenciaisInvalidas() {
        var loginRequest = new LoginRequest("naoexiste@test.com", "senhaIncorreta");
        response = RestAssuredUtil.produces()
                .body(loginRequest)
                .post("/login");
    }

    @Entao("o login deve ser realizado com sucesso")
    public void validarLoginComSucesso() {
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Entao("o token de autenticação deve ser gerado")
    public void validarTokenGerado() {
        authToken = response.jsonPath().getString("token");
        Assertions.assertNotNull(authToken, "O token de autenticação não foi gerado.");
    }

    @Entao("a requisição deve retornar status {int}")
    public void validarStatusDeErro(int status) {
        response.then().statusCode(status);
    }

    @Entao("a mensagem deve ser {string}")
    public void validarMensagemDeErro(String mensagemEsperada) {
        String mensagem = response.getBody().asString();
        Assertions.assertEquals(mensagemEsperada, mensagem);
    }
}
