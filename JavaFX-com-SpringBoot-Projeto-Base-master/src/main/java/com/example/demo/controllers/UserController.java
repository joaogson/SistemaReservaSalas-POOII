package com.example.demo.controllers;

import com.example.demo.entities.MeetingReserve;
import com.example.demo.entities.User;
import com.example.demo.service.ReservaSalaService;
import com.example.demo.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Controller
public class UserController implements Initializable {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cargoTextField;

    @FXML
    private Button adicionaUsuarioButton;

    @FXML
    private Button editarUsuarioButton;

    @FXML
    private Button excluirUsuarioButton;

    @FXML
    private ListView<User> usuarios = new ListView<User>();

    @FXML
    private Label LabelConfirmaAcao;

    @FXML
    private ListView<MeetingReserve>ReservasUsuario = new ListView<MeetingReserve>();

    @FXML
    private VBox VboxReservas;

    @Autowired
    UserService userService;

    private ObservableList<MeetingReserve> ObservableReservas = FXCollections.observableArrayList();

    @Autowired
    ReservaSalaService reservaSalaService;

    public UserController() {}


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

        ReservasUsuario.setCellFactory(param -> new javafx.scene.control.ListCell<>(){
            @Override
            protected void updateItem(MeetingReserve meeting, boolean empty) {
                super.updateItem(meeting, empty);
                setText((empty || meeting == null) ? null : meeting.getRoom().getLocalization());
            }
        });

        //Define no Campo  CargoTextField como Administrador ou Usuario
        usuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){

                ReservasUsuario.setItems(UpdateListViewReservas(newSelection));
            } else {
                ObservableReservas.clear();
            }

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
    private void AtualizaListView(){
        usuarios.setItems(userService.buscarTodos());
    }

    @FXML
    private ObservableList<MeetingReserve> UpdateListViewReservas(User user){

        ObservableReservas.setAll(user.getMeetings());
        return ObservableReservas;
    }

    @FXML
    private void HabilitarBotoes(){
        //se Listview selecionado  ==> Edit Habilitado, Excluir desabilitado, Adicionar Desabilitado
        if(usuarios.getSelectionModel().getSelectedItem() != null){
            editarUsuarioButton.setDisable(false);
            excluirUsuarioButton.setDisable(false);
            VboxReservas.setVisible(true);

        }
        //se ListView selecionado ==> Excluir HABILITADO, Edit HABILITADO, Adicionar DESABILITADO
        if(cargoTextField.getText().isEmpty() && nomeTextField.getText().isEmpty()){
            editarUsuarioButton.setDisable(true);
            excluirUsuarioButton.setDisable(true);
            adicionaUsuarioButton.setDisable(false);
            VboxReservas.setVisible(false);
        }



    }

    @FXML
    private void onAdicionarButtonClick(){
        User user = new User();
        user.setName(nomeTextField.getText());
        user.setFunction(Objects.equals(cargoTextField.getText(), "Administrador") ? 1 : 0);

        if(user.getName() != null|| user.getFunction() != 0)
            userService.save(user);
        else{
            System.out.println("Algum dos atributos estão nulos!");
        }
        LabelConfirmaAcao.setText("Usuario Adicionado!");
        AtualizaListView();
        HabilitarBotoes();
    }
    @FXML
    private void onExcluirButtonClick(){
       User user = usuarios.getSelectionModel().getSelectedItem();
        userService.delete(user);
        usuarios.setItems(userService.buscarTodos());
        LabelConfirmaAcao.setText("Usuario excluido!");
        AtualizaListView();
        HabilitarBotoes();

    }
    @FXML
    private void onEditarButtonClick(){
       User user = usuarios.getSelectionModel().getSelectedItem();

        user.setName(nomeTextField.getText());
        user.setFunction(Objects.equals(cargoTextField.getText(), "Administrador") ? 1 : 0);
        userService.edit(user);
        LabelConfirmaAcao.setText("Editar Usuario!");
        AtualizaListView();
        HabilitarBotoes();
    }
}
