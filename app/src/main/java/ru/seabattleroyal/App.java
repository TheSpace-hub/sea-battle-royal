package ru.seabattleroyal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import ru.seabattleroyal.game.Game;
import ru.seabattleroyal.repositories.GameRepository;
import tools.jackson.databind.ObjectMapper;

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
        int numberOfPlayers = body.get("number-of-players");
        if (numberOfPlayers > 5 || numberOfPlayers < 2)
            return Map.of("error", "Invalid number of players");

        String gameId = repository.createGame(numberOfPlayers);
        return Map.of("id", gameId);
    }

    @GetMapping("/list-of-games")
    public String getGames() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(repository.getGames());
    }

}
