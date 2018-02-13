package se.sjolinder.advent10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnotHashTest {

    @Test
    void part1() {
        String input = "3, 4, 1, 5";
        assertEquals(12, KnotHash.part1(input, 5));
    }

    @Test
    void part2() {
        // assertEquals("a2582a3a0e66e6e86e3812dcb672a272", KnotHash.part2("", 256));
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", KnotHash.part2("AoC 2017", 256));
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", KnotHash.part2("1,2,3", 256));
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", KnotHash.part2("1,2,4", 256));
    }

    @Test
    void part2_input() {
        String input = "1,2,3";
        int[] ascii = KnotHash.parseInput2(input);
        List<String> asciiString = new ArrayList<String>();
        for (int i = 0; i < ascii.length; i++) {
            asciiString.add(ascii[i] + "");
        }
        assertEquals("49,44,50,44,51,17,31,73,47,23", String.join(",", asciiString));
    }
}