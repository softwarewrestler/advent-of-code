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
public class Day2Test 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Day2Test( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Day2Test.class );
    }


    public void testPartOne() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("day2sample.txt");
        Day2 day2 = new Day2();
        int product = day2.partOne(new Scanner(inputStream));
        assertEquals(150, product);

        inputStream = getClass().getClassLoader().getResourceAsStream("day2.txt");
        product = day2.partOne(new Scanner(inputStream));
        System.out.println("Day 2 - Part 1: " + product);
    }

    public void testPartTwo() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("day2sample.txt");
        Day2 day2 = new Day2();
        int count = day2.partTwo(new Scanner(inputStream));
        assertEquals(900, count);

        inputStream = getClass().getClassLoader().getResourceAsStream("day2.txt");
        count = day2.partTwo(new Scanner(inputStream));
        System.out.println("Day 2 - Part 2: " + count);
    }
}


