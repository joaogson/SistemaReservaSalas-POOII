package com.example.demo.repository;

import com.example.demo.entities.MeetingReserve;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface ReservaSalaRepository extends CrudRepository<MeetingReserve, Integer>{ }

