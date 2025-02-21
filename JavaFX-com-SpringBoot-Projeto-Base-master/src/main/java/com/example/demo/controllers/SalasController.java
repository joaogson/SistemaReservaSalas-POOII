package com.example.demo.controllers;

import com.example.demo.entities.Room;
import com.example.demo.service.RoomService;
import com.example.demo.telas.TelaListaReservas;
import com.example.demo.telas.TelaListaSalas;
import com.example.demo.utils.AbridorJanela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class SalasController implements Initializable {

    @FXML
    private ListView<Room> Salas = new ListView<Room>();

    /*
    *
    *   private final RoomService roomService;
    *
    *   public SalasController(RoomService roomService) {
    *       this.roomService = roomService;
    *   }
    *
    *   Vou testar a injeção de dependência com a annotation @Autowired (field injection).
    *   Você também pode utilizar a injeção via construtor, como demonstrado acima.
    *   Suspeito que o mesmo problema possa ocorrer com outros componentes.
    *
     * */
    @Autowired
    private TelaListaReservas telaListaReservas;
    @Autowired
    public RoomService roomService; // Problema: a injeção de dependência não está funcionando corretamente
    @Autowired
    private TelaListaSalas telaListaSalas;
    @Autowired
    private AbridorJanela abridorJanela;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Salas.setItems(roomService.buscarTodasSalas()); // Erro: roomService é null, causando NullPointerException

        Salas.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                if (empty || room == null) {
                    // Fail-fast: tratamos as exceções primeiro para evitar processamento desnecessário
                    setText(null);
                } else {
                    // Define o formato de exibição de cada sala
                    setText(room.getRoomType() + " - " + room.getLocalization() + " - " + room.isIsAvailable() + " - " + room.getEquipments() + " - " + room.getCapacity());
                }
            }
        });

        //Configurar a rotina de clicar na sala para visualizar as reservas da reuniao
        Salas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                telaListaReservas.abrir();
            }
        });
    }

}
