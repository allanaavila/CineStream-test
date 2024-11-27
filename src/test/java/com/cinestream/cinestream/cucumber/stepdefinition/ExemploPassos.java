package com.cinestream.cinestream.cucumber.stepdefinition;

import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import org.junit.jupiter.api.Assertions;

public class ExemploPassos {
    private boolean estouFeliz = false;

    public ExemploPassos() {
        System.out.println("init");
    }

    @Dado("que eu estou com fome")
    public void estouComFome() {
        System.out.println("estouComFome");
        estouFeliz = false;
    }

    @Quando("eu como uma pizza")
    public void comer(){
        System.out.println("comer");
        estouFeliz = true;
    }

    @Quando("eu como uma lasanha")
    public void comerLasanha(){
        System.out.println("comer");
        estouFeliz = true;
    }

    @Quando("eu como um pao de queijo")
    public void comerPaoDeQueijo(){
        System.out.println("comer");
        estouFeliz = true;
    }

    @Entao("eu estarei feliz")
    public void estouFeliz(){
        System.out.println("estouFeliz");
        Assertions.assertTrue(estouFeliz);
    }

}
