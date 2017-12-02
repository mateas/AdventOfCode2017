package se.sjolinder.advent02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChecksumTest {

    @Test
    void calcSum1() {
        String spreadsheet = "5 1 9 5\n" +
                "7 5 3\n" +
                "2 4 6 8";
        assertEquals(18, Checksum.part1(spreadsheet));
    }

    @Test
    void calcSum2() {
        String spreadsheet = "5 9 2 8\n" +
                "9 4 7 3\n" +
                "3 8 6 5";
        Checksum checksum = new Checksum();
        assertEquals(9, checksum.part2(spreadsheet));
    }
}