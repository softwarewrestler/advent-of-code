package com.agaeg.aoc2021;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class BingoBoard {
    private List<Row> board;

    private int winningRow = -1;
    private int winningColumn = -1;

    public BingoBoard(List<String> input) {
        board = new ArrayList<Row>();
        initBoard(input);
    }

    private void initBoard(List<String> input) {
        for (String line : input) {
            board.add(new Row(line));
        }
    }

    public boolean playNumber(int number) {
        for (Row row : board) {
            row.playNumber(number);
        }
        return false;
    }


    /*
    public boolean isWinner() {
        return (winningRow > 0 || winningColumn > 0);
    }
    */

    public boolean isWinner() {
        for (Row row : board) {
            if (row.isWinningRow()) {
                return true;
            }
        }

        for ( int i=0; i< board.size(); i++) {
            if (isWinningColumn(i)) {
                return true;
            }
        }

        return false;
    }

    /*
    private boolean isWinningRow(int index) {
        Row row = board.get(index);
        return row.isWinningRow();
    }
    */

    private boolean isWinningColumn(int index) {
        return 
            board.get(0).checkMarked(index) &&
            board.get(1).checkMarked(index) &&
            board.get(2).checkMarked(index) &&
            board.get(3).checkMarked(index) &&
            board.get(4).checkMarked(index); 
    }


    public int calculateScore() {
        int score = 0;

        for (Row row : board) {
            score += row.calculateScore();
        }

        return score;
    }

    private class Row {
        ArrayList<Cell> cellRow = new ArrayList<Cell>();

        public Row(String row) {
            String [] parts = row.split("\\s+");
            for(int i=0; i<parts.length; i++) {
                cellRow.add(i, new Cell(Integer.parseInt(parts[i])));
            }
        }

        public boolean playNumber(int number) {
            for(Cell cell : cellRow) {
                if (cell.matchNumber(number)) {
                    return true;
                }
            }

            return false;
        }

        public boolean checkMarked(int index) {
            return cellRow.get(index).isMarked();
        }

        public String toString() {
            StringJoiner joiner = new StringJoiner(",", "[", "]");
            for(Cell cell : cellRow ) {
                joiner.add(cell.toString());
            }

            return joiner.toString();
        }

        public boolean isWinningRow() {
            for (Cell cell : cellRow) {
                if (! cell.isMarked()) {
                    return false;
                }
            }

            return true;
        }

        public int calculateScore() {
            int score = 0;
            for (Cell cell : cellRow) {
                if (! cell.isMarked()) {
                    score += cell.getValue();
                }
            }

            return score;
        }
    }

    private class Cell {
        boolean marked = false;
        int value;

        public Cell(int number) {
            value = number;
        }

        public void mark() {
            marked = true;
        }

        public boolean matchNumber(int number) {
            if (value == number) {
                mark();
                return true;
            }

            return false;
        }

        public void setValue (int number) {
            value = number;
        }

        public int getValue() {
            return value;
        }

        public boolean isMarked() {
            return marked;
        }


        public String toString(){
            String returnString = String.valueOf(value);
            if (isMarked()) {
                return returnString.concat("M");
            }

            return returnString;
        }
    }
}
