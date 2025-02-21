package com.example.demo.service;

import com.example.demo.entities.MeetingReserve;
import com.example.demo.repository.ReservaSalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaSalaService {

    @Autowired
    ReservaSalaRepository reservaSalaRepository;

    public MeetingReserve save(MeetingReserve meetingReserve)
    {
        return reservaSalaRepository.save(meetingReserve);
    }
}
