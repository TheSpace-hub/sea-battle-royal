package ru.seabattleroyal.repositories;

import ru.seabattleroyal.game.Game;

import java.util.Map;

public interface GameRepository {
    String createGame();

    void deleteGame(String gameId);

    Game getGame(String gameId);

    Map<String, Game> getGames();
}
