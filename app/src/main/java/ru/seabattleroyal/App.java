package ru.seabattleroyal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostMapping("/create-game")
    public Map<String, Integer> createGame(@RequestBody Map<String, Integer> body) {
        System.out.println(body);
        System.out.println(body.get("number-of-players"));
        return Map.of("id", 0);
    }

}
