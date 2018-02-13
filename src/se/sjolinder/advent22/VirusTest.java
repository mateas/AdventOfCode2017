package se.sjolinder.advent22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VirusTest {

    @Test
    void part1() {
        String input="..#\n" +
                "#..\n" +
                "...";
        assertEquals(5587, Virus.part1(input));
    }
    @Test
    void part2() {
        String input="..#\n" +
                "#..\n" +
                "...";
        assertEquals(2511944, Virus.part2(input));
    }
}