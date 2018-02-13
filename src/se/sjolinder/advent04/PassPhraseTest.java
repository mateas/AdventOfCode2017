package se.sjolinder.advent04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassPhraseTest {

    @Test
    void part1_1() {
        String passPhrase = "aa bb cc dd ee";
        assertTrue(PassPhrase.part1(passPhrase));
    }
    @Test
    void part1_2() {
        String passPhrase = "aa bb cc dd aa";
        assertFalse(PassPhrase.part1(passPhrase));
    }
    @Test
    void part1_3() {
        String passPhrase = "aa bb cc dd aaa";
        assertTrue(PassPhrase.part1(passPhrase));
    }

    @Test
    void part2_1() {
        String passPhrase = "abcde fghij";
        assertTrue(PassPhrase.part2(passPhrase));
    }
    @Test
    void part2_2() {
        String passPhrase = "abcde xyz ecdab";
        assertFalse(PassPhrase.part2(passPhrase));
    }
    @Test
    void part2_3() {
        String passPhrase = "a ab abc abd abf abj";
        assertTrue(PassPhrase.part2(passPhrase));
    }
    @Test
    void part2_4() {
        String passPhrase = "iiii oiii ooii oooi oooo";
        assertTrue(PassPhrase.part2(passPhrase));
    }
    @Test
    void part2_5() {
        String passPhrase = "oiii ioii iioi iiio";
        assertFalse(PassPhrase.part2(passPhrase));
    }

}