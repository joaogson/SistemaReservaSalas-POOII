package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import org.springframework.stereotype.Component;

@Component
public class TelaUsuarios {

    AbridorJanela abridorJanela;

    public TelaUsuarios(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/tela-usuarios.fxml", "Usuarios", 700, 500);
    }

}
