package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Optional<User> buscarPorId(int id)
    {
        return userRepository.findById((long) id);
    }

    @Transactional
    public ObservableList<User> buscarTodos(){
        Iterable<User> users = userRepository.findAll();
        ObservableList<User> observableList = FXCollections.observableArrayList();
        users.forEach(observableList::add);
        return observableList;
    }

    @Transactional
    public void save(User user){
        try{
            if(user != null)
                userRepository.save(user);
        } catch (NullPointerException e){
            System.out.println("Erro: Usuario nulo");
        }
    }

    @Transactional
    public void delete(User user){
        userRepository.delete(user);
    }

    @Transactional
    public void edit(User user){
        userRepository.findById((long)user.getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        User userAux = new User(user.getId(), user.getName(), user.getFunction());
        save(user);
    }
}
