package com.agaeg.aoc2021;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;

public class AocUtil {
    private static String SUB_COMMAND_REGEX = "(\\w+)\\s+(\\d+)";
    private static Pattern SUB_COMMAND_PATTERN = Pattern.compile(SUB_COMMAND_REGEX);

    public static Scanner readFile(String filename) throws Exception {
        File file = new File(filename);
        return new Scanner(file);
    }

    public static ArrayList<Integer> scannerToIntList(Scanner scanner) {
        ArrayList<Integer> input = new ArrayList();
        while (scanner.hasNext()) {
            input.add(scanner.nextInt());
        }

        return input;
    }

    public static ArrayList<String> scannerToStringList(Scanner scanner) {
        ArrayList<String> input = new ArrayList<String>();
        while (scanner.hasNext()) {
            input.add(scanner.next().trim());
        }

        return input;
    }

    public static ArrayList<String> scannerToLineList(Scanner scanner) {
        ArrayList<String> input = new ArrayList<String>();
        while (scanner.hasNext()) {
            input.add(scanner.nextLine().trim());
        }

        return input;
    }

    public static ArrayList<Pair<String, Integer>> scannerToPairList(Scanner scanner) {
        ArrayList<Pair<String, Integer>> input = new ArrayList<Pair<String, Integer>>();

        while (scanner.hasNext()) {
            String stringPair = scanner.nextLine();
            Pair<String, Integer> pair = AocUtil.stringToPair(stringPair);

            if(pair != null) {
                input.add(pair);
            } else {
                System.out.println("ERROR splitting pair: " + stringPair);
            }

        }

//System.out.println("Input List as Pairs: " + input);
        return input;
    }

    public static Pair<String, Integer> stringToPair(String pairString) {
        Matcher matcher = SUB_COMMAND_PATTERN.matcher(pairString);

        if (matcher.find()) {
            String direction = matcher.group(1);
            Integer amount = Integer.parseInt(matcher.group(2));

            return new Pair<String, Integer>(direction, amount);
        }

        return null;
    }


}
