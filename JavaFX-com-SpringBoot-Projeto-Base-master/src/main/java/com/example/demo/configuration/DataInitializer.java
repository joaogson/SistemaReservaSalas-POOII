package com.example.demo.configuration;

import com.example.demo.entities.Room;
import com.example.demo.entities.User;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    private CommandLineRunner commandLineRunner;

    @Bean
    CommandLineRunner initDataBase(RoomRepository roomRepository, UserRepository userRepository) {
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

            if(userRepository.count() == 0) {
                userRepository.saveAll(List.of(
                   new User("Jean", 2),
                   new User("Joao", 2)
                ));
                System.out.println("Usuarios iniciais adicionados ao banco!");
            } else {
                System.out.println("O banco já contém usuarios, não foi necessário adicionar.");
            }
        };
    }
}
