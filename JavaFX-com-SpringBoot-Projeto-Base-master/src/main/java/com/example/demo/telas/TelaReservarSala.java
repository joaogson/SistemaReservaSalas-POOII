package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import org.springframework.stereotype.Component;

@Component
public class TelaReservarSala {

    AbridorJanela abridorJanela;

    public TelaReservarSala(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/tela-reservar-sala.fxml", "Reserva sala", 700, 500);
    }

}
