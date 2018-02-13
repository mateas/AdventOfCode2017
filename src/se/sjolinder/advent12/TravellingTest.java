package se.sjolinder.advent12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TravellingTest {

    @Test
    void part1() {
        String input = "0 <-> 2\n" +
                "1 <-> 1\n" +
                "2 <-> 0, 3, 4\n" +
                "3 <-> 2, 4\n" +
                "4 <-> 2, 3, 6\n" +
                "5 <-> 6\n" +
                "6 <-> 4, 5";
        assertEquals(6, Travelling.part1(input));
    }
    @Test
    void part2() {
        String input = "0 <-> 2\n" +
                "1 <-> 1\n" +
                "2 <-> 0, 3, 4\n" +
                "3 <-> 2, 4\n" +
                "4 <-> 2, 3, 6\n" +
                "5 <-> 6\n" +
                "6 <-> 4, 5";
        assertEquals(2, Travelling.part2(input));
    }
}