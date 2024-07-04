package org.example;

import java.util.*;

public class Game {

    private Board boardOpponent1;
    private Board boardOpponent2;

    private Board boardGuessOpponent1;
    private Board boardGuessOpponent2;

    public Scanner scanner;
    public int turn = 1;
    public boolean game = true;
    public boolean placement = true;

    private String coordOne;
    private String coordTwo;

    public Game(){
        boardOpponent1 = new Board();
        boardOpponent2 = new Board();
        boardGuessOpponent1 = new Board();
        boardGuessOpponent2 = new Board();
        scanner = new Scanner(System.in);
    }

    public void execute(){
        ArrayList<String> shipsNames = new ArrayList<>();
        shipsNames.add("Aircraft Carrier (5 cells)");
        shipsNames.add("Battleship (4 cells)");
        shipsNames.add("Submarine (3 cells)");
        shipsNames.add("Cruiser (3 cells)");
        shipsNames.add("Destroyer(2 cells)");
        int i = 0;
        while (game){
            if (!placement){
                System.out.println("Player " + turn);
                System.out.println("Your guess board:");
                if (turn == 1){
                    boardGuessOpponent1.printBoard();
                    System.out.print("> ");
                    String coord = scanner.next();
                    boolean isHit = boardOpponent2.checkIsShipInCell(coord);
                    if (isHit){
                        boardGuessOpponent1.setBoardCellHitShip(coord);
                        Ship ship = boardOpponent2.findShipWithCoord(coord);
                        if (ship != null) {
                            boolean isSunk = boardGuessOpponent1.checkShipIsSunk(ship);
                            if (isSunk) {
                                System.out.println("You sank a ship!");
                            } else {
                                System.out.println("You hit a ship!");
                            }
                            boolean areAllSunk = boardGuessOpponent1.checkAllShipsAreSunk(boardOpponent2.getShips());
                            if (areAllSunk) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                            }
                        }
                    } else {
                        boardGuessOpponent1.setBoardCellMissShip(coord);
                        System.out.println("You missed a ship!");
                    }
                    turn = 2;
                } else if (turn == 2){
                    boardGuessOpponent2.printBoard();
                    System.out.print("> ");
                    String coord = scanner.next();
                    boolean isHit = boardOpponent1.checkIsShipInCell(coord);
                    if (isHit){
                        boardGuessOpponent2.setBoardCellHitShip(coord);
                        Ship ship = boardOpponent1.findShipWithCoord(coord);
                        if (ship != null) {
                            boolean isSunk = boardGuessOpponent2.checkShipIsSunk(ship);
                            if (isSunk) {
                                System.out.println("You sank a ship!");
                            } else {
                                System.out.println("You hit a ship!");
                            }
                            boolean areAllSunk = boardGuessOpponent2.checkAllShipsAreSunk(boardOpponent1.getShips());
                            if (areAllSunk) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                                game = false;
                            }
                        }
                    } else {
                        boardGuessOpponent2.setBoardCellMissShip(coord);
                        System.out.println("You missed a ship!");
                    }
                    turn = 1;
                }
            }
            else {
                System.out.println("Player " + turn);
                System.out.println("Your board:");
                if (turn == 1){
                    boardOpponent1.printBoard();
                } else if (turn == 2){
                    boardOpponent2.printBoard();
                }
                System.out.println("Enter the coordinates of the " + shipsNames.get(i));
                System.out.print("> ");
                coordOne = scanner.next();
                coordTwo = scanner.next();
                if (turn == 1) {
                    boolean isAdded = boardOpponent1.addShip(coordOne, coordTwo);
                    while (!isAdded) {
                        System.out.print("> ");
                        coordOne = scanner.next();
                        coordTwo = scanner.next();
                        isAdded = boardOpponent1.addShip(coordOne, coordTwo);
                    }

                    boardOpponent1.printBoard();
                    turn = 2;
                } else if (turn == 2) {
                    boolean isAdded = boardOpponent2.addShip(coordOne, coordTwo);
                    while (!isAdded) {
                        System.out.print("> ");
                        coordOne = scanner.next();
                        coordTwo = scanner.next();
                        isAdded = boardOpponent2.addShip(coordOne, coordTwo);
                    }
                    boardOpponent2.printBoard();
                    i += 1;
                    turn = 1;
                }
                if (boardOpponent1.getShips().size() == 5 && boardOpponent2.getShips().size() == 5){
                    placement = false;
                    System.out.println("The game starts!");
                }
            }
        }
    }

}
