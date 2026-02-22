package ru.seabattleroyal.game;

import java.util.Random;

public class Game {

    private static final Random random = new Random();

    public static String generateId() {
        char[] code = new char[6];
        for (int i = 0; i < 6; i++) {
            code[i] = "abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt(36));
        }
        return new String(code);
    }

}
