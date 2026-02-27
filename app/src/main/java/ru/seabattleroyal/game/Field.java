package ru.seabattleroyal.game;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

public class Field {

    @Getter
    private final CellType[][] field;
    @Getter
    private final int sizeX;
    @Getter
    private final int sizeY;

    public Field(CellType defaultField) {
        sizeX = 10;
        sizeY = 10;
        field = new CellType[10][10];
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                field[y][x] = defaultField;
            }
        }
    }

    public Field(CellType[][] field) {
        sizeX = 10;
        sizeY = 10;
        this.field = field;
    }

    public void setCell(Position position, CellType cell) {
        setCell(position.getX(), position.getY(), cell);
    }

    public void setCell(int x, int y, CellType cell) {
        field[y][x] = cell;
    }

    public CellType getCell(Position position) {
        return getCell(position.getX(), position.getY());
    }

    public CellType getCell(int x, int y) {
        if (y >= 0 && y < field.length) {
            if (x < 0 || x >= field[0].length) {
                return null;
            }
        } else {
            return null;
        }
        return field[y][x];
    }

    @Override
    public String toString() {
        return "Field{" +
                "field=" + Arrays.toString(field) +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                '}';
    }

    @Getter
    public enum CellType {
        UNKNOWN(0),
        SHIP(1),
        EMPTY(2),
        WOUNDED(3),
        DEAD(4);

        private final int code;

        CellType(int code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code + "";
        }
    }

    @Getter
    @Setter
    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Position) {
                return (((Position) obj).getX() == this.x && ((Position) obj).getY() == this.y);
            }
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
