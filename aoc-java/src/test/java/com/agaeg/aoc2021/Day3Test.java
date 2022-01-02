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
public class Day3Test 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Day3Test( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Day3Test.class );
    }


    public void testPartOne() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("day3sample.txt");
        Day3 day3 = new Day3();
        int product = day3.partOne(new Scanner(inputStream));
        assertEquals(198, product);

        inputStream = getClass().getClassLoader().getResourceAsStream("day3.txt");
        product = day3.partOne(new Scanner(inputStream));
        System.out.println("Day 3 - Part 1: " + product);
    }

    public void testPartTwo() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("day3sample.txt");
        Day3 day3 = new Day3();
        int count = day3.partTwo(new Scanner(inputStream));
        assertEquals(230, count);

        inputStream = getClass().getClassLoader().getResourceAsStream("day3.txt");
        count = day3.partTwo(new Scanner(inputStream));
        System.out.println("Day 3 - Part 2: " + count);
    }
}



