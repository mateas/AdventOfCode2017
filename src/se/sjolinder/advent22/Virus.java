package se.sjolinder.advent22;

import java.util.ArrayList;
import java.util.HashMap;

public class Virus {
    public static void main(String[] args) {
        String input = ".##.#..#...#....###....#.\n" +
                "#.#######.##.##.#.##.##..\n" +
                ".##.#..#.###.#....###..##\n" +
                "......#.#..##.##...#.#.##\n" +
                ".#.##.##.######...##.#..#\n" +
                "###...#..####..######.#..\n" +
                "###....#....#..#####.#.##\n" +
                "..##..#..#.#.#.#....#####\n" +
                "#.#.......##.#....##..#.#\n" +
                "##..#.###.##.####.##...#.\n" +
                "#.####.##.##..##.#.##.##.\n" +
                "###.#..##.##.#.####...#..\n" +
                "######.#...#....#.#...#..\n" +
                ".#.#.###.##.##..#.#....##\n" +
                "#.###..##....###.###..#.#\n" +
                ".#..##.......#..#.##.##.#\n" +
                "..#...####...##.#.##..#.#\n" +
                "..#.##..#..##.###.#####.#\n" +
                "##..##.##....#..###.#.###\n" +
                ".#..######.#.####..#.###.\n" +
                "##...####..##.#.#.#.#.###\n" +
                "#.#....###...##.##..##.#.\n" +
                "..###.#####.####.#.#..#..\n" +
                "..####..#.#....#.###.....\n" +
                ".#......#.#..####.###....";
        System.err.println("Advent22: part 1: " + part1(input));
        System.err.println("Advent22: part 2: " + part2(input));
    }

    public static int part1(String input) {
        String[] inputRows = input.split(System.getProperty("line.separator"));

        ArrayList<String> matrix = new ArrayList<>();
        for (int y = 0; y < inputRows.length; y++) {
            String inputRow = inputRows[y];
            for (int x = 0; x < inputRow.length(); x++) {
                char cell = inputRow.charAt(x);
                if (cell == '#')
                    matrix.add(getKey(x, y));
            }
        }
        int xPos = inputRows[0].length() / 2;
        int yPos = inputRows.length / 2;

        int compas = 0;
        int[] xFactor = {0, 1, 0, -1};
        int[] yFactor = {-1, 0, 1, 0};
        int infectionCounter = 0;
        for (int i = 0; i < 10000; i++) {
            if (matrix.contains(getKey(xPos, yPos))) {
                compas++;
                matrix.remove(getKey(xPos, yPos));
            } else {
                matrix.add(getKey(xPos, yPos));
                compas = compas + 3;
                infectionCounter++;
            }
            compas = compas % 4;
            xPos += xFactor[compas];
            yPos += yFactor[compas];
        }

        return infectionCounter;
    }

    public static int part2(String input) {
        String[] inputRows = input.split(System.getProperty("line.separator"));

        HashMap<String, Integer> matrix = new HashMap<>();
        for (int y = 0; y < inputRows.length; y++) {
            String inputRow = inputRows[y];
            for (int x = 0; x < inputRow.length(); x++) {
                char cell = inputRow.charAt(x);
                if (cell == '#')
                    matrix.put(getKey(x, y), 2);
            }
        }
        int xPos = inputRows[0].length() / 2;
        int yPos = inputRows.length / 2;

        int compas = 0;
        int[] xFactor = {0, 1, 0, -1};
        int[] yFactor = {-1, 0, 1, 0};
        int infectionCounter = 0;
        for (int i = 0; i < 10000000; i++) {
            if (matrix.containsKey(getKey(xPos, yPos))) {
                Integer state = matrix.get(getKey(xPos, yPos));
                switch (state) {
                    case 1:
                        matrix.put(getKey(xPos, yPos), 2);
                        infectionCounter++;
                        break;
                    case 2:
                        matrix.put(getKey(xPos, yPos), 3);
                        compas++;
                        break;
                    case 3:
                        matrix.remove(getKey(xPos, yPos));
                        compas = compas + 2;
                        break;
                }
            } else {
                matrix.put(getKey(xPos, yPos), 1);
                compas = compas + 3;
            }
            compas = compas % 4;
            xPos += xFactor[compas];
            yPos += yFactor[compas];
        }

        return infectionCounter;
    }

    public static String getKey(int x, int y) {
        return x + "_" + y;
    }
}
