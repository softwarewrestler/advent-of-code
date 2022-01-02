package com.agaeg.aoc2021;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Day1 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public int largerThanPrev(Scanner scanner) {

        ArrayList<Integer> input = AocUtil.scannerToIntList(scanner);

        int previous = 10000000;
        int count = 0;

        for (Integer current : input) {
            if (current > previous) {
                count++;
            }

            previous = current;
        }
        return count;
    }


    public int partTwo(Scanner scanner) {
        ArrayList<Integer> input = AocUtil.scannerToIntList(scanner);

        int previous = 10000000;
        int count = 0;

        for (int i=0; i<input.size() - 2; i++) {
            int current = input.get(i) + input.get(i+1) + input.get(i+2);

//System.out.println("CURRENT: " + current + ", PREVIOUS: " + previous);
            if (current > previous) {
                count++;
            }

            previous = current;
        }

        return count;
    }
}

