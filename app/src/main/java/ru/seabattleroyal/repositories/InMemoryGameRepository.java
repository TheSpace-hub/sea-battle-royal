package ru.seabattleroyal.repositories;

import org.springframework.stereotype.Component;
import ru.seabattleroyal.game.Game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryGameRepository implements GameRepository {

    private final Map<String, Game> games;

    public InMemoryGameRepository() {
        games = new HashMap<>();
    }

    @Override
    public int createGame() {

        return 0;
    }

    @Override
    public void deleteGame(String gameId) {

    }

    @Override
    public Game getGame(String gameId) {
        return null;
    }

    @Override
    public List<Integer> getGames() {
        return List.of();
    }

}
