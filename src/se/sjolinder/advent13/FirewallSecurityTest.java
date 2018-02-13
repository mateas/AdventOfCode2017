package se.sjolinder.advent13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirewallSecurityTest {

    @Test
    void part1() {
        String input = "0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4";
        assertEquals(24, FirewallSecurity.part1(input));
    }
    @Test
    void part2() {
        String input = "0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4";
        assertEquals(10, FirewallSecurity.part2(input));
    }
}