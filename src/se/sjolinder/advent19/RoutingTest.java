package se.sjolinder.advent19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoutingTest {

    @Test
    void part1() {
        String input =
                "     |          \n" +
                        "     |  +--+    \n" +
                        "     A  |  C    \n" +
                        " F---|----E|--+ \n" +
                        "     |  |  |  D \n" +
                        "     +B-+  +--+ \n" +
                        "                ";
        assertEquals("ABCDEF", Routing.part1(input));
    }
    @Test
    void part2() {
        String input =
                "     |          \n" +
                        "     |  +--+    \n" +
                        "     A  |  C    \n" +
                        " F---|----E|--+ \n" +
                        "     |  |  |  D \n" +
                        "     +B-+  +--+ \n" +
                        "                ";
        assertEquals(38, Routing.part2(input));
    }
}