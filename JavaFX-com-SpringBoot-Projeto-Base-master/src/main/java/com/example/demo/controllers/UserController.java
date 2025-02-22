package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;


@Controller
public class UserController implements Initializable {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cargoTextField;

    @FXML
    private Button confirmarButton;

    @FXML
    private Button adicionaUsuarioButton;

    @FXML
    private Button editarUsuarioButton;

    @FXML
    private Button excluirUsuarioButton;

    @FXML
    private ListView<User> usuarios = new ListView<User>();

    @Autowired
    UserService userService;

    public UserController() {}

//Tentar arrumar a ListView para aparecer os usuarios
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usuarios.setItems(userService.buscarTodos());

        usuarios.setCellFactory(param -> new javafx.scene.control.ListCell<>(){
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if(empty || user == null){
                    setText(null);
                } else {
                    setText(user.getName());
                }
            }

        });

        //Define no Campo  CargoTextField como Administrador ou Usuario
        usuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (usuarios.getSelectionModel().getSelectedItem() != null) {
                nomeTextField.setText(newSelection.getName());
                if(newSelection.getFunction() == 1){
                    cargoTextField.setText("Usuario");
                } else if (newSelection.getFunction() == 2) {
                    cargoTextField.setText("Administrador");
                }

            } else {
                cargoTextField.setText("");
                nomeTextField.setText("");
            }
        });
    }



    @FXML
    private void HabilitarBotoes(){
        //se Listview selecionado  ==> Edit Habilitado, Excluir desabilitado, Adicionar Desabilitado
        if(usuarios.getSelectionModel().getSelectedItem() != null){
            editarUsuarioButton.setDisable(false);
            excluirUsuarioButton.setDisable(false);
            adicionaUsuarioButton.setDisable(true);
        }
        //se ListView selecionado ==> Excluir HABILITADO, Edit HABILITADO, Adicionar DESABILITADO
        if(cargoTextField.getText().isEmpty() && nomeTextField.getText().isEmpty()){
            editarUsuarioButton.setDisable(true);
            excluirUsuarioButton.setDisable(true);
            adicionaUsuarioButton.setDisable(false);
        }

    }

    @FXML
    private void onAdicionarButtonClick(){

    }
    @FXML
    private void onExcluirButtonClick(){

    }
    @FXML
    private void onEditarButtonClick(){

    }
}
