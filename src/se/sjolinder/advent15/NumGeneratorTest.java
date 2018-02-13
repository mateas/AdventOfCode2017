package se.sjolinder.advent15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumGeneratorTest {

    @Test
    void part1() {
        assertEquals(588, NumGenerator.part1(65, 8921));
    }
    @Test
    void part2() {
        assertEquals(309, NumGenerator.part2(65, 8921));
    }
}