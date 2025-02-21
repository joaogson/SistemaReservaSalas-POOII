package com.example.demo.controllers;

import com.example.demo.entities.MeetingReserve;
import com.example.demo.entities.Room;
import com.example.demo.entities.User;
import com.example.demo.service.ReservaSalaService;
import com.example.demo.service.RoomService;
import com.example.demo.service.UserService;
import com.example.demo.utils.AbridorJanela;
import com.sun.javafx.scene.control.IntegerField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Controller
public class ReservaSalaController implements Initializable {

    @FXML
    private ComboBox responsavelComboBox;

    @FXML
    private ComboBox salaComboBox;

    @FXML
    private TextField dataReservaTextField;

    @FXML
    private TextField horarioInicioTextField;

    @FXML
    private TextField horarioFimTextField;

    @FXML
    private IntegerField capacidadeTextField;

    @FXML
    private TextArea descricaoTextArea;

    @Autowired
    private ReservaSalaService reservaSalaService;

    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;

    public ReservaSalaController(ReservaSalaService reservaSalaService){
        this.reservaSalaService = reservaSalaService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            ObservableList<User> Responsaveis = FXCollections.observableArrayList(userService.buscarTodos());
            ObservableList<String> nomesResponsaveis = FXCollections.observableArrayList(
                    Responsaveis.stream().map(User::getName).toList()
            );
            responsavelComboBox.setItems(nomesResponsaveis);

            ObservableList<Room> Salas = FXCollections.observableArrayList(roomService.buscarTodasSalas());
            ObservableList<Integer> IdSalas = FXCollections.observableArrayList(
                    Salas.stream().map(Room::getId).toList()
            );
            salaComboBox.setItems(IdSalas);
    }

    @FXML
    private void confirmaReservaOnButtonClick(){

        MeetingReserve meetingReserve = new MeetingReserve();
        meetingReserve.setUser(onActionBuscaUserPorId());
        meetingReserve.setDuration(SetDurationTime());
        meetingReserve.setTime(SetDataReserva());
        meetingReserve.setParticipantsNumber(capacidadeTextField.getValue());
        meetingReserve.setRequirements(descricaoTextArea.getText());
        reservaSalaService.save(meetingReserve);

    }

    //Puxar o id do responsavel
    public User onActionBuscaUserPorId(){
        return userService.buscarPorId(Integer.parseInt((String) responsavelComboBox.getValue())).orElseThrow();
    }

    //Converte os valores dos campos de duração para Duration
    private Duration SetDurationTime(){
        LocalTime horarioInicio = LocalTime.parse(horarioInicioTextField.getText());
        LocalTime horarioFim = LocalTime.parse(horarioFimTextField.getText());

        return Duration.between(horarioInicio, horarioFim);
    }

    //Converte o valor do campo data para Date
    private Date SetDataReserva(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(dataReservaTextField.getText());
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter a data!" + e);
        }
    }


}
