package se.sjolinder.advent05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionsTest {

    @Test
    void part1() {
        String input = "0\n" +
                "3\n" +
                "0\n" +
                "1\n" +
                "-3";
        assertEquals(5, Instructions.part1(input));

    }
    @Test
    void part2() {
        String input = "0\n" +
                "3\n" +
                "0\n" +
                "1\n" +
                "-3";
        assertEquals(10, Instructions.part2(input));

    }
}