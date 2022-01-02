package com.agaeg.aoc2021;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class Day5Test 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Day5Test( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Day5Test.class );
    }


    public void testPartOne() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("day5sample.txt");
        Day5 day5 = new Day5();
        int product = day5.partOne(new Scanner(inputStream));
        assertEquals(5, product);

        System.out.println("Now for real!");
        inputStream = getClass().getClassLoader().getResourceAsStream("day5.txt");
        product = day5.partOne(new Scanner(inputStream));
        System.out.println("Day 5 - Part 1: " + product);
    }

    public void testPartTwo() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("day5sample.txt");
        Day5 day5 = new Day5();
        int count = day5.partTwo(new Scanner(inputStream));
        assertEquals(12, count);

        inputStream = getClass().getClassLoader().getResourceAsStream("day5.txt");
        count = day5.partTwo(new Scanner(inputStream));
        System.out.println("Day 5 - Part 2: " + count);
    }
}




