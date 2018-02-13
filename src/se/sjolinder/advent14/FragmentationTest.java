package se.sjolinder.advent14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FragmentationTest {

    @Test
    void part1() {
        String input = "flqrgnkx";
        assertEquals(8108,Fragmentation.part1(input));
    }
    @Test
    void part2() {
        String input = "flqrgnkx";
        assertEquals(1242,Fragmentation.part2(input));
    }
}