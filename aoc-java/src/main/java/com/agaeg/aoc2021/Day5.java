package com.agaeg.aoc2021;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.util.Pair;

public class Day5 {
    private static String VENT_REGEX = "(\\d+),(\\d+)\\s->\\s(\\d+),(\\d+)";
    private static Pattern VENT_PATTERN = Pattern.compile(VENT_REGEX);

    public int partOne(Scanner scanner) {
        ArrayList<String> input = AocUtil.scannerToLineList(scanner);
//System.out.println("INPUT: " + input);
        List<Pair<Point,Point>> coords = input.stream().map(this::toPointPair).collect(Collectors.toList());

        Map<Point, Integer> map = new HashMap<Point, Integer>();

        for (Pair<Point, Point> points : coords) {
//System.out.println("POINTS: " + points);
            mapOutLines(points, map, false);
        }

        long count = map.values().stream().filter(value -> value > 1).count();
//System.out.println("COUNT: " + count);
    
        return (int)count;
    }

    private void mapOutLines(Pair<Point,Point> points, Map<Point, Integer> map, boolean partTwo) {
        Set<Point> pointsInLine = getLinePoints(points, partTwo);

        if (pointsInLine == null) {
            return;
        }

        for (Point point : pointsInLine) {
            int count = map.getOrDefault(point, 0);
            count++;
            map.put(point, count);
        }
    }

    private Set<Point> getLinePoints(Pair<Point, Point> points, boolean partTwo) {
        Point origin = points.getKey();
        Point dest = points.getValue();

        if (origin.getX() == dest.getX()) {
            return getHorizontalLine((int) origin.getX(), (int) origin.getY(), (int) dest.getY());
        }

        if (origin.getY() == dest.getY()) {
            return getVerticalLine((int) origin.getY(), (int) origin.getX(), (int) dest.getX());
        }

        if (partTwo) {
            return getDiagonalLine((int) origin.getX(), (int) dest.getX(), (int) origin.getY(), (int) dest.getY());
        }

        return null;
    }

    private Set<Point> getHorizontalLine(int x, int y1, int y2) {
        Set<Point> pointsInLine = new HashSet<Point>();

        if (y1 < y2) {
            for (int i=y1; i<=y2; i++) {
                pointsInLine.add(new Point(x, i));
            }
        } else {
            for (int i=y2; i<=y1; i++) {
                pointsInLine.add(new Point(x, i));
            }
        }

        return pointsInLine;
    }

    private Set<Point> getVerticalLine(int y, int x1, int x2) {
        Set<Point> pointsInLine = new HashSet<Point>();

        if (x1 < x2) {
            for (int i=x1; i<=x2; i++) {
                pointsInLine.add(new Point(i, y));
            }
        } else {
            for (int i=x2; i<=x1; i++) {
                pointsInLine.add(new Point(i, y));
            }
        }

        return pointsInLine;
    }

    private Set<Point> getDiagonalLine(int x1, int x2, int y1, int y2) {
        Set<Point> pointsInLine = new HashSet<Point>();

        int diff = Math.abs(x2 - x1);
        for(int i=0; i<=diff; i++) {
            int newX = 0;
            int newY = 0;
            if (x1 < x2) {
                newX = x1 + i;
            } else {
                newX = x1 - i;
            }
            if (y1 < y2) {
                newY = y1 + i;
            } else {
                newY = y1 - i;
            }

            pointsInLine.add(new Point(newX, newY));
        }

        return pointsInLine;
    }


    private Pair<Point, Point> toPointPair(String line) {
        Matcher matcher = VENT_PATTERN.matcher(line);

        if (matcher.find()) {
            String x1 = matcher.group(1);
            String y1 = matcher.group(2);
            String x2 = matcher.group(3);
            String y2 = matcher.group(4);

            Point point1 = new Point(Integer.parseInt(x1),Integer.parseInt(y1));
            Point point2 = new Point(Integer.parseInt(x2),Integer.parseInt(y2));

            return new Pair<Point, Point>(point1, point2);
        }

        return null;
    }


    private void printMap(Map<Point,Integer> map) {
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                Point point = new Point(i,j);
                Integer count = map.get(point);
                if (count == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(count + " ");
                }
            }
            System.out.println();
        }
    }

    public int partTwo(Scanner scanner) {
        ArrayList<String> input = AocUtil.scannerToLineList(scanner);
System.out.println("INPUT: " + input);
        List<Pair<Point,Point>> coords = input.stream().map(this::toPointPair).collect(Collectors.toList());

        Map<Point, Integer> map = new HashMap<Point, Integer>();

        for (Pair<Point, Point> points : coords) {
System.out.println("POINTS: " + points);
            mapOutLines(points, map, true);
        }

        long count = map.values().stream().filter(value -> value > 1).count();
System.out.println("COUNT: " + count);
    
        return (int)count;
    }
}



