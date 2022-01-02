package com.agaeg.aoc2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.util.Pair;

public class Day4 
{
    public int partOne(Scanner scanner) {
        ArrayList<String> input = AocUtil.scannerToLineList(scanner);
//System.out.println("INPUT: " + input);

        String numberLine = input.remove(0);

        List<Integer> numbers = Stream.of(numberLine.split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        ArrayList<BingoBoard> boards = createBoards(input);
//System.out.println("Bingo Boards: " + boards);


        for (int number : numbers) {
            int value = playNumber(boards, number);

            if (value > 0) {
                return value * number;
            }
        }

        return 0;
    }

    private int playNumber(ArrayList<BingoBoard> boards, int number) {
        for (BingoBoard board : boards) {
            board.playNumber(number);
            if (board.isWinner()) {
                return board.calculateScore();
            }
        }

        return -1;
    }

    private ArrayList<BingoBoard> createBoards(ArrayList<String> input) {
        ArrayList<String> boardInput = new ArrayList<String>();

        ArrayList<BingoBoard> boards = new ArrayList<BingoBoard>();

        for(String line : input) {
            if (line.isEmpty()) {
                boardInput = new ArrayList<String>();
                continue;
            }

            boardInput.add(line);

            if (boardInput.size() == 5) {
                boards.add(new BingoBoard(boardInput));
            }
        }

        return boards;
    }


    public int partTwo(Scanner scanner) {
        ArrayList<String> input = AocUtil.scannerToLineList(scanner);
//System.out.println("INPUT: " + input);

        String numberLine = input.remove(0);

        List<Integer> numbers = Stream.of(numberLine.split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        ArrayList<BingoBoard> boards = createBoards(input);
//System.out.println("Bingo Boards: " + boards);


        for (int number : numbers) {
            int value = playNumberToLose(boards, number);

            if (value > 0) {
                return value * number;
            }
        }

        return 0;
    }

    private int playNumberToLose(ArrayList<BingoBoard> boards, int number) {

        List<BingoBoard> boardsToRemove = new ArrayList<BingoBoard>();

        for (BingoBoard board : boards) {
            board.playNumber(number);
            if (board.isWinner()) {
                if (boards.size() == 1) {
                    return board.calculateScore();
                } else {
                    boardsToRemove.add(board);
                }
            } 
        }

System.out.println("Boards size before remove: " + boards.size());
System.out.println("Number to remove: " + boardsToRemove.size());
        boards.removeAll(boardsToRemove);
System.out.println("Boards size after remove: " + boards.size());

        return -1;
    }
}


