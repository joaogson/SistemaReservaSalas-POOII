package com.example.demo;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        Application.launch(SpringInitializer.class, args);
    }

}
