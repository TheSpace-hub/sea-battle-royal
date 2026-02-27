package ru.seabattleroyal.game;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Getter
@Setter
@Slf4j
public class Game {

    private static final Random random = new Random();

    private final Set<Player> players = new HashSet<>();
    private final int numberOfPlayers;
    private final Set<Field.Position> openCells = new HashSet<>();
    private Player currentPlayer;

    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public Player getPlayer(String uuid) {
        for (Player player : players) {
            if (player.getUuid().equals(uuid)) {
                return player;
            }
        }
        return null;
    }

    public boolean isPlayersReady() {
        return players.size() == numberOfPlayers && players.stream().allMatch(player -> player.getField() != null);
    }

    public void start() {
        assert !isPlayersReady();

        currentPlayer = players.stream().skip(random.nextInt(players.size())).findFirst().orElse(null);
        assert currentPlayer != null;
    }

    public static String generateId() {
        char[] code = new char[6];
        for (int i = 0; i < 6; i++) {
            code[i] = "abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt(36));
        }
        return new String(code);
    }

}
