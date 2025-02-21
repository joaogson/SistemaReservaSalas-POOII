package com.example.demo.entities;

import jakarta.persistence.*;
import jdk.jfr.Timespan;
import lombok.*;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class MeetingReserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date time;
    private Duration duration;
    private String requirements;
    private int participantsNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Room room;



}
