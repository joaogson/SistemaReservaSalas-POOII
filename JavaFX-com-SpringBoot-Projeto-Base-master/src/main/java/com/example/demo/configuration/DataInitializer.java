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

                if(roomRepository.count()==0) {
                    roomRepository.saveAll(List.of(
                            new Room("Bloco A - 1º Andar", "Computadores e Lousa", "Sala de Desenvolvimento", 15),
                            new Room("Bloco B - 2º Andar", "Mesa Redonda e Lousa", "Sala de Reunião Pequena", 8),
                            new Room("Bloco C - 3º Andar", "Mesas e Projetor e Tela", "Sala de Reunião", 20),
                            new Room("Bloco D - Térreo", "Microfone e Telão", "Auditório", 50),
                            new Room("Bloco A - 2º Andar", "Computadores e Projetor e Tela", "Sala de Desenvolvimento", 10),
                            new Room("Bloco B - 1º Andar", "Mesa Redonda e Lousa", "Sala de Reunião Pequena", 6),
                            new Room("Bloco C - 4º Andar", "Computadores e Telão", "Sala de Desenvolvimento", 12),
                            new Room("Bloco D - 3º Andar", "Lousa e Projetor e Tela", "Sala de Reunião", 5),
                            new Room("Bloco A - 5º Andar", "Mesas e Computadores", "Sala de Reunião Pequena", 4),
                            new Room("Bloco B - Térreo", "Microfone e Projetor e Tela", "Sala de Reunião", 10),
                            new Room("Bloco C - 2º Andar", "Projetor e Tela e Lousa", "Sala de Reunião Pequena", 7),
                            new Room("Bloco D - 1º Andar", "Mesa Redonda e Computadores", "Sala de Desenvolvimento", 8),
                            new Room("Bloco A - 3º Andar", "Computadores e Telão", "Sala de Desenvolvimento", 12),
                            new Room("Bloco B - 4º Andar", "Lousa e Microfone", "Sala de Reunião", 18),
                            new Room("Bloco C - 5º Andar", "Mesas e Telão", "Auditório", 60),
                            new Room("Bloco D - 2º Andar", "Microfone e Computadores", "Sala de Desenvolvimento", 14),
                            new Room("Bloco A - 4º Andar", "Projetor e Tela e Lousa", "Sala de Desenvolvimento", 10),
                            new Room("Bloco B - 5º Andar", "Mesa Redonda e Microfone", "Sala de Reunião Pequena", 9),
                            new Room("Bloco C - Térreo", "Computadores e Telão", "Auditório", 55),
                            new Room("Bloco D - 5º Andar", "Lousa e Telão", "Sala de Reunião", 11),
                            new Room("Bloco A - Térreo", "Mesas e Microfone", "Auditório", 80),
                            new Room("Bloco B - 3º Andar", "Computadores e Projetor e Tela", "Sala de Desenvolvimento", 13),
                            new Room("Bloco C - 1º Andar", "Mesas e Lousa", "Sala de Reunião", 9),
                            new Room("Bloco D - 4º Andar", "Mesa Redonda e Telão", "Sala de Desenvolviment", 16),
                            new Room("Bloco A - 5º Andar", "Computadores e Microfone", "Sala de Reunião Pequena", 6)
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
