package com.cinestream.cinestream.cucumber.usuario;

import com.cinestream.cinestream.cucumber.restassured.RestAssuredUtil;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

public class UsuarioStepDefinition {

    private Response response;
    private UsuarioRequest usuario;


    @Dado("usuario nao cadastrado")
    public void usuarioNaoCadastrado() {

        usuario = new UsuarioRequest();
        usuario.setNome(RandomStringUtils.randomAlphabetic(10));
        usuario.setUsername(RandomStringUtils.randomAlphabetic(8));
        usuario.setEmail(RandomStringUtils.randomAlphabetic(10)+"@test.com");
        usuario.setSenha(RandomStringUtils.randomAlphabetic(12));
    }

    @Quando("realizo o cadastro do usuario")
    public void cadastrarUsuario(){
        response = RestAssuredUtil.produces()
                .body(usuario)
                .post("/register");
    }

    @Entao("usuario foi cadastrado com sucesso")
    public void sucessoCadastro() {
        response.then().statusCode(HttpStatus.SC_CREATED);
    }

    @E("exibe resultado da requisicao de usuario")
    public void exibeResponseUsuario() {
        var id = response.jsonPath().getString("id");
        Assertions.assertNotNull(id, "O ID do usuario nao deve ser nulo.");
    }

    @Dado("usuario ja cadastrado")
    public void usuarioCadastrado() {
        usuario = new UsuarioRequest();
        usuario.setNome(RandomStringUtils.randomAlphabetic(10));
        usuario.setUsername(RandomStringUtils.randomAlphabetic(8));
        usuario.setEmail(RandomStringUtils.randomAlphabetic(10)+"@test.com");
        usuario.setSenha(RandomStringUtils.randomAlphabetic(12));
        response = RestAssuredUtil.produces()
                .body(usuario)
                .post("/register");
        response.then().statusCode(HttpStatus.SC_CREATED);

    }

    @Entao("resposta da requisicao de usuario tenha status igual a {int}")
    public void verificarErroDeCadastroDuplicado(int status) {
        response = RestAssuredUtil.produces()
                .body(usuario)
                .post("/register");
        response.then().statusCode(status);
    }

    //Busca por email

    @Quando("realizo uma requisicao para buscar email do usuario cadastrado")
    public void realizo_uma_requisicao_para_buscar_o_email_do_usuario_cadastrado() {
        response = RestAssuredUtil.produces()
                .get("/usuario/" + usuario.getEmail());
    }

    @Entao("o usuario deve ser encontrado com status {int}")
    public void validarUsuarioEncontradoComStatus(int status) {
        response.then().statusCode(status);
        String emailRetornado = response.jsonPath().getString("email");
        Assertions.assertEquals(emailRetornado, usuario.getEmail());
    }

    @Dado("nenhum usuario com esse email")
    public void nenhumUsuarioComEmail() {}


    @Quando("realizo uma requisicao para buscar email invalido")
    public void buscarUsuarioPorEmailInvalido() {
        String emailInvalido = RandomStringUtils.randomAlphabetic(5) + "@test.com";
        response = RestAssuredUtil.produces()
                .get("/usuario/" + emailInvalido);
    }

    @Entao("resposta da requisicao de usuario tenha status igual {int}")
    public void validarUsuarioNaoEncontradoComStatus(int status) {
        response.then().statusCode(status);
    }
}