package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import org.springframework.stereotype.Component;

@Component
public class TelaListaSalas {

    AbridorJanela abridorJanela;

    public TelaListaSalas(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/tela-salas.fxml", "Salas", 700, 500);
    }

}
