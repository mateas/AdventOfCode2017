package se.sjolinder.advent21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitArtTest {

    @Test
    void part1() {
        String input = "../.# => ##./#../...\n" +
                ".#./..#/### => #..#/..../..../#..#";
        assertEquals(12, BitArt.part1(input, 2));
    }

    @Test
    void rotate() {

        Matrix<Integer> matrix = Matrix.parsString("#.#/##./.#.").rotate();
        matrix.toString();
    }
}