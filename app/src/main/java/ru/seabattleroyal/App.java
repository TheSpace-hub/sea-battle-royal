package ru.seabattleroyal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ru.seabattleroyal.game.Game;
import ru.seabattleroyal.repositories.GameRepository;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@SpringBootApplication
@RestController
public class App {

    private final GameRepository repository;
    private final SimpMessagingTemplate messagingTemplate;

    public App(GameRepository repository, SimpMessagingTemplate messagingTemplate) {
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostMapping("/api/create-game")
    public Map<String, String> createGame(@RequestBody Map<String, Integer> body) {
        int numberOfPlayers = body.get("number-of-players");
        if (numberOfPlayers > 5 || numberOfPlayers < 2)
            return Map.of("error", "Invalid number of players");

        String gameId = repository.createGame(numberOfPlayers);

        messagingTemplate.convertAndSend("/topic/updating-the-list-of-games", "");

        return Map.of("id", gameId);
    }

    @GetMapping("/api/list-of-games")
    public String getGames() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(repository.getGames());
    }

}
