package se.sjolinder.advent09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamParserTest {

    @Test
    void part1() {

        assertEquals(1, StreamParser.part1("{}"));
        assertEquals(6, StreamParser.part1("{{{}}}"));
        assertEquals(5, StreamParser.part1("{{},{}}"));
        assertEquals(16, StreamParser.part1("{{{},{},{{}}}}"));
        assertEquals(1, StreamParser.part1("{<a>,<a>,<a>,<a>}"));
        assertEquals(9, StreamParser.part1("{{<ab>},{<ab>},{<ab>},{<ab>}}"));
        assertEquals(9, StreamParser.part1("{{<!!>},{<!!>},{<!!>},{<!!>}}"));
        assertEquals(3, StreamParser.part1("{{<a!>},{<a!>},{<a!>},{<ab>}}"));
    }
    @Test
    void part2() {

        assertEquals(0, StreamParser.part2("<>"));
        assertEquals(6, StreamParser.part2("<sdadas>"));
        assertEquals(3, StreamParser.part2("<<<<>"));
        assertEquals(2, StreamParser.part2("<{!>}>"));
        assertEquals(0, StreamParser.part2("<!!>"));
        assertEquals(0, StreamParser.part2("<!!!>>"));
        assertEquals(10, StreamParser.part2("<{o\"i!a,<{i<a>"));
    }
}