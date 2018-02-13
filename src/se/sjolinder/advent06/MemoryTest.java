package se.sjolinder.advent06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryTest {

    @Test
    void part1() {
        String input = "0   2   7   0";
        assertEquals(5, Memory.part1(input));

    }
    @Test
    void part2() {
        String input = "0   2   7   0";
        assertEquals(4, Memory.part2(input));

    }
}