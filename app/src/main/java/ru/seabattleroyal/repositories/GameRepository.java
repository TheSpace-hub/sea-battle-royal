package ru.seabattleroyal.repositories;

import ru.seabattleroyal.game.Game;

import java.util.List;

public interface GameRepository {
    int createGame();

    void deleteGame(String gameId);

    Game getGame(String gameId);

    List<Integer> getGames();
}
