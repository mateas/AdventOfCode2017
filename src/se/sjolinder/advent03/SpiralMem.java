package se.sjolinder.advent03;

import java.util.HashMap;

public class SpiralMem {
    public static void main(String[] args) {

        int input = 347991;
        System.out.println("Advent03: part1 1: " + part1(input));
        System.out.println("Advent03: part1 2: " + part2(input));
    }

    public static int part2(int x, int y) {
        return _part2(x, y, Integer.MAX_VALUE);
    }
    public static int part2(int stopValue) {
        return _part2(Integer.MAX_VALUE, Integer.MAX_VALUE, stopValue);
    }
    private static int _part2(int x, int y, int stopValue) {
        HashMap<String, Integer> values = new HashMap<>();
        values.put(getKey(0, 0), 1);

        return _part2(values, x, y, stopValue);
    }

    private static int _part2(HashMap<String, Integer> values, int x, int y, int stopValue) {
        if (values.containsKey(getKey(x, y))) {
            return values.get(getKey(x, y));
        }

        int xBlocks = 0;
        int yBlocks = 0;
        int[] xFactor = {1, 0, -1, 0};
        int[] yFactor = {0, 1, 0, -1};
        int[] xScan = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] yScan = {-1, -1, -1, 0, 0, 1, 1, 1};

        int compas = 0;
        int strikeLength = 1;
        int strikeCounter = 0;
        int sum = 1;
        for (int i = 2; i <= Integer.MAX_VALUE; i++) {
            if (xBlocks == x && yBlocks == y)
                break;
            xBlocks += xFactor[compas % 4];
            yBlocks += yFactor[compas % 4];
            strikeCounter++;

            if (strikeCounter == strikeLength) {
                if (compas % 2 == 1) {
                    strikeLength++;
                }
                compas++;
                strikeCounter = 0;
            }

            sum = 0;
            for (int j = 0; j < 8; j++) {
                int adjX =xBlocks +xScan[j];
                int adjY =yBlocks +yScan[j];
                if (values.containsKey(getKey(adjX, adjY))) {
                    sum += values.get(getKey(adjX, adjY));
                }
            }
            values.put(getKey(xBlocks, yBlocks), sum);

            if (sum > stopValue)
                break;
        }
        return sum;
    }

    private static String getKey(int x, int y) {
        return x + "_" + y;
    }

    public static int part1(int input) {
        int xBlocks = 0;
        int yBlocks = 0;
        int[] xFactor = {1, 0, -1, 0};
        int[] yFactor = {0, 1, 0, -1};
        int compas = 0;
        int strikeLength = 1;
        int strikeCounter = 0;
        for (int i = 2; i <= input; i++) {
            xBlocks += xFactor[compas % 4];
            yBlocks += yFactor[compas % 4];
            strikeCounter++;

            if (strikeCounter == strikeLength) {
                if (compas % 2 == 1) {
                    strikeLength++;
                }
                compas++;
                strikeCounter = 0;
            }
        }
        return Math.abs(xBlocks) + Math.abs(yBlocks);
    }
}
