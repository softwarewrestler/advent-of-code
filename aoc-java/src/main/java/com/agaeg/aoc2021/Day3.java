package com.agaeg.aoc2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class Day3 
{
    public int partOne(Scanner scanner) {
        ArrayList<String> input = AocUtil.scannerToStringList(scanner);
        Map<Integer, Integer> map = new HashMap();
        int inputCount = parseInput(input, map);

        int gamma = calculateGamma(map, inputCount);
        int epsilon = calculateEpsilon(map, inputCount);
//System.out.println("GAMMA: " + gamma);
//System.out.println("EPSILON: " + epsilon);

        return gamma * epsilon;
    }

    private int parseInput(ArrayList<String> input, Map<Integer, Integer> map) {
        int lineCount = 0;

        for (String line : input) {
            for (int i=0; i<line.length(); i++) {
                char bit  = line.charAt(i);
                if (bit == '1') {
                    int count = map.getOrDefault(i, 0);
                    count++;
                    map.put(i, count);
                }
            }
            lineCount ++;
        }
        
        return lineCount;
    }

    private int calculateEpsilon(Map<Integer, Integer> map, int count) {
        int index = 0;
        StringBuffer binaryString = new StringBuffer();
        int halfCount = count/2;

        while (map.containsKey(index)) {
            int oneCount = map.get(index);

            if (oneCount > halfCount) {
                binaryString.append(0);
            } else {
                binaryString.append(1);
            }
            index++;
        }

//System.out.println("BINARY STRING: " + binaryString);

        return Integer.parseInt(binaryString.toString(), 2);
    }

    private int calculateGamma(Map<Integer, Integer> map, int count) {
        int index = 0;
        StringBuffer binaryString = new StringBuffer();
        int halfCount = count/2;

        while (map.containsKey(index)) {
            int oneCount = map.get(index);

            if (oneCount > halfCount) {
                binaryString.append(1);
            } else {
                binaryString.append(0);
            }
            index++;
        }

//System.out.println("BINARY STRING: " + binaryString);

        return Integer.parseInt(binaryString.toString(), 2);
    }
    
/*
PART TWO
*/
    public int partTwo(Scanner scanner) {
        ArrayList<String> input = AocUtil.scannerToStringList(scanner);

        return processInput2(input);
    }

    private int processInput2(ArrayList<String> input) {
        int oxygen = computeOxygen(input, 0);
System.out.println("OXYGEN: " + oxygen);

        int co2 = computeCo2(input, 0);
System.out.println("CO2: " + co2);

        return oxygen * co2;
    }

    private int computeOxygen(List<String> input, int index) {
System.out.println("INPUT SIZE: " + input.size());
        //if (index == 2) {
        if (input.size() == 1) {
            return Integer.parseInt(input.get(0), 2);
        }

        List<String> filtered = filterMax(input, index);

        return computeOxygen(filtered, ++index);
    }

    private List<String> filterMax(List<String> input, int index) {
        char max = getMostCommon(input, index);
System.out.println("filterMax max: " + max);

        return filterInput(index, max, input);
    }

    private int computeCo2(List<String> input, int index) {
System.out.println("INPUT SIZE: " + input.size());
        //if (index == 2) {
        if (input.size() == 1) {
            return Integer.parseInt(input.get(0), 2);
        }

        List<String> filtered = filterLeastCommon(input, index);

        return computeCo2(filtered, ++index);
    }

    private List<String> filterLeastCommon(List<String> input, int index) {
        char leastCommon = getLeastCommon(input, index);
System.out.println("filterLeastCommon leastCommon: " + leastCommon);

        return filterInput(index, leastCommon, input);
    }

    private char getMostCommon(List<String> input, int index) {
        int zeroCount = 0;
        int oneCount = 0;
        for (String inputLine : input) {
            char bit = inputLine.charAt(index);
System.out.println("Count this bit: " + bit);

            if (bit == '0') {
                zeroCount++;
            } else {
                oneCount++;
            }
        }

        if (oneCount >= zeroCount) {
            return '1';
        }

        return '0';
    }

    private char getLeastCommon(List<String> input, int index) {
        int zeroCount = 0;
        int oneCount = 0;
        for (String inputLine : input) {
            char bit = inputLine.charAt(index);

            if (bit == '0') {
                zeroCount++;
            } else {
                oneCount++;
            }
        }

        if (zeroCount <= oneCount) {
            return '0';
        }

        return '1';
    }

    private List<String> filterInput(int index, char bit, List<String> input) {
System.out.println("filterInput index: " + index);
System.out.println("filterInput bit: " + bit);
        List<String> filtered = new ArrayList<String>();
        for (String code : input) {
System.out.println("filterInput code: " + code);
            if (bit == code.charAt(index)) {
                filtered.add(code);
            }
        }
        /*
        List<String> filtered = input.stream()
            .filter(code -> code.charAt(index) == bit)
            .collect(Collectors.toList());
            */

System.out.println("filterInput INPUT SIZE: " + input.size());
System.out.println("filterInput FILTERED: " + filtered.size());
        return filtered;
    }

}

