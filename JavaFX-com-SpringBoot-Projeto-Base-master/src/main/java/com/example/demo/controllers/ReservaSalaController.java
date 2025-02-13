package com.example.demo.controllers;

import com.example.demo.entities.MeetingReserve;
import com.example.demo.service.ReservaSalaService;
import com.example.demo.service.UserService;
import com.sun.javafx.scene.control.IntegerField;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class ReservaSalaController {

    @FXML
    private TextField responsavelTextField;

    @FXML
    private ComboBox salaComboBox;

    @FXML
    private TextField salaTextField;

    @FXML
    private TextField horarioInicioTextField;

    @FXML
    private TextField horarioFimTextField;

    @FXML
    private IntegerField capacidadeTextField;

    @FXML
    private TextArea descricaoTextArea;

    private ReservaSalaService reservaSalaService;
    private UserService userService;

    public ReservaSalaController(ReservaSalaService reservaSalaService){
        this.reservaSalaService = reservaSalaService;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void confirmaReservaOnButtonClick(){

        MeetingReserve meetingReserve = new MeetingReserve();

    }

    //Puxar o id do responsavel
    public void onKeyTypedIdUser(){

    }
}
