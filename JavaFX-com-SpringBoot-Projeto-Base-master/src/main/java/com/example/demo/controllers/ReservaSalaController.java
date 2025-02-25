package com.example.demo.controllers;

import com.example.demo.entities.MeetingReserve;
import com.example.demo.entities.Room;
import com.example.demo.entities.User;
import com.example.demo.service.ReservaSalaService;
import com.example.demo.service.RoomService;
import com.example.demo.service.UserService;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.javafx.scene.control.IntegerField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.net.URL;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ReservaSalaController implements Initializable {

    @FXML
    private ComboBox<User> responsavelComboBox;

    @FXML
    private ComboBox<Room> salaComboBox;

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

    @FXML
    private Label LabelConfirmacao;

    @Autowired
    private ReservaSalaService reservaSalaService;

    @Autowired
    private UserService userService;
    @Autowired
    private RoomService  roomService;

    public ReservaSalaController(ReservaSalaService reservaSalaService){
        this.reservaSalaService = reservaSalaService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            ObservableList<User> Responsaveis = FXCollections.observableArrayList(userService.buscarTodos());
            responsavelComboBox.setItems(Responsaveis);

            salaComboBox.setItems(getSalasDisponiveis());
    }

    @FXML
    private void confirmaReservaOnButtonClick() throws ParseException {

        Room room = onActionBuscaSalaPorId();
        User user = onActionBuscaUserPorId();
        MeetingReserve meetingReserve = new MeetingReserve();
        meetingReserve.setUser(user);
        meetingReserve.setRoom(room);
        meetingReserve.setDuration(SetDurationTime());
        meetingReserve.setInitialTime(SetInitialTime());
        meetingReserve.setTime(SetDataReserva());
        meetingReserve.setParticipantsNumber(capacidadeTextField.getValue());
        meetingReserve.setRequirements(descricaoTextArea.getText());
        user.setReservas(meetingReserve);

        reservaSalaService.save(meetingReserve);
        LabelConfirmacao.setText(meetingReserve.toString());

        meetingReserve.getRoom().setIsAvailable(false);
        salaComboBox.setItems(getSalasDisponiveis());
        roomService.Save(room);


    }

    //Puxar Id da Sala
    private Room onActionBuscaSalaPorId() {
        Room room = salaComboBox.getValue();
        return roomService.buscarSalaPorId(room.getId()).orElseThrow(() -> new RuntimeException("Valor Ausente"));
    }

    //Retorna as salas disponiveis para reserva
    private ObservableList<Room> getSalasDisponiveis(){
        ObservableList<Room> Salas = FXCollections.observableArrayList(roomService.buscarTodasSalas());
        List<Room> SalaDisponiveis = Salas.stream().filter(Room::isAvailable).collect(Collectors.toList());
        ObservableList<Room> ObservableSalas = FXCollections.observableArrayList(SalaDisponiveis);

        return ObservableSalas;
    }

    //Puxar o id do responsavel
    public User onActionBuscaUserPorId(){
        User user = responsavelComboBox.getValue();

        return userService.buscarPorId(user.getId()).orElseThrow(() -> new RuntimeException("Valor ausente!"));
    }

    private Time SetInitialTime() throws ParseException {

            String horarioInicial = horarioInicioTextField.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date dataFormatada = sdf.parse(horarioInicial);
            Time horarioInicio = new Time(dataFormatada.getTime());

            return horarioInicio;
    }

    //Converte os valores dos campos de duração para Duration
    private Duration SetDurationTime(){

        LocalTime horarioInicio = null;
        LocalTime horarioFim = null;
        String horarioInicialString = horarioInicioTextField.getText().trim();
        String horarioFinalString = horarioFimTextField.getText().trim();

        if (horarioInicialString.isEmpty() || horarioFinalString.isEmpty())
            throw new RuntimeException("Erro: Os campos de horário não podem estar vazios.");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            horarioInicio = LocalTime.parse(horarioInicialString, formatter);
            horarioFim = LocalTime.parse(horarioFinalString, formatter);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter horario! Formato deve ser em HH:mm");
        }
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
