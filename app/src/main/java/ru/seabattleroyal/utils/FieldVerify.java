package ru.seabattleroyal.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.seabattleroyal.game.Field;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FieldVerify {

    public boolean isFieldCorrect(Field field) {
        List<Ship> ships = getShipsList(field);
        if (ships == null) return false;
        return ships.stream().filter(ship -> ship.positions.size() == 1).count() == 4 &&
                ships.stream().filter(ship -> ship.positions.size() == 2).count() == 3 &&
                ships.stream().filter(ship -> ship.positions.size() == 3).count() == 2 &&
                ships.stream().filter(ship -> ship.positions.size() == 4).count() == 1 &&
                ships.size() == 10;
    }

    private List<Ship> getShipsList(Field field) {
        int targetX = 0;
        int targetY = 0;
        List<Ship> ships = new ArrayList<>();

        while (true) {
            while (field.getCell(targetX, targetY) != Field.CellType.SHIP || isCellInShipsList(ships, targetX, targetY)) {
                if (targetX == field.getSizeX() - 1 && targetY == field.getSizeY() - 1) {
                    return ships;
                }
                targetX++;
                if (targetX == field.getSizeX()) {
                    targetX = 0;
                    targetY++;
                }
            }

            Direction direction = getShipDirection(field, targetX, targetY);
            if (direction == null) return null;

            Ship ship = new Ship();
            for (int i = 0; i < countLengthOfShipCells(field, targetX, targetY, direction); i++) {
                ship.positions.add(new Field.Position(
                        targetX + (direction == Direction.RIGHT ? i : 0),
                        targetY + (direction == Direction.DOWN ? i : 0)
                ));
            }
            ships.add(ship);
        }
    }

    private boolean isCellInShipsList(List<Ship> ships, int x, int y) {
        for (Ship ship : ships) {
            if (ship.positions.contains(new Field.Position(x, y))) {
                return true;
            }
        }
        return false;
    }

    private int countLengthOfShipCells(Field field, int x, int y, Direction direction) {
        int shipCells = 1;
        x += direction == Direction.RIGHT ? 1 : 0;
        y += direction == Direction.DOWN ? 1 : 0;
        while (!isCellEmptyOrOffTheMap(field, x, y)) {
            if (isCellShip(field, x, y)) {
                if (direction == Direction.RIGHT &&
                        isCellEmptyOrOffTheMap(field, x + 1, y - 1) &&
                        isCellEmptyOrOffTheMap(field, x + 1, y + 1)) {
                    shipCells++;
                    x += 1;
                } else if (direction == Direction.DOWN &&
                        isCellEmptyOrOffTheMap(field, x - 1, y + 1) &&
                        isCellEmptyOrOffTheMap(field, x + 1, y + 1)) {
                    shipCells++;
                    y += 1;
                } else {
                    return -1;
                }
            }
        }

        return shipCells;
    }

    private Direction getShipDirection(Field field, int targetX, int targetY) {
        if (isCellEmptyOrOffTheMap(field, targetX - 1, targetY - 1) &&
                isCellEmptyOrOffTheMap(field, targetX, targetY - 1) &&
                isCellEmptyOrOffTheMap(field, targetX + 1, targetY - 1) &&
                isCellEmptyOrOffTheMap(field, targetX - 1, targetY + 1) &&
                isCellEmptyOrOffTheMap(field, targetX, targetY + 1) &&
                isCellEmptyOrOffTheMap(field, targetX + 1, targetY + 1) &&
                isCellEmptyOrOffTheMap(field, targetX - 1, targetY)) {
            return Direction.RIGHT;
        } else if (isCellEmptyOrOffTheMap(field, targetX - 1, targetY - 1) &&
                isCellEmptyOrOffTheMap(field, targetX - 1, targetY) &&
                isCellEmptyOrOffTheMap(field, targetX - 1, targetY + 1) &&
                isCellEmptyOrOffTheMap(field, targetX + 1, targetY - 1) &&
                isCellEmptyOrOffTheMap(field, targetX + 1, targetY) &&
                isCellEmptyOrOffTheMap(field, targetX + 1, targetY + 1) &&
                isCellEmptyOrOffTheMap(field, targetX, targetY - 1)) {
            return Direction.DOWN;
        }

        return null;
    }

    private boolean isCellEmptyOrOffTheMap(Field field, int targetX, int targetY) {
        if (targetX < 0 || targetY < 0 || targetX >= field.getSizeX() || targetY >= field.getSizeY()) {
            return true;
        } else return field.getCell(targetX, targetY) == Field.CellType.EMPTY;
    }

    private boolean isCellShip(Field field, int targetX, int targetY) {
        if (targetX < 0 || targetY < 0 || targetX >= field.getSizeX() || targetY >= field.getSizeY()) {
            return false;
        } else return field.getCell(targetX, targetY) == Field.CellType.SHIP;
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private void printPrettyField(Field field) {
        StringBuffer pretty = new StringBuffer();

        for (int y = 0; y < field.getSizeY(); y++) {
            for (int x = 0; x < field.getSizeX(); x++) {
                if (field.getCell(x, y) == Field.CellType.EMPTY) {
                    pretty.append("0");
                } else if (field.getCell(x, y) == Field.CellType.SHIP) {
                    pretty.append("#");
                } else {
                    pretty.append(field.getCell(x, y));
                }
            }
            pretty.append("\n");
        }

        log.info("PF: \n{}", pretty);
    }

    private static class Ship {
        public List<Field.Position> positions;

        public Ship() {
            this.positions = new ArrayList<>();
        }

        public boolean isAlive(List<Field.Position> openCells) {
            for (Field.Position position : positions) {
                if (!openCells.contains(position)) {
                    return true;
                }
            }
            return false;
        }

        public static Ship getShipByCell(List<Ship> ships, Field.Position position) {
            for (Ship ship : ships) {
                if (ship.positions.contains(position)) {
                    return ship;
                }
            }
            return null;
        }

    }

}