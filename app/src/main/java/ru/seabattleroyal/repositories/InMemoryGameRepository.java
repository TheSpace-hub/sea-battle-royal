package ru.seabattleroyal.repositories;

import org.springframework.stereotype.Component;
import ru.seabattleroyal.game.Game;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryGameRepository implements GameRepository {

    private final Map<String, Game> games;

    public InMemoryGameRepository() {
        games = new HashMap<>();
    }

    @Override
    public String createGame() {
        String gameId = Game.generateId();
        return gameId;
    }

    @Override
    public void deleteGame(String gameId) {
        games.remove(gameId);
    }

    @Override
    public Game getGame(String gameId) {
        return games.get(gameId);
    }

    @Override
    public Map<String, Game> getGames() {
        return games;
    }

}
