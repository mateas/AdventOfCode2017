package se.sjolinder.advent03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpiralMemTest {

    @Test
    void part1_1() {
        int input = 1;
        assertEquals(0, SpiralMem.part1(input));
    }
    @Test
    void part1_2() {
        int input = 12;
        assertEquals(3, SpiralMem.part1(input));
    }
    @Test
    void part1_3() {
        int input = 23;
        assertEquals(2, SpiralMem.part1(input));
    }
    @Test
    void part1_4() {
        int input = 1024;
        assertEquals(31, SpiralMem.part1(input));
    }
    @Test
    void part1_Puzzle() {
        int input = 347991;
        assertEquals(480, SpiralMem.part1(input));
    }

    @Test
    void part2_1() {
        int x = 0;
        int y = 0;
        assertEquals(1, SpiralMem.part2(x, y));
    }
    @Test
    void part2_2() {
        int x = 1;
        int y = 0;
        assertEquals(1, SpiralMem.part2(x,y));
    }
    @Test
    void part2_3() {
        int x = 1;
        int y = 1;
        assertEquals(2, SpiralMem.part2(x,y));
    }
    @Test
    void part2_4() {
        int x = 0;
        int y = 1;
        assertEquals(4, SpiralMem.part2(x,y));
    }
    @Test
    void part2_5() {
        int x = -1;
        int y = 1;
        assertEquals(5, SpiralMem.part2(x,y));
    }
}