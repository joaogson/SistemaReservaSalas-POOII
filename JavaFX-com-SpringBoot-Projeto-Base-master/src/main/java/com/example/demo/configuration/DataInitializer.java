package com.example.demo.configuration;

import com.example.demo.entities.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDataBase(RoomRepository roomRepository){
        return args -> {
            // Verifica se o banco já tem dados
            if (roomRepository.count() == 0) {
                roomRepository.saveAll(List.of(
                        new Room("Bloco A - Terreo", "Projetor e Microfone", "Auditorio", 60),
                        new Room("Bloco B - 2 Andar","TV e mesas junto as cadeiras", "Sala Reunião", 10)
                ));
                System.out.println("Salas iniciais adicionadas ao banco!");
            } else {
                System.out.println("O banco já contém salas, não foi necessário adicionar.");
            }

        };
    }
}
