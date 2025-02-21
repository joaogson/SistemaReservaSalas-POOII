package com.example.demo.controllers;

import com.example.demo.telas.TelaListaSalas;
import com.example.demo.telas.TelaReservarSala;
import com.example.demo.telas.TelaUsuarios;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

@Controller
public class TelaInicialController {

    private TelaReservarSala telaReservarSala;
    private TelaListaSalas telaListaSalas;
    private TelaUsuarios telaUsuarios;

    public TelaInicialController(TelaReservarSala telaReservarSala, TelaListaSalas telaListaSalas, TelaUsuarios telaUsuarios) {

        this.telaReservarSala = telaReservarSala;
        this.telaListaSalas = telaListaSalas;
        this.telaUsuarios = telaUsuarios;
    }

    @FXML
    protected void onAbrirTelaReservarButtonClick() {
        telaReservarSala.abrir();
    }

    @FXML
    protected void onAbrirTelaListaSalasButtonClick(){ telaListaSalas.abrir(); }

    @FXML
    protected void onAbrirTelaUsuariosButtonClick(){
        telaUsuarios.abrir();
    }

}


