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
public class Day1Test 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Day1Test( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Day1Test.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testDay1() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("day1sample.txt");
        Day1 day1 = new Day1();
        int count = day1.largerThanPrev(new Scanner(inputStream));
        assertEquals(7, count);

        inputStream = getClass().getClassLoader().getResourceAsStream("day1.txt");
        count = day1.largerThanPrev(new Scanner(inputStream));
        System.out.println("Day 1: " + count);
    }

    public void testPartTwo() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("day1sample.txt");
        Day1 day1 = new Day1();
        int count = day1.partTwo(new Scanner(inputStream));
        assertEquals(5, count);

        inputStream = getClass().getClassLoader().getResourceAsStream("day1.txt");
        count = day1.partTwo(new Scanner(inputStream));
        System.out.println("Day 1 - Part 2: " + count);
    }
}

