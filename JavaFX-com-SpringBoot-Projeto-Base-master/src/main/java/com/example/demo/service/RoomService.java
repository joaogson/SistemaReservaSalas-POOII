package com.example.demo.service;

import com.example.demo.entities.Room;
import com.example.demo.repository.RoomRepository;
import jakarta.transaction.Transactional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired // Injeção de dependência adicionada aqui
    RoomRepository roomRepository;

    @Transactional
    public ObservableList<Room> buscarTodasSalas(){
        // Erro anterior: ClassCastException ao tentar converter ArrayList para ObservableList
        // return (ObservableList<Room>) roomRepository.findAll(); -> Caused by: java.lang.ClassCastException: class java.util.ArrayList cannot be cast to class javafx.collections.ObservableList
        Iterable<Room> rooms = roomRepository.findAll();
        ObservableList<Room> observableList = FXCollections.observableArrayList();
        rooms.forEach(observableList::add);
        /* Debug: imprimir os objetos para verificar se estão sendo carregados corretamente
        for (Room room : observableList) {
            System.out.println(room);
        } */
        return observableList;
    }
}
