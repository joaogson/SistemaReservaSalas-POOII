package com.example.demo.controllers;

import com.example.demo.telas.TelaListaSalas;
import com.example.demo.telas.TelaReservarSala;
import com.example.demo.telas.TelaSecundaria;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

@Controller
public class TelaInicialController {

    private TelaReservarSala telaReservarSala;
    private TelaListaSalas telaListaSalas;

    public TelaInicialController(TelaReservarSala telaReservarSala, TelaListaSalas telaListaSalas) {

        this.telaReservarSala = telaReservarSala;
        this.telaListaSalas = telaListaSalas;
    }

    @FXML
    protected void onAbrirTelaReservarButtonClick() {
        telaReservarSala.abrir();
    }

    @FXML
    protected void onAbrirTelaListaSalasButtonClick(){ telaListaSalas.abrir(); }

}
