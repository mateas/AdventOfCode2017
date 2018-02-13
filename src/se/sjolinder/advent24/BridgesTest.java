package se.sjolinder.advent24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BridgesTest {

    @Test
    void part1() {
        String input = "0/2\n" +
                "2/2\n" +
                "2/3\n" +
                "3/4\n" +
                "3/5\n" +
                "0/1\n" +
                "10/1\n" +
                "9/10";
        assertEquals(31, Bridges.part1(input));
    }
    @Test
    void part2() {
        String input = "0/2\n" +
                "2/2\n" +
                "2/3\n" +
                "3/4\n" +
                "3/5\n" +
                "0/1\n" +
                "10/1\n" +
                "9/10";
        assertEquals(31, Bridges.part2(input));
    }
}