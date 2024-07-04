package org.example;
import java.util.ArrayList;

public class Board {
    public static final int BOARD_SIZE = 10;
    private char [][] board;
    private ArrayList<Ship> ships;

    public Board(){
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '~';
            }
        }
        ships = new ArrayList<>();
    }

    private char[][] getBoard(){
        return board;
    }

    public ArrayList<Ship> getShips(){
        return ships;
    }

    public char getCell(int x, int y){
        return board[x][y];
    }

    private void setBoardCellMarkShip(int one, int two){
        board[one][two] = '0';
    }

    public void setBoardCellHitShip(String coord){
        int coordX = (int)  coord.charAt(0) - 65;
        int coordY = Integer.parseInt(coord.substring(1, coord.length())) - 1;
        board[coordX][coordY] = 'X';
    }

    public void setBoardCellMissShip(String coord){
        int coordX = (int)  coord.charAt(0) - 65;
        int coordY = Integer.parseInt(coord.substring(1, coord.length())) - 1;
        board[coordX][coordY] = 'M';
    }

    private void setBoardCellRemoveShip(int one, int two){
        board[one][two] = '~';
    }

    public void printBoard(){
        System.out.print(" ");
        for (int i = 1; i <= BOARD_SIZE; i++){
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int j = 65; j < 65+BOARD_SIZE; j++){
            char c = (char) j;
            System.out.print(c + " ");
            for (int k = 0; k < BOARD_SIZE; k++){
                System.out.print(getBoard()[j-65][k]+ " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public boolean addShip(String coordOne, String coordTwo){
        Ship ship = new Ship(coordOne, coordTwo);
        ships.add(ship);
        updateBoardAddShip(ship);
        if (ship.isShipValid(this) == false){
            updateBoardRemoveShip(ship);
            ships.remove(ships.size() - 1);
            return false;
        }
        return true;
    }

    public boolean checkIsShipInCell(String coord){
        int coordX = (int)  coord.charAt(0) - 65;
        int coordY = Integer.parseInt(coord.substring(1, coord.length())) - 1;
        if (getCell(coordX, coordY) == '0'){
            return true;
        }
        return false;
    }


    public void updateBoardAddShip(Ship ship){
        for (int i = ship.getBeginX(); i <= ship.getEndX(); i++){
            for (int j = ship.getBeginY(); j <= ship.getEndY(); j++){
                setBoardCellMarkShip(i, j);
            }
        }
    }

    public void updateBoardRemoveShip(Ship ship){
        for (int i = ship.getBeginX(); i <= ship.getEndX(); i++){
            for (int j = ship.getBeginY(); j <= ship.getEndY(); j++){
                setBoardCellRemoveShip(i, j);
            }
        }
    }

    public Ship findShipWithCoord(String coord){
        int coordX = (int) coord.charAt(0) - 65;
        int coordY = Integer.parseInt(coord.substring(1, coord.length())) - 1;
        for (Ship ship: getShips()){
            if (ship.getIsHorizontal() && ship.getBeginX() == coordX){
                for (int i = ship.getBeginY(); i <= ship.getEndY(); i++){
                    if (i == coordY){
                        return ship;
                    }
                }
            }
            if (ship.getIsHorizontal() == false && ship.getBeginY() == coordY){
                for (int i = ship.getBeginX(); i <= ship.getEndX(); i++){
                    if (i == coordX){
                        return ship;
                    }
                }
            }
        }
        return null;
    }

    public boolean checkShipIsSunk(Ship ship){
        if (ship.getIsHorizontal()){
            int counter = 0;
            int x = ship.getBeginX();
            for (int y = ship.getBeginY(); y <= ship.getEndY(); y++){
                if (getCell(x, y) == 'X'){
                    counter += 1;
                }
            }
            if (counter == ship.getShipLength()){
                return true;
            }
        } else {
            int counter = 0;
            int y = ship.getBeginY();
            for (int x = ship.getBeginX(); x <= ship.getEndX(); x++){
                if (getCell(x, y) == 'X'){
                    counter += 1;
                }
            }
            if (counter == ship.getShipLength()){
                return true;
            }
        }
        return false;
    }

    public boolean checkAllShipsAreSunk(ArrayList<Ship> ships2){
        int counter = 0;
        for (Ship ship: ships2){
            boolean isShipSank = checkShipIsSunk(ship);
            if (isShipSank){
                counter += 1;
            }
        }
        if (counter == ships2.size()){
            return true;
        }
        return false;
    }

}
