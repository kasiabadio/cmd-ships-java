package org.example;

import lombok.Getter;
import lombok.Setter;

public class Ship {
    @Getter @Setter private int beginX;
    @Getter @Setter private int beginY;
    @Getter @Setter private int endX;
    @Getter @Setter private int endY;
    @Getter @Setter private boolean isSunk = false;
    @Getter private int shipLength;
    private boolean isHorizontal;

    public Ship(String coordOne, String coordTwo){
        if (coordOne.charAt(0) == coordTwo.charAt(0)){
            this.isHorizontal = true;
        } else {
            this.isHorizontal = false;
        }
        setShipsPositions(coordOne, coordTwo);
    }

    private void setIsHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public boolean getIsHorizontal(){
        return this.isHorizontal;
    }
    public void setShipLength(int one, int two){
        this.shipLength = Math.abs(one - two) + 1;
    }


    public boolean isShipValid(Board board){
        if (beginX > board.BOARD_SIZE || endX > board.BOARD_SIZE || beginY > board.BOARD_SIZE || endY > board.BOARD_SIZE){
            System.out.println("Error! Wrong ship location! Try again: ");
            return false;
        }
        if (getShipLength() > 5){
            System.out.println("Error! Wrong length of the Submarine! Try again:");
            return false;
        }
        if (hasShipsAdjacent(board)){
            System.out.println("Error! You placed it too close to another one. Try again: ");
            return false;
        }
        if (beginX == endX || beginY == endY){
            return true;
        }
        System.out.println("Error!");
        return false;
    }


    public boolean hasShipsAdjacent(Board board){
        if (getIsHorizontal()){
            int x = getBeginX() - 1;
            if (x >= 0){
                for (int y = getBeginY(); y <= getEndY(); y++){
                    char cell = board.getCell(x, y);
                    if (cell == '0'){
                        return true;
                    }
                }
            }
            x += 2;
            if (x < board.BOARD_SIZE){
                for (int y = getBeginY(); y <= getEndY(); y++){
                    char cell = board.getCell(x, y);
                    if (cell == '0'){
                        return true;
                    }
                }
            }
            int y = getBeginY() - 1;
            if (y >= 0){
                for (x = getBeginX() - 1; x <= getEndX()+ 1; x++){
                    if (x >= 0 && x < board.BOARD_SIZE){
                        char cell = board.getCell(x, y);
                        if(cell == '0'){
                            return true;
                        }
                    }
                }
            }

            y = getEndY() + 1;
            if (y < board.BOARD_SIZE){
                for (x = getBeginX() - 1; x <= getEndX()+ 1; x++){
                    if (x >= 0 && x < board.BOARD_SIZE){
                        char cell = board.getCell(x, y);
                        if(cell == '0'){
                            return true;
                        }
                    }
                }
            }

        } else {
            int y = getBeginY() - 1;
            if (y >= 0){
                for (int x = getBeginX() - 1; x < getEndX() + 1; x++){
                    if (x >= 0 && x < board.BOARD_SIZE){
                        char cell = board.getCell(x, y);
                        if (cell == '0'){
                            return true;
                        }
                    }
                }
            }

            y = getEndY() + 1;
            if (y < board.BOARD_SIZE){
                for (int x = getBeginX() - 1; x < getEndX() - 1; x++){
                    if (x >= 0 && x < board.BOARD_SIZE) {
                        char cell = board.getCell(x, y);
                        if (cell == '0') {
                            return true;
                        }
                    }
                }
            }

            int x = getBeginX() - 1;
            if (x >= 0){
                for (y = getBeginY() - 1; y <= getEndY() + 1; y++){
                    if (y >= 0 && y < board.BOARD_SIZE){
                        char cell = board.getCell(x, y);
                        if (cell == '0'){
                            return true;
                        }
                    }
                }
            }

            x = getEndX() + 1;
            if (x < board.BOARD_SIZE){
                for (y = getBeginY() - 1; y <= getEndY() + 1; y++){
                    if (y >= 0 && y < board.BOARD_SIZE){
                        char cell = board.getCell(x, y);
                        if (cell == '0'){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void setShipsPositions(String coordOne, String coordTwo){
        int coordOneX = (int)  coordOne.charAt(0) - 65;
        int coordOneY = Integer.parseInt(coordOne.substring(1, coordOne.length())) - 1;
        int coordTwoX = (int) coordTwo.charAt(0) - 65;
        int coordTwoY = Integer.parseInt(coordTwo.substring(1, coordTwo.length())) - 1;
        if (coordOneX == coordTwoX){
            setIsHorizontal(true);
            setShipLength(coordOneY, coordTwoY);
            setBeginX(coordOneX);
            setEndX(coordTwoX);
            if (coordOneY > coordTwoY){
                setBeginY(coordTwoY);
                setEndY(coordOneY);
            }  else {
                setEndY(coordTwoY);
                setBeginY(coordOneY);
            }
        } else if (coordOneY == coordTwoY) {
            setIsHorizontal(false);
            setShipLength(coordOneX, coordTwoX);
            setBeginY(coordOneY);
            setEndY(coordTwoY);
            if (coordOneX > coordTwoX){
                setBeginX(coordTwoX);
                setEndX(coordOneX);
            }  else {
                setEndX(coordTwoX);
                setBeginX(coordOneX);
            }
        }
    }




}
