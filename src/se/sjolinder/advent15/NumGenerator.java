package se.sjolinder.advent15;

public class NumGenerator {
    public static void main(String[] args) {
        int seedA = 634;
        int seedB = 301;
        System.err.println("Advent15: part1 1: " + part1(seedA, seedB));
        System.err.println("Advent15: part1 2: " + part2(seedA, seedB));
    }

    public static int part1(int seedA, int seedB) {
        int factorA = 16807;
        int factorB = 48271;

        int prevA = seedA;
        int prevB = seedB;
        int count = 0;
        for (int i = 0; i < 40000000; i++) {
            int newA = generator(prevA, factorA);
            int newB = generator(prevB, factorB);

            int comparision = newA ^ newB;
            comparision = comparision & 0x0000FFFF;
            if (comparision == 0) {
                count++;
            }
            prevA = newA;
            prevB = newB;
        }
        return count;
    }

    public static int part2(int seedA, int seedB) {
        int factorA = 16807;
        int factorB = 48271;
        int multipleA = 4;
        int multipleB = 8;

        int prevA = seedA;
        int prevB = seedB;
        int count = 0;
        for (int i = 0; i < 5000000; i++) {
            int newA = generator(prevA, factorA, multipleA);
            int newB = generator(prevB, factorB, multipleB);

            int comparision = newA ^ newB;
            comparision = comparision & 0x0000FFFF;
            if (comparision == 0) {
                count++;
            }
            prevA = newA;
            prevB = newB;
        }
        return count;
    }

    private static int generator(int lastValue, int factor) {

        long newValue = ((long) lastValue * factor);
        newValue = newValue % 2147483647;
        return (int) newValue;
    }

    private static int generator(int lastValue, int factor, int multiple) {
        do {
            lastValue = generator(lastValue, factor);

        } while (lastValue % multiple != 0);
        return (int) lastValue;

    }

}
