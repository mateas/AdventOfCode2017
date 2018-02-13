package se.sjolinder.advent18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuetTest {

    @Test
    void part1() {
        String input = "set a 1\n" +
                "add a 2\n" +
                "mul a a\n" +
                "mod a 5\n" +
                "snd a\n" +
                "set a 0\n" +
                "rcv a\n" +
                "jgz a -1\n" +
                "set a 1\n" +
                "jgz a -2\n";
        assertEquals(4, Duet.part1(input));
    }

    @Test
    void part2() {
        String input = "snd 1\n" +
                "snd 2\n" +
                "snd p\n" +
                "rcv a\n" +
                "rcv b\n" +
                "rcv c\n" +
                "rcv d";
        Duet.part2(input);
    }
}