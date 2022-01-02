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
public class Day4Test 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Day4Test( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Day4Test.class );
    }


    public void testPartOne() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("day4sample.txt");
        Day4 day4 = new Day4();
        int product = day4.partOne(new Scanner(inputStream));
        assertEquals(4512, product);

        inputStream = getClass().getClassLoader().getResourceAsStream("day4.txt");
        product = day4.partOne(new Scanner(inputStream));
        System.out.println("Day 4 - Part 1: " + product);
    }

    public void testPartTwo() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("day4sample.txt");
        Day4 day4 = new Day4();
        int count = day4.partTwo(new Scanner(inputStream));
        assertEquals(1924, count);

        inputStream = getClass().getClassLoader().getResourceAsStream("day4.txt");
        count = day4.partTwo(new Scanner(inputStream));
        System.out.println("Day 4 - Part 2: " + count);
    }
}




