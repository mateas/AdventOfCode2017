package se.sjolinder.advent17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpinlockTest {

    @Test
    void part() {
        assertEquals(638, Spinlock.part1(3));
    }
}