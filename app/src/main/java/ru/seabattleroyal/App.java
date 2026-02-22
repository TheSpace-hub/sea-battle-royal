package ru.seabattleroyal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import ru.seabattleroyal.repositories.GameRepository;

import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class App {

    private final GameRepository repository;

    public App(GameRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostMapping("/create-game")
    public Map<String, String> createGame(@RequestBody Map<String, Integer> body) {
        String gameId = repository.createGame();
        return Map.of("id", gameId);
    }

}
