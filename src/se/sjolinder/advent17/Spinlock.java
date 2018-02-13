package se.sjolinder.advent17;

import java.util.ArrayList;

public class Spinlock {
    public static void main(String[] args) {
        int input = 348;
        System.err.println("Advent17: part1 1: " + part1(input));
        System.err.println("Advent17: part1 2: " + part2(input));
    }

    public static int part1(int input) {
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        buffer.add(0);
        int index = 0;
        for (int i = 1; i < 2018; i++) {
            index = (index + input) % buffer.size();
            buffer.add(++index, i);
        }
        return buffer.get(index + 1);

    }

    public static int part2(int input) {
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        buffer.add(0);
        int index = 0;
        int i = 1;
        int valueAtIndex1 = 0;
        for (; i < 50000000; i++) {
            index = (index + input) % i;
            if (++index == 1) {
                valueAtIndex1 = i;
            }
        }

        return valueAtIndex1;

    }


}
