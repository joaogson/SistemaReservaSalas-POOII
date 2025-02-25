package com.example.demo.entities;

import jakarta.persistence.*;
import jdk.jfr.Timespan;
import lombok.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(doNotUseGetters = true)
public class MeetingReserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date time;
    private Time InitialTime;
    private Duration duration;
    private String requirements;
    private int participantsNumber;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Room room;

    public MeetingReserve(int id, Date time, Duration duration, String requirements, int participansNumber){
        this.id = id;
        this.time = time;
        this.duration = duration;
        this.requirements = requirements;
        this.participantsNumber = participansNumber;
    }

    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return "Sala: " + room + "\n" +
                " - Reponsável: " + user.getName() + "\n" +
                 " - Data: " +  formatter.format(time) + "\n" +
                " - Horario de Inicio: " + InitialTime + "\n" +
                " - Duração: " + duration.toHours() + " Horas" + "\n" +
                " - Localização: " + room.getLocalization();

    }


}
