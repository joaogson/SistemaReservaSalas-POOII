package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import org.springframework.stereotype.Component;

@Component
public class TelaSecundaria {

    AbridorJanela abridorJanela;

    public TelaSecundaria(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/tela-lista-salas.fxml", "Lista das Salas", 700, 500);
    }

}
