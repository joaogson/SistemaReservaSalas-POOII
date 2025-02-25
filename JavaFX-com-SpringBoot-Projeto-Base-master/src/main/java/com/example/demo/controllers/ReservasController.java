package com.example.demo.controllers;

import com.example.demo.entities.MeetingReserve;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservasController implements Initializable {

    @FXML
    private ListView<MeetingReserve> reservas = new ListView<MeetingReserve>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
