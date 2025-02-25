package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString(doNotUseGetters = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int Function;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<MeetingReserve> meetings = new ArrayList<MeetingReserve>();


    public User(String nome, int function) {
        this.name = nome;
        this.Function = function;
    }

    public User(int id, String nome, int Function){
        this.id = id;
        this.name = name;
        this.Function = Function;
    }

    public void setReservas(MeetingReserve meetingReserve){
        meetings.add(meetingReserve);
    }

    @Override
    public String toString(){
        return id + " - Usuario: " +
                name;
    }
}
