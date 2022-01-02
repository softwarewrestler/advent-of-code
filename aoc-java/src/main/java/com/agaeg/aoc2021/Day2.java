package com.agaeg.aoc2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.util.Pair;

public class Day2 
{
    private static String FORWARD = "forward";
    private static String UP = "up";
    private static String DOWN = "down";
    private static String AIM_KEY = "aim";
    private static String DEPTH_KEY = "depth";


    public int partOne(Scanner scanner) {
        ArrayList input = AocUtil.scannerToPairList(scanner);
        Map<String, Integer> map = processInput(input);

        int upCount = map.getOrDefault(UP, 0);
        int downCount = map.getOrDefault(DOWN, 0);
        int forwardCount = map.getOrDefault(FORWARD, 0);

//System.out.println("COUNTS:\n\tUP: " + upCount + "\n\tDOWN: " + downCount + "\n\tFORWARD: " + forwardCount);

        return forwardCount * (downCount - upCount);
    }

    private Map<String, Integer> processInput(ArrayList<Pair<String, Integer>> input) {
        Map<String, Integer> map = new HashMap();
        for (Pair<String, Integer> pair : input) {
            int value = map.getOrDefault(pair.getKey(), 0);
            int total = value + pair.getValue();

            map.put(pair.getKey(), total);
        }

//System.out.println("MAP: " + map);
        return map;
    }


    public int partTwo(Scanner scanner) {
        ArrayList<Pair<String, Integer>> input = AocUtil.scannerToPairList(scanner);

        Map<String, Integer> map = processInput2(input);

        int forward = map.get(FORWARD);
        int depth = map.get(DEPTH_KEY);


        return forward * depth;
    }

    private Map<String, Integer> processInput2(ArrayList<Pair<String, Integer>> input) {
        Map<String, Integer> map = new HashMap();
        map.put(AIM_KEY, 0);
        map.put(DEPTH_KEY, 0);
        map.put(FORWARD, 0);
        for (Pair<String, Integer> pair : input) {
            processCommand(pair.getKey(), pair.getValue(), map);
        }

        return map;
    }

    private void processCommand(String command, Integer amount, Map<String, Integer> map) {
        if (DOWN.equals(command)) {
            int aim = map.get(AIM_KEY);
            map.put(AIM_KEY, aim + amount);
            return;
        }

        if (UP.equals(command)) {
            int aim = map.get(AIM_KEY);
            map.put(AIM_KEY, aim - amount);
            return;
        }

        if (FORWARD.equals(command)) {
            int forward = map.get(FORWARD);
            map.put(FORWARD, forward + amount);

            int depth = map.get(DEPTH_KEY);
            int aim = map.get(AIM_KEY);
            map.put(DEPTH_KEY, depth + (aim * amount));
        }
    }

}
