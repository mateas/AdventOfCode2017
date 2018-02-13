package se.sjolinder.advent16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDanceTest {

    @Test
    void part1() {
        assertEquals("baedc", ArrayDance.part1("abcde","s1,x3/4,pe/b", 1));
    }

    @Test
    void part2() {

        assertEquals("baedc", ArrayDance.part2("abcde", "s1,x3/4,pe/b", 1));
        String one1000 = ArrayDance.part2("abcde", "s1,x3/4,pe/b", 1000);
        assertEquals("baedc", ArrayDance.part2(one1000, "s1,x3/4,pe/b", 1000000));
    }
}