package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int Function;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<MeetingReserve> meetings = new ArrayList<MeetingReserve>();

    
}
