package com.example.demo.service;

import com.example.demo.entities.MeetingReserve;
import com.example.demo.entities.User;
import com.example.demo.repository.ReservaSalaRepository;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservaSalaService {

    @Autowired
    ReservaSalaRepository reservaSalaRepository;

    public void save(MeetingReserve meetingReserve)
    {
        reservaSalaRepository.save(meetingReserve);
    }
}
