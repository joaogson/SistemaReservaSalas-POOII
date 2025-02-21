package com.example.demo.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AbridorJanela {

    private final SpringFXMLLoader fxmlLoader;

    public AbridorJanela(SpringFXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void abrirNovaJanela(String fxmlPath, String titulo, double width, double height) {
        try {
            FXMLLoader loader = fxmlLoader.load(fxmlPath);
            /*
            *   LoadException ao clicar no botão salas na tela inicial
            *       Call stack:
            * 	        at com.example.demo.utils.SpringFXMLLoader.load(SpringFXMLLoader.java:24) ~[classes/:na]
            *           at com.example.demo.utils.AbridorJanela.abrirNovaJanela(AbridorJanela.java:21) ~[classes/:na]
            *           at com.example.demo.telas.TelaListaSalas.abrir(TelaListaSalas.java:16) ~[classes/:na]
            *           at com.example.demo.controllers.TelaInicialController.onAbrirTelaListaSalasButtonClick(TelaInicialController.java:26)
            *           .
            *           .
            *           .
            *       Caused by: java.lang.NullPointerException: Cannot invoke "com.example.demo.service.RoomService.buscarTodasSalas()" because "this.roomService" is null
            *           at com.example.demo.controllers.SalasController.initialize(SalasController.java:23) ~[classes/:na]
            *
            * ========================================================================================================//
            *
            *       1 - Alterei o metodo de log de erro no try-catch (linha 61)
            *       2 - Analisei a call stack para investigar o código
            *       3 - O metodo buscarTodasSalas do RoomService falha porque this.roomService é null
            *       4 - Isso indica que o SalasController não está instanciando corretamente o RoomService
            *       5 - O erro aponta que o objeto esperado não foi recebido, resultando em NullPointerException
            *       6 - Suspeito que o FXMLLoader esteja criando o SalasController sem injetar corretamente as dependências
            *       7 - Para testar, comentei a linha 11 do tela-salas.fxml e observei o comportamento do FXMLLoader
            *       8 - Concluí que o FXMLLoader instancia o controller, mas não interage bem com o Spring
            *       9 - A injeção de dependências falha, deixando RoomService como null
            *      10 - Corrigi a injeção no SalasController
            *      11 - No SalasController arrumei a injeção de dependência
            *      12 - No tela-salas.fxml, adicionei fx:id na linha 24
            *      13 - No RoomService, incluí uma annotation na linha 14 e converti o tipo corretamente no metodo buscarTodasSalas (linha 19)
            *      14 - No SalasController (linha 40), adicionei uma arrow function no Salas.setCellFactory() para a exibição dos registros na tela
            *
             * */
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle(titulo);
            stage.setWidth(width);
            stage.setHeight(height);
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        } catch (Exception e) {
            // System.out.println("Erro ao abrir janela: " + e.getMessage());
            log.error("error: ", e);
        }
    }
}