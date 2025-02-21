package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Localization;
    private String Equipments;
    private String RoomType;
    private int Capacity;
    private boolean IsAvailable;

    @OneToOne
    private MeetingReserve meeting;

    public Room(String localization, String equipments, String roomType, int capacity) {
        this.Localization = localization;
        this.Equipments = equipments;
        this.RoomType = roomType;
        this.Capacity = capacity;
        IsAvailable = true;
    }
}
