package se.sjolinder.advent08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionsTest {

    @Test
    void part1() {
        String input = "b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10";
        assertEquals(1, Instructions.part1(input));
    }
    @Test
    void part2() {
        String input = "b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10";
        assertEquals(10, Instructions.part2(input));
    }
}