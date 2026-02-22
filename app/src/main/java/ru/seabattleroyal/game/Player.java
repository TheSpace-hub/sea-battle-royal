package ru.seabattleroyal.game;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Player {

    String username;
    UUID uuid;
    Field field;

    public Player(String username) {
        this.username = username;
        this.uuid = UUID.randomUUID();
        this.field = new Field(Field.CellType.UNKNOWN);
    }

}
