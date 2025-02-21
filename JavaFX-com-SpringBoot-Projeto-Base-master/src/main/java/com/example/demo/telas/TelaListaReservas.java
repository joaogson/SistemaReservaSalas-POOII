package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import org.springframework.stereotype.Component;

@Component
public class TelaListaReservas {

    AbridorJanela abridorJanela;

    public TelaListaReservas(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/tela-lista-reservas.fxml", "Reservas", 700, 500);
    }

}
